package mb.ganesh.wens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MaterialButton dummyBtn;
    Intent service;
    String cityName ;
    String display = "";
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        service = new Intent(MainActivity.this, MyService.class);

        dummyBtn = findViewById(R.id.dummnyBtn);
        // new check

        dummyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DummyActivity.class));
            }
        });

        result = findViewById(R.id.resultId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://www.ndtv.com/fuel-prices/petrol-price-in-"+cityName+"-city";
                try {
                    Document document = Jsoup.connect(url).get();

                    Elements root = document.getElementsByClass("prcContnr pl-10 mt-5");
                    Elements sub = root.first().getElementsByTag("h4");

                    for (Element e : sub) {
                        Log.e("Data", e.text());
                        display = e.text();
                        Log.e("Display", display);

                        Elements inner = root.first().getElementsByTag("span");
                        Log.e("Price", inner.text());
                        display += inner.text();

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            result.setText(" PetrolPrice : " + display.substring(0, display.length() - 4));
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Log.e("DisplayOut", display);

//        .substring(0 , display.length()-4)
//        https://github.com/mbganesh/webscrap.git

    }

    @Override
    protected void onStart() {
        super.onStart();
        startService(service);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(service);
    }
}

