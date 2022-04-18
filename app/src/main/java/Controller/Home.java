package Controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.homemaplace.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Match;

import View.GSONPaser;
import View.MatchAdapter;


public class Home extends AppCompatActivity implements MatchAdapter.OnMatchListerner, View.OnClickListener {
    RecyclerView rv_match;
    TextView title;
    Button btnAdd,btnValid,btn_editUser;
    ProgressDialog dialog;
    GSONPaser paser=new GSONPaser();
    int status,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /**Get Button*/
            btnAdd=findViewById(R.id.btn_add);
            btn_editUser=findViewById(R.id.btn_editUser);
            btnValid=findViewById(R.id.btn_billetValid);
        /**End*/
        rv_match= findViewById(R.id.rv_match);
        AllMatch allMatch=new AllMatch();
        allMatch.execute();

        title=findViewById(R.id.title_program);
        id=getIntent().getIntExtra("id",0);


        btnAdd.setOnClickListener(this);
        btn_editUser.setOnClickListener(this);
        btnValid.setOnClickListener(this);
    }

    @Override
    public void onClick(Match match) {
        Intent intent = new Intent(this,DetailMatch.class);
        intent.putExtra(DetailMatch.match_key,match);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view==btnAdd){
            Intent openAdd = new Intent(Home.this,AddMatch.class);
            startActivityForResult(openAdd,300);
        }
        else if(view==btn_editUser){
            Intent openPerson = new Intent(Home.this,InfoPerson.class);
            openPerson.putExtra("id",id);
            startActivity(openPerson);
        }
        else if(view==btnValid){
            Intent openBillet = new Intent(Home.this,BilletValid.class);
            startActivityForResult(openBillet,1000);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setMessage("Êtes-vous sûre de vouloir quitté l'application").
                setCancelable(false).
                setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        super.onBackPressed();
    }

    class AllMatch extends AsyncTask<String,String,String> {
         MatchAdapter matchAdapter;
        ArrayList<Match> match=new ArrayList<>();
        protected void onPreExecute() {
            super.onPreExecute();
            dialog= new ProgressDialog(Home.this);
            dialog.setMessage("Chargement de données");
            dialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map=new HashMap<>();
            JSONObject object=paser.makeHttpRequest("http://10.0.2.2/App-mobile/Match/show.php","GET",map);
            try {
                status=object.getInt("status");
                if(status==1){
                    JSONArray matches=object.getJSONArray("Match");
                    for(int i=0;i<matches.length();i++){
                        JSONObject matche=matches.getJSONObject(i);
                        Match m=new Match();
                        m.setId(matche.getString("id"));
                        m.setTeamsA(matche.getString("equipeA"));
                        m.setTeamsB(matche.getString("equipeB"));
                        m.setPlace(matche.getString("lieu"));
                        m.setSquare(matche.getString("place"));
                        m.setMeet(matche.getString("dateRencontre"));
                        match.add(m);
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
            matchAdapter=new MatchAdapter(match,Home.this );
            rv_match.setAdapter(matchAdapter);
            rv_match.setLayoutManager(new LinearLayoutManager(Home.this));

        }
    }
}