package Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homemaplace.R;

import Webservice.Match.BgAddMatch;

public class AddMatch extends AppCompatActivity implements View.OnClickListener {
    TextView title;
    EditText teamsA,teamsB,place,meet,square;
    Button valid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        title=findViewById(R.id.tv_titleAddMatch);
        /**Get EditText*/
            teamsA=findViewById(R.id.ed_AdTeamsA);
            teamsB=findViewById(R.id.ed_AdTeamsB);
            place=findViewById(R.id.ed_AdPlce);
            meet=findViewById(R.id.ed_AdDate);
            square=findViewById(R.id.ed_AdSquare);
        /**End*/
        /**Get Button*/
            valid=findViewById(R.id.btn_addMatch);
        /**End*/
        valid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==valid){

            if(teamsA.getText().toString().equals("") || teamsB.getText().toString().equals("")){
                    title.setText("Equipe");
                //Toast.makeText(this, "Veuillez entrer toutes les équipes", Toast.LENGTH_SHORT).show();
            }
            else if(place.getText().toString().equals("") || meet.getText().toString().equals("")){
                title.setText("Infor");
                //Toast.makeText(this, "Veuillez entrer les infos de rencontre", Toast.LENGTH_SHORT).show();
            }
            else if(square.getText().toString().equals("")){
                title.setText("Place");
                //Toast.makeText(this, "Veuillez entrer le nombre de place", Toast.LENGTH_SHORT).show();
            }
            else {
                String teams1=teamsA.getText().toString();
                String teams2=teamsB.getText().toString();
                String lieu=place.getText().toString();
                String date=meet.getText().toString();
                String place=square.getText().toString();

                BgAddMatch addMatch= new BgAddMatch(this);
                addMatch.execute(teams1,teams2,lieu,date,place);
                clearEditText();
            }


        }
    }
    private void clearEditText(){
        teamsA.setText("");
        teamsB.setText("");
        place.setText("");
        meet.setText("");
        square.setText("");
    }
}