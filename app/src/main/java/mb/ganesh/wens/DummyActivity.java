package mb.ganesh.wens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DummyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        String url = "https://www.ndtv.com/fuel-prices/petrol-price-in-tamil-nadu-state";


        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchData(url);
            }
        }).start();



    }

    private void fetchData(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Log.e("Doc" , doc.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}