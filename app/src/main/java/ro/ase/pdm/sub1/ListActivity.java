package ro.ase.pdm.sub1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    ListAdapter adapter;
    List<HomeExchange> list = new ArrayList<>();
    List<HomeExchange> listForCheck = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.lvList);

        list = (List)getIntent().getParcelableArrayListExtra("List");

        adapter = new ListAdapter(this, R.layout.list_item, list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, AdaugaActivity.class);
                intent.putExtra("Obj", (HomeExchange)listView.getItemAtPosition(position));
                intent.putExtra("position", position);
                Log.i("AOLOBA", String.valueOf(position));
                startActivityForResult(intent,2);
            }
        });

        if(list.size()>0){
            checkDatabase();
            for(int i=0;i<listForCheck.size();i++){
                Log.i("database", listForCheck.get(i).toString());
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK){
            int pos = data.getIntExtra("position", 0);
            HomeExchange homeExchange = (HomeExchange)data.getSerializableExtra("Object");
            if(pos >=0 && pos < list.size() && homeExchange!=null){
                list.set(pos,homeExchange);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void checkDatabase(){
        DBHelper helper = new DBHelper(ListActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM homeExchanges", null);

        while(cursor.moveToNext()){
            HomeExchange homeExchange = new HomeExchange();
            int index = cursor.getColumnIndex("adresa");
            homeExchange.setAdresa(cursor.getString(index));
            index = cursor.getColumnIndex("nrCamere");
            homeExchange.setNrCamere(cursor.getInt(index));
            index = cursor.getColumnIndex("suprafata");
            homeExchange.setSuprafata(cursor.getFloat(index));
            index = cursor.getColumnIndex("perioada");
            homeExchange.setPerioada(cursor.getString(index));
            index = cursor.getColumnIndex("tipLocuinta");
            homeExchange.setTipLocuinta(HomeExchange.TipLocuinta.valueOf(cursor.getString(index)));

            listForCheck.add(homeExchange);
        }

        cursor.close();

    }
}
