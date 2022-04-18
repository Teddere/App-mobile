package Webservice.Match;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import View.GSONPaser;


public class BgAddMatch extends AsyncTask<String,Void,String> {
    Context context;
    ProgressDialog dialog;
    GSONPaser paser= new GSONPaser();
    String message;
    int status;

    public BgAddMatch(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog= new ProgressDialog(context);
        dialog.setMessage("Patientez s'il vous plaît !");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String teamsA=params[0];
        String teamsB=params[1];
        String place=params[2];
        String meet=params[3];
        String square=params[4];

        HashMap<String, String> map= new HashMap<>();

        map.put("teamsA",teamsA);
        map.put("teamsB",teamsB);
        map.put("place",place);
        map.put("meet",meet);
        map.put("square",square);
        JSONObject object=paser.makeHttpRequest("http://10.0.2.2/App-mobile/Match/AddMatch.php","POST",map);

        try {
            status=object.getInt("status");
            message=object.getString("msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage(message);
        dialog.show();
        dialog.cancel();
    }
}
