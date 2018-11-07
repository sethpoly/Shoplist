package edu.ccm.polyniakseth.shoplist;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Text;

import static edu.ccm.polyniakseth.shoplist.R.id.baseball;
import static edu.ccm.polyniakseth.shoplist.R.id.baseballbatPrice;
import static edu.ccm.polyniakseth.shoplist.R.id.basketballPrice;
import static edu.ccm.polyniakseth.shoplist.R.id.tennisballPrice;
import static edu.ccm.polyniakseth.shoplist.R.id.tennisracketPrice;

public class SportsActivity extends AppCompatActivity {


    Button basketballPrice;
    Button baseballPriceView;
    Button tennisballPriceView;
    Button tennisracketPriceView;
    Button baseballbatPriceView;

    String basketballSite = "https://www.target.com/p/spalding-street-29-5-basketball/-/A-10578742";
    String baseballSite = "https://www.target.com/p/mlb-rawlings-team-logo-baseball/-/A-52957321?preselect=52900927#lnk=sametab";
    String tennisballSite = "https://www.target.com/p/penn-tennis-ball-champ-3pk/-/A-14769449";
    String tennisracketSite = "https://www.target.com/p/wilson-triumph-tennis-racket-size-2/-/A-13405082";
    String baseballbatSite = "https://www.target.com/p/rawlings-5150-alloy-adult-baseball-bat-2019-3/-/A-75551440?preselect=75472162#lnk=sametab";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        basketballPrice = (Button) findViewById(R.id.basketballPrice);
        baseballPriceView = (Button) findViewById(R.id.baseballPrice);
        tennisballPriceView = (Button) findViewById(R.id.tennisballPrice);
        tennisracketPriceView = (Button) findViewById(R.id.tennisracketPrice);
        baseballbatPriceView = (Button) findViewById(R.id.baseballbatPrice);


        new doIt().execute();

        basketballPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(basketballSite)));
            }
        });

        baseballPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(baseballSite)));
            }
        });

        tennisballPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tennisballSite)));
            }
        });

        tennisracketPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tennisracketSite)));
            }
        });

        baseballbatPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(baseballbatSite)));
            }
        });


    }

    public void openBasketball(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.target.com/p/spalding-street-29-5-basketball/-/A-10578742")));
    }
    public void openBaseball(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.target.com/p/mlb-rawlings-team-logo-baseball/-/A-52957321?preselect=52900927#lnk=sametab")));
    }
    public void openTennisball(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.target.com/p/penn-tennis-ball-champ-3pk/-/A-14769449")));
    }
    public void openTennisracket(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.target.com/p/wilson-triumph-tennis-racket-size-2/-/A-13405082")));
    }
    public void openBaseballbat(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.target.com/p/rawlings-5150-alloy-adult-baseball-bat-2019-3/-/A-75551440?preselect=75472162#lnk=sametab")));
    }



    public class doIt extends AsyncTask<Void,Void,Void>
    {
        String basketWords;
        String baseballWords;
        String tennisballWords;
        String tennisRacketWords;
        String baseballbatWords;

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc1 = Jsoup.connect("https://www.target.com/p/spalding-street-29-5-basketball/-/A-10578742").get();        // Basketball
                Document doc2 = Jsoup.connect("https://www.target.com/p/mlb-rawlings-team-logo-baseball/-/A-52957321?preselect=52900927#lnk=sametab").get();    // Baseball
                Document doc3 = Jsoup.connect("https://www.target.com/p/penn-tennis-ball-champ-3pk/-/A-14769449").get();    // Tennisballs
                Document doc4 = Jsoup.connect("https://www.target.com/p/wilson-triumph-tennis-racket-size-2/-/A-13405082").get(); // Tennisracket
                Document doc5 = Jsoup.connect("https://www.target.com/p/rawlings-5150-alloy-adult-baseball-bat-2019-3/-/A-75551440?preselect=75472162#lnk=sametab").get(); // Baseballbat

                basketWords = doc1.text();
                baseballWords = doc2.text();
                tennisballWords = doc3.text();
                tennisRacketWords = doc4.text();
                baseballbatWords = doc5.text();


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
            getPrice(basketWords, itemPrice, basketballPrice);
            getPrice(baseballWords, itemPrice, baseballPriceView);
            getPrice(tennisballWords, itemPrice, tennisballPriceView);
            getPrice(tennisRacketWords, itemPrice, tennisracketPriceView);
            getPrice(baseballbatWords,  itemPrice, baseballbatPriceView);


        }
    }

    public void getPrice(String fullText, String price, TextView item)
    {

        StringBuilder sb = new StringBuilder();

        if(fullText != null && fullText.contains("$"))
        {
            Integer beginIndex = fullText.indexOf("$");

            // For loop that appends all chars of Basketball price value
            for(int i = beginIndex; i < beginIndex + 6; i++)
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




