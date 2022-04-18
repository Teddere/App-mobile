package Webservice.Match;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import Controller.Home;
import View.GSONPaser;


public class DeleteMatch extends AsyncTask<String,Void,String> {
    ProgressDialog dialog;
    Context context;
    GSONPaser paser= new GSONPaser();
    int status=0,index;
    String message;

    public DeleteMatch(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog= new ProgressDialog(context);
        dialog.setMessage("Patientez s'il vous plaît !");
        dialog.show();
    }

    @Override
    protected String doInBackground(String[] params) {
        String id=params[0];
        index=Integer.parseInt(id);
        HashMap<String, String> map= new HashMap<>();
        map.put("id",id);

        JSONObject object=paser.makeHttpRequest("http://10.0.2.2/App-mobile/Match/deleteMatch.php","POST",map);

        try {
            status=object.getInt("status");
            message=object.getString("msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        dialog.setMessage(message);
        dialog.show();
        dialog.cancel();
        if(status==1){
            Intent openHome= new Intent(context, Home.class);
            openHome.putExtra("id",index);
            context.startActivity(openHome);
        }

    }
}
