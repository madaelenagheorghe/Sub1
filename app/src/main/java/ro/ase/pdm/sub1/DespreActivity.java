package ro.ase.pdm.sub1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DespreActivity extends AppCompatActivity {

    TextView dataValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despre);

        dataValue = findViewById(R.id.tvDataValue);

        dataValue.setText(getIntent().getExtras().getString("DataValue"));


    }
}
