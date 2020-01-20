package ro.ase.pdm.sub1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String dbName = "database.db";

    public DBHelper(Context context){
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE homeExchanges(adresa TEXT PRIMARY KEY, nrCamere INTEGER, suprafata REAL, perioada TEXT, tipLocuinta TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(HomeExchange homeExchange){
        ContentValues values = new ContentValues();
        values.put("adresa", homeExchange.getAdresa());
        values.put("nrCamere", homeExchange.getNrCamere());
        values.put("suprafata", homeExchange.getSuprafata());
        values.put("perioada", homeExchange.getPerioada());
        values.put("tipLocuinta", homeExchange.getTipLocuinta().toString());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("homeExchanges", null, values);
        db.close();
        Log.d("DataBase", "Inserted into database.");
    }
}
