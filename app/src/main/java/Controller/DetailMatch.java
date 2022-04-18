package Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homemaplace.R;

import Model.Match;
import Webservice.Match.DeleteMatch;
import Webservice.Match.EditMatch;


public class DetailMatch extends AppCompatActivity implements View.OnClickListener {
    public final static  String match_key="";
    TextView title,equipeA,equipeB,date,place;
    EditText teamsA,teamsB,edPlace,meet,square;
    LinearLayout form;
    Button edit,delete,valid;
    private Match detailMatch;
    private Match editMatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_match);
        /**get TextView*/
        title=findViewById(R.id.tv_dTitle);
        equipeA=findViewById(R.id.tv_equipeA);
        equipeB=findViewById(R.id.tv_equipeB);
        date=findViewById(R.id.tv_dateR);
        place=findViewById(R.id.place);
        /**End*/
        /**Get Button*/
            edit=findViewById(R.id.btn_edit);
            delete=findViewById(R.id.btn_del);
            valid=findViewById(R.id.btn_editMatch);
        /**End*/
        /**LinearLayout*/
            form= findViewById(R.id.editForm);
            form.setVisibility(View.INVISIBLE);
        /**End*/

        Match match = (Match) getIntent().getExtras().getSerializable(match_key);
        detailMatch=match;
        /**Set Data*/
            title.setText(detailMatch.getPlace());
            equipeA.setText(detailMatch.getTeamsA());
            equipeB.setText(detailMatch.getTeamsB());
            date.setText(detailMatch.getMeet());
            place.setText(detailMatch.getSquare());
        /**End*/
        teamsA=findViewById(R.id.ed_equipeA);
        teamsB=findViewById(R.id.ed_equipeB);
        edPlace=findViewById(R.id.ed_lieu);
        meet=findViewById(R.id.td_dateR);
        square=findViewById(R.id.ed_place);
        /**Press btn*/
        edit.setOnClickListener(this);
        delete.setOnClickListener(this);
        valid.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==edit){
            form.setVisibility(View.VISIBLE);
        }

        else if(view==delete){
            DeleteMatch deleteMatch= new DeleteMatch(DetailMatch.this);
            deleteMatch.execute(detailMatch.getId());

        }
        else if(view==valid){
            String title="Modification";
            String message="Voulez-vous vraiment modifier cette rencontre ?";

            String id=this.detailMatch.getId();
            String equipA=teamsA.getText().toString();
            String equipB=teamsB.getText().toString();
            String lieu=edPlace.getText().toString();
            String date=meet.getText().toString();
            String pla=square.getText().toString();
          EditMatch editMatch= new EditMatch(DetailMatch.this);
           editMatch.execute(id,equipA,equipB,lieu,date,pla);



        }
    }


    private int controlInput(){
        int result=0;
        if(teamsA.getText().toString().equals("") || teamsB.getText().toString().equals("")){
            Toast.makeText(this, "Veuillez entrer toutes équiles", Toast.LENGTH_SHORT).show();
        }
        else if(edPlace.getText().toString().equals("") || meet.getText().toString().equals("")){
            Toast.makeText(this, "Veuillez entrer les informations de rencontre", Toast.LENGTH_SHORT).show();
        }
        else if(square.getText().toString().equals("")){
            Toast.makeText(this, "Veuillez entrer le nombre de place", Toast.LENGTH_SHORT).show();
        }else {
            result = 1;
        }

        return  result;
    }

}