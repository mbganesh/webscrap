package mb.ganesh.wens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Video1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video1);

        String url = "https://gadgets.ndtv.com/";
        Log.e("TitleCheck" , "Check");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).get();
                    String title = doc.title();
                    Log.e("Title" , title);

                }catch (Exception e){
                    e.getStackTrace();
                }
            }
        }).start();
    }
}

//    https://github.com/mbganesh/webscrap.git