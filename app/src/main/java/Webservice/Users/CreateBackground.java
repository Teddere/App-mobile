package Webservice.Users;
import android.app.AlertDialog;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import Controller.Home;
import Controller.MainActivity;

public class CreateBackground extends AsyncTask<String,Void,String>{
    AlertDialog alert;
    Context context;
    public CreateBackground(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        alert= new AlertDialog.Builder(context).create();
        alert.setTitle("Création de compte");
    }

    @Override
    protected String doInBackground(String... params) {
        String result="";
        String nom=params[0];
        String prenom=params[1];
        String mail=params[2];
        String password=params[3];
        String urlConnect="http://10.0.2.2/App-mobile/Users/create.php";

        try {
            URL url=new URL(urlConnect);
            //Connexion
            HttpURLConnection http=(HttpURLConnection)  url.openConnection();
            //Méthode utlisée
            http.setRequestMethod("POST");
            //J'envoie des donnés
            http.setDoInput(true);
            //Je reçois les données
            http.setDoOutput(true);
            //Un flux d'envoie des données en sortie
            OutputStream ops=http.getOutputStream();
            //Zone de mémoire
            BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            //La saisie des données
            String data= URLEncoder.encode("mail","UTF-8")+"="+URLEncoder.encode(mail,"UTF-8")+
                    "&&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+
                    "&&"+URLEncoder.encode("nom","UTF-8")+"="+URLEncoder.encode(nom,"UTF-8")+
                    "&&"+URLEncoder.encode("prenom","UTF-8")+"="+URLEncoder.encode(prenom,"UTF-8");
            //Insertion des données dans la zone mémoire
            writer.write(data);
            //Transfert les données dans qui se trouve dans le buffer
            writer.flush();
            //Fermeture de la zone mémoire
            writer.close();
            //Ouverture du canal d'entrer ou
            InputStream ips=http.getInputStream();
            //un flex d'entré retourne de mon php
            BufferedReader reader= new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));

            //Récupération de chaque ligne
            String line="";
            while ((line= reader.readLine()) !=null)
            {
                result +=line;
            }
            //Fermer le lecteur de données
            reader.close();
            //Fermer le flux de données
            ips.close();
            //Déconnexion
            http.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Valeur rétournée
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        alert.setMessage(result);
        alert.show();
        if(result.contains("reussie")){
            Intent open= new Intent(context, MainActivity.class);
            context.startActivity(open);
        }
    }
}
