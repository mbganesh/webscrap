package mb.ganesh.wens;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private boolean mIsServiceOn;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("mSerive" , "in OnBind");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("onStartCommand" , "My Thread : " + Thread.currentThread().getId());
        mIsServiceOn = isOnline();
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomNumbers();
            }
        }).start();

        return START_STICKY;
    }

    private void startRandomNumbers() {
        while (isOnline()){
            try{
                Thread.sleep(1000);
                if (isOnline()){
                    Log.e("NetworkState" , "Active");
                }
            }
            catch (Exception e){
                Log.e("Error : " , e.getMessage());
            }
        }
    }

    private void stopRandomNumbers() {
        mIsServiceOn = false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomNumbers();
        Log.e("onDestroyService" , "Service Destroyed");
    }

    class MyService2 extends Binder {
        public MyService getService(){
            return MyService.this;
        }
    }

    private IBinder mBinder = new MyService2();


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


}
