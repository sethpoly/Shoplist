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

public class OfficeSuppliesActivity extends AppCompatActivity {


    Button staplerPriceView;
    Button pencilPriceView;
    Button notebookPriceView;
    Button folderPriceView;
    Button highlighterPriceView;

    String staplerSite = "https://www.target.com/p/marble-stapler-threshold-153/-/A-51931291";
    String pencilSite = "https://www.target.com/p/bic-174-2-xtra-life-mechanical-pencils-0-7mm-40ct-multicolor/-/A-50516598";
    String notebookSite = "https://www.target.com/p/yoobi-153-spiral-notebook-1-subject-college-ruled/-/A-51291772?preselect=51548939#lnk=sametab";
    String folderSite = "https://www.target.com/p/yoobi-153-pronged-2-pocket-plastic-folder/-/A-51291773?preselect=51549954#lnk=sametab";
    String highlighterSite = "https://www.target.com/p/sharpie-174-clear-view-174-highlighter-6ct-multicolor/-/A-51912582";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_supplies);

        staplerPriceView = (Button) findViewById(R.id.btnStaplerPrice);
        pencilPriceView = (Button) findViewById(R.id.btnPencilPrice);
        notebookPriceView = (Button) findViewById(R.id.btnNotebookPrice);
        folderPriceView = (Button) findViewById(R.id.btnFolderPrice);
        highlighterPriceView = (Button) findViewById(R.id.btnHighlighterPrice);


        new doIt().execute();

        staplerPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(staplerSite)));
            }
        });

        pencilPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pencilSite)));
            }
        });

        notebookPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(notebookSite)));
            }
        });

        folderPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(folderSite)));
            }
        });

        highlighterPriceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(highlighterSite)));
            }
        });


    }

    public void openStapler(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(staplerSite)));
    }
    public void openPencil(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pencilSite)));
    }
    public void openNotebook(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(notebookSite)));
    }
    public void openFolder(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(folderSite)));
    }
    public void openHighlighter(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(highlighterSite)));
    }



    public class doIt extends AsyncTask<Void,Void,Void>
    {
        String staplerWords;
        String pencilWords;
        String notebookWords;
        String folderWords;
        String highlighterWords;

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc1 = Jsoup.connect("https://www.target.com/p/marble-stapler-threshold-153/-/A-51931291").get();        // Basketball
                Document doc2 = Jsoup.connect("https://www.target.com/p/bic-174-2-xtra-life-mechanical-pencils-0-7mm-40ct-multicolor/-/A-50516598").get();    // Baseball
                Document doc3 = Jsoup.connect("https://www.target.com/p/yoobi-153-spiral-notebook-1-subject-college-ruled/-/A-51291772?preselect=51548939#lnk=sametab").get();    // Tennisballs
                Document doc4 = Jsoup.connect("https://www.target.com/p/yoobi-153-pronged-2-pocket-plastic-folder/-/A-51291773?preselect=51549954#lnk=sametab").get();       // Tennisracket
                Document doc5 = Jsoup.connect("https://www.target.com/p/sharpie-174-clear-view-174-highlighter-6ct-multicolor/-/A-51912582").get(); // Baseballbat

                staplerWords = doc1.text();
                pencilWords = doc2.text();
                notebookWords = doc3.text();
                folderWords = doc4.text();
                highlighterWords = doc5.text();


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
            getPrice(staplerWords, itemPrice, staplerPriceView);
            getPrice(pencilWords, itemPrice, pencilPriceView);
            getPrice(notebookWords, itemPrice, notebookPriceView);
            getPrice(folderWords, itemPrice, folderPriceView);
            getPrice(highlighterWords,  itemPrice, highlighterPriceView);

        }
    }

    public void getPrice(String fullText, String price, TextView item)
    {

        StringBuilder sb = new StringBuilder();

        if(fullText.contains("$"))
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




