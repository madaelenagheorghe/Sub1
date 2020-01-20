package ro.ase.pdm.sub1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<HomeExchange> bigList = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private static DateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    Button btnDespre, btnAdauga, btnLista, btnPreluare, btnCetralizator;
    JSONOp jsonOp = new JSONOp();

    String linkToJson = "https://api.myjson.com/bins/197gjk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("App_Preferences", MODE_PRIVATE);

        btnDespre = findViewById(R.id.buttonAbout);
        btnAdauga = findViewById(R.id.buttonAdd);
        btnLista = findViewById(R.id.buttonList);
        btnPreluare = findViewById(R.id.buttonPreluare);
        btnCetralizator = findViewById(R.id.buttonCentralizator);

        saveLoginInfo();

        btnDespre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DespreActivity.class);
                intent.putExtra("DataValue", sharedPreferences.getString("LoginDate", "lol"));
                startActivity(intent);
            }
        });

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdaugaActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putParcelableArrayListExtra("List",(ArrayList)bigList);
                startActivity(intent);
            }
        });

        btnPreluare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runThread();
            }
        });

        btnCetralizator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, String.valueOf(bigList.size()), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1 && resultCode == RESULT_OK){
            HomeExchange homeExchange = (HomeExchange)data.getSerializableExtra("Object");
            bigList.add(homeExchange);
        }
    }

    private void saveLoginInfo() {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Date date = new Date();

        editor.putString("LoginDate", sdf.format(date));

        editor.apply();
    }

    protected  void connectToURL(String link) {
    try{
        URL url = new URL(link);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder stringBuilder = new StringBuilder();
            String linie;
            while((linie = br.readLine())!=null){
                stringBuilder.append(linie);
            }
            in.close();

            String str = stringBuilder.toString();


            int listSize = jsonOp.getSize(str);

            for(int i=0; i<listSize;i++){
                HomeExchange homeExchange = new HomeExchange();
                homeExchange = jsonOp.getObjectFromList(str, homeExchange,i);
                bigList.add(homeExchange);
            }

        }
        finally {
            urlConnection.disconnect();
        }

    }
    catch (Exception ex){
        ex.printStackTrace();
    }
    }

    protected  void runThread(){
        new Thread(){
            @Override
            public void run() {
                connectToURL(linkToJson);
            }
        }.start();
    }


}
