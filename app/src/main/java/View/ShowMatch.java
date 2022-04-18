package View;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Match;

public class ShowMatch extends AsyncTask<String,String,String> {
    ProgressDialog dialog;
    GSONPaser paser=new GSONPaser();
    Context contexte;
    MatchAdapter matchAdapter;

    public ShowMatch(Context contexte, ArrayList<Match> match) {
        this.contexte = contexte;
        this.match = match;
    }

    int status;
    ArrayList<Match> match;
    protected void onPreExecute() {
        super.onPreExecute();
        dialog= new ProgressDialog(contexte);
        dialog.setMessage("Chargement de données");
        dialog.show();
    }
    @Override
    protected String doInBackground(String... strings) {
        HashMap<String,String> map=new HashMap<>();
        JSONObject object=paser.makeHttpRequest("http://10.0.2.2/App-mobile/show.php","GET",map);
        try {
            status=object.getInt("status");
            if(status==1){
                JSONArray matches=object.getJSONArray("Webservice/Match");
                for(int i=0;i<matches.length();i++){
                    JSONObject matche=matches.getJSONObject(i);
                    Match m=new Match();
                    m.setId(matche.getString("id"));
                    m.setTeamsA(matche.getString("equipeA"));
                    m.setTeamsB(matche.getString("equipeB"));
                    m.setPlace(matche.getString("lieu"));
                    m.setSquare(matche.getString("place"));
                    m.setMeet(matche.getString("dateRencontre"));
                    this.match.add(m);
                }
            }else {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.cancel();


    }
}
