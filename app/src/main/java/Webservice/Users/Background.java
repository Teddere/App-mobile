package Webservice.Users;

import android.app.ProgressDialog;
import android.content.Context;

import android.os.AsyncTask;


import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;

import Model.Match;
import View.GSONPaser;


public class Background extends AsyncTask<String,Void,String> {
    Context context;
    ArrayList<Match> match;
    GSONPaser paser= new GSONPaser();

    public Background(Context context, ArrayList<Match> match) {
        this.context = context;
        this.match = match;
    }

    @Override
    protected String doInBackground(String... params) {
        String mail=params[0];

        HashMap<String, String> map= new HashMap<>();
        map.put("selector",mail);
        JSONObject object=paser.makeHttpRequest("http://10.0.2.2/App-mobile/Match/show.php","GET",map);

        return null;
    }
    //Mehode éxecute avant
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    //Après l'exécution de doBackground il s'éxecute ce programme
    @Override
    protected void onPostExecute(String result) {
        /*dialog.cancel();
        if(status==1){
            AlertDialog.Builder alert= new AlertDialog.Builder(context);
            alert.setMessage(message);
            alert.setNeutralButton("Valider",null);
            alert.show();

        }else {
            AlertDialog.Builder alert= new AlertDialog.Builder(context);
            alert.setMessage(message);
            alert.setNeutralButton("valider",null);
            alert.show();
        }*/

    }


}

