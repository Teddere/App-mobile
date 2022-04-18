package Webservice.Users;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import Controller.Home;

public class EditUser extends AsyncTask<String,Void,String> {
    ProgressDialog dialog;
    Context context;

    public EditUser(Context context) {
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
        String result="";
        String id=params[0];
        String nom=params[1];
        String prenom=params[2];
        String email=params[3];
        String password=params[4];
        String urlConnect="http://10.0.2.2/App-mobile/Users/editUsers.php";
        try {
            URL url=new URL(urlConnect);
            HttpURLConnection http=(HttpURLConnection)  url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            OutputStream ops=http.getOutputStream();
            BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+
                    "&&"+URLEncoder.encode("nom","UTF-8")+"="+URLEncoder.encode(nom,"UTF-8")+
                    "&&"+URLEncoder.encode("prenom","UTF-8")+"="+URLEncoder.encode(prenom,"UTF-8")+
                    "&&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+
                    "&&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            InputStream ips=http.getInputStream();
            BufferedReader reader= new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line="";
            while ((line= reader.readLine()) !=null)
            {
                result +=line;
            }
            reader.close();
            ips.close();
            http.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        dialog.setMessage(result);
        dialog.show();
        dialog.cancel();
        if(result.contains("reussie")){
            Intent openHome=new Intent(context, Home.class);
            context.startActivity(openHome);
        }
    }
}
