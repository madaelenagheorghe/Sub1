package ro.ase.pdm.sub1;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdaugaActivity extends AppCompatActivity {


    EditText etAdresa, etNr, etSuprafata, etPerioada;
    Spinner spTipLocuinta;
    List<String> tipLocuinta = new ArrayList<>();

    Button btnSalveaza;

    ArrayAdapter<String> adapter;
    DBHelper dbHelper;
    List<HomeExchange> list = new ArrayList<>();

    Intent intent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga);

        initSpinner();

        etAdresa = findViewById(R.id.etAdresaValue);
        etNr = findViewById(R.id.etNrCamereValue);
        etSuprafata = findViewById(R.id.etSuprafataValue);
        etPerioada = findViewById(R.id.etPerioadaValue);
        btnSalveaza = findViewById(R.id.btnSalveaza);

        spTipLocuinta = findViewById(R.id.spinnerLocuinta);

        adapter = new ArrayAdapter<>(AdaugaActivity.this, R.layout.spinner_layout, tipLocuinta);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spTipLocuinta.setAdapter(adapter);

        dbHelper = new DBHelper(AdaugaActivity.this);

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validare();
            }
        });


        HomeExchange homeExchange = (HomeExchange)getIntent().getSerializableExtra("Obj");
        if(homeExchange!=null){
            int pos = getIntent().getIntExtra("position", 0);
            etAdresa.setText(homeExchange.getAdresa());
            etNr.setText(String.valueOf(homeExchange.getNrCamere()));
            etSuprafata.setText(String.valueOf(homeExchange.getSuprafata()));
            etPerioada.setText(homeExchange.getPerioada());
            spTipLocuinta.setSelection(homeExchange.getTipLocuinta().ordinal());
            intent.putExtra("position", pos);
        }



    }

    private void validare(){
        if(!"".equals(etAdresa.getText().toString()) && !"".equals(etPerioada.getText().toString())
                && Integer.parseInt(etNr.getText().toString())>0
                && Float.parseFloat(etSuprafata.getText().toString())>0){

            String adresa = etAdresa.getText().toString();
            int nr = Integer.parseInt(etNr.getText().toString());
            float suprafata = Float.parseFloat(etSuprafata.getText().toString());
            String perioada = etPerioada.getText().toString();

            HomeExchange homeExchange = new HomeExchange();

            homeExchange.setAdresa(adresa);
            homeExchange.setNrCamere(nr);
            homeExchange.setSuprafata(suprafata);
            homeExchange.setPerioada(perioada);
            homeExchange.setTipLocuinta(HomeExchange.TipLocuinta.valueOf
                    (spTipLocuinta.getSelectedItem().toString()));

            dbHelper.insert(homeExchange);
            intent.putExtra("Object", homeExchange);
            setResult(RESULT_OK,intent);
            finish();


        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(AdaugaActivity.this);
            builder.setTitle("Date eronate!");
            builder.setMessage("Verificati corectitudinea datelor");
            builder.setPositiveButton("OK",null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void initSpinner(){

        tipLocuinta.add("Casa");
        tipLocuinta.add("Apartament");

    }
}
