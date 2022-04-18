package Webservice.Users;

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

public class LoginBackground extends AsyncTask<String,Void,String> {
    Context context;
    ProgressDialog dialog;
    GSONPaser paser= new GSONPaser();
    int status=0;
    String message,id;

    public LoginBackground(Context context) {
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
        String mail=params[0];
        String password=params[1];
        HashMap<String, String> map= new HashMap<>();
        map.put("mail",mail);
        map.put("password",password);
        JSONObject object=paser.makeHttpRequest("http://10.0.2.2/App-mobile/show.php","POST",map);
        try {
            status=object.getInt("status");
            message=object.getString("msg");
            id=object.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if(status==1){
            dialog.setMessage(message);
            dialog.show();
            dialog.cancel();
            Intent open= new Intent(context, Home.class);
            open.putExtra("id",id);
            context.startActivity(open);
        }else{
            dialog.setMessage(message);
            dialog.show();
            dialog.cancel();
            Toast.makeText(context, "Authentification échouée", Toast.LENGTH_SHORT).show();
        }
    }

}
