package ro.ase.pdm.sub1;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONOp {

    public  JSONOp(){

    }

    public int getSize(String str){
        int nr = 0;
        try{
            JSONObject json = new JSONObject(str);
            nr = json.getInt("numarObiecte");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return  nr;
    }

    public HomeExchange getObject(JSONObject obj, HomeExchange homeExchange){
        try {
            homeExchange.setAdresa(obj.getString("adresa"));
            homeExchange.setNrCamere(obj.getInt("nrCamere"));
            homeExchange.setSuprafata(Float.parseFloat(obj.getString("suprafata")));
            homeExchange.setPerioada(obj.getString("perioada"));
            homeExchange.setTipLocuinta(HomeExchange.TipLocuinta.valueOf
                    (obj.getString("tipLocuinta")));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return homeExchange;
    }

    public HomeExchange getObjectFromList(String str, HomeExchange homeExchange, int position){
        try{
            JSONObject json = new JSONObject(str);
            JSONArray jsonArray = json.getJSONArray("list");
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            homeExchange = getObject(jsonObject, homeExchange);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return homeExchange;
    }



}
