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

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ArrayList arrayList2 = new ArrayList();

        String cityName = "Tirunelveli";
        String url = "https://www.ndtv.com/fuel-prices/petrol-price-in-"+cityName+"-city";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(url).get();
                    Elements elements = document.getElementsByClass("tabs_cont");

                    Elements elements1 = elements.addClass("font-16 color-blue");


                    for (Element e : elements1){

                        Elements inner = elements1.first().getElementsByTag("tr");

                        String sx1 = e.getElementsByTag("td").text();

                        for (int i = 0 ; i < inner.size() ; i++){

                        }

                        Log.e("sx1" , sx1);

                    }




                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }
}