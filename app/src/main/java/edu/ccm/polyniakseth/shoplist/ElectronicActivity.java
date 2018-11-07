package edu.ccm.polyniakseth.shoplist;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ElectronicActivity extends AppCompatActivity {


    Button dronePriceView;
    Button ps4PriceView;
    Button ipadPriceView;
    Button bosePriceView;
    Button sonosPriceView;

    String droneSite = "https://www.target.com/p/spark-drone-controller-combo-white/-/A-54017897";
    String ps4Site = "https://www.target.com/p/playstation-4-pro-1tb-console/-/A-51610033";
    String ipadSite = "https://www.target.com/p/apple-174-ipad-pro-10-5-wi-fi-only-2017-model/-/A-52652493?preselect=52607220#lnk=sametab";
    String boseSite = "https://www.target.com/p/bose-quietcomfort-35-wireless-headphones-ii-android-ios-black/-/A-52791828?preselect=52791828#lnk=sametab";
    String sonosSite = "https://www.target.com/p/sonos-one-wireless-speaker-with-amazon-alexa-voice-assistant/-/A-53293422?preselect=53275937#lnk=sametab";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic);

        dronePriceView = (Button) findViewById(R.id.btnDronePrice);
        ps4PriceView = (Button) findViewById(R.id.btnPS4Price);
        ipadPriceView = (Button) findViewById(R.id.btnIpadPrice);
        bosePriceView = (Button) findViewById(R.id.btnBosePrice);
        sonosPriceView = (Button) findViewById(R.id.btnSonosPrice);


        new doIt().execute();

        dronePriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(droneSite)));
            }
        });

        ps4PriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ps4Site)));
            }
        });

        ipadPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ipadSite)));
            }
        });

        bosePriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(boseSite)));
            }
        });

        sonosPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(sonosSite)));
            }
        });


    }

    public void openDrone(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(droneSite)));
    }
    public void openPs4(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ps4Site)));
    }
    public void openIpad(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ipadSite)));
    }
    public void openBose(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(boseSite)));
    }
    public void openSonos(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(sonosSite)));
    }



    public class doIt extends AsyncTask<Void,Void,Void>
    {
        String droneWords;
        String ps4Words;
        String ipadWords;
        String boseWords;
        String sonosWords;

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc1 = Jsoup.connect("https://www.target.com/p/spark-drone-controller-combo-white/-/A-54017897").get();        // Basketball
                Document doc2 = Jsoup.connect("https://www.target.com/p/playstation-4-pro-1tb-console/-/A-51610033").get();    // Baseball
                Document doc3 = Jsoup.connect("https://www.target.com/p/apple-174-ipad-pro-10-5-wi-fi-only-2017-model/-/A-52652493?preselect=52607220#lnk=sametab").get();    // Tennisballs
                Document doc4 = Jsoup.connect("https://www.target.com/p/bose-quietcomfort-35-wireless-headphones-ii-android-ios-black/-/A-52791828?preselect=52791828#lnk=sametab").get(); // Tennisracket
                Document doc5 = Jsoup.connect("https://www.target.com/p/sonos-one-wireless-speaker-with-amazon-alexa-voice-assistant/-/A-53293422?preselect=53275937#lnk=sametab").get(); // Baseballbat

                droneWords = doc1.text();
                ps4Words = doc2.text();
                ipadWords = doc3.text();
                boseWords = doc4.text();
                sonosWords = doc5.text();


            }catch(Exception e){e.printStackTrace();}

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // Var price to hold the price of item being compare
            String itemPrice = "";

            // Intialize index for each item
            Integer beginIndex = 0;

            // Intitialize stringbuilders for each item
            StringBuilder sb = new StringBuilder();

            // Calls method to set text = to current price on website
            getPrice(droneWords, itemPrice, dronePriceView);
            getPrice(ps4Words, itemPrice, ps4PriceView);
            getPrice(ipadWords, itemPrice, ipadPriceView);
            getPrice(boseWords, itemPrice, bosePriceView);
            getPrice(sonosWords,  itemPrice, sonosPriceView);

        }
    }

    public void getPrice(String fullText, String price, TextView item)
    {

        StringBuilder sb = new StringBuilder();

        if(fullText.contains("$"))
        {
            Integer beginIndex = fullText.indexOf("$");

            // For loop that appends all chars of Basketball price value
            for(int i = beginIndex; i < beginIndex + 7; i++)
            {
                sb.append(fullText.charAt(i));
            }

            // Adds all chars to string value of basketPrice
            price = sb.toString();
        }

        // Changes text in xml to current price of item
        item.setText(price);

        // Sets sb back to nothing so strings dont stack
        sb.setLength(0);

    }
}




