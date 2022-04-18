package Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.homemaplace.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import View.GSONPaser;
import Webservice.Users.DeleteUser;
import Webservice.Users.EditUser;

public class InfoPerson extends AppCompatActivity implements View.OnClickListener {
    TextView tv_lname,tv_fname,tv_email;
    ScrollView sv_person;
    EditText ed_lname,ed_fname,ed_email,ed_password;
    Button btn_edit,btn_delete,btn_valid;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_person);
        id=getIntent().getIntExtra("id",0);


        tv_fname=findViewById(R.id.tv_fname);
        tv_lname=findViewById(R.id.tv_lname);
        tv_email=findViewById(R.id.tv_email);
        /**Get ScrollView*/
        sv_person=findViewById(R.id.sv_person);
        /**Get editText*/
        ed_fname=findViewById(R.id.ed_fname);
        ed_lname=findViewById(R.id.ed_lname);
        ed_email=findViewById(R.id.ed_email);
        ed_password=findViewById(R.id.ed_password);
        /**Get Button*/
        btn_delete=findViewById(R.id.btn_delPerson);
        btn_edit=findViewById(R.id.btn_editPerson);
        btn_valid=findViewById(R.id.btn_validPerson);
        ShowUser user = new ShowUser();
        String id=""+this.id;
        user.execute(id);

        btn_edit.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_valid.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==btn_edit){
            ed_fname.setText(tv_fname.getText());
            ed_lname.setText(tv_lname.getText());
            ed_email.setText(tv_email.getText());
            sv_person.setVisibility(View.VISIBLE);
        }
        else if(view==btn_valid){
            String id=""+this.id;
            String nom=ed_lname.getText().toString();
            String prenom=ed_fname.getText().toString();
            String email= ed_email.getText().toString();
            String pass=ed_password.getText().toString();

            EditUser edit = new EditUser(this);
            edit.execute(id,nom,prenom,email,pass);
        }
        else if(view==btn_delete){
            String id=""+this.id;
            DeleteUser delete=new DeleteUser(this);
            delete.execute(id);

            finish();

        }

    }

    class ShowUser extends AsyncTask<String,Void,String> {
        GSONPaser paser= new GSONPaser();
        int status;
        String fname,lname,email;

        @Override
        protected String doInBackground(String... integers) {
            String id=integers[0];
            HashMap<String, String> map= new HashMap<>();
            map.put("id",id);
            JSONObject object=paser.makeHttpRequest("http://10.0.2.2/App-mobile/Users/show.php","POST",map);

            try {
                status=object.getInt("status");
                fname=object.getString("prenom");
                lname=object.getString("nom");
                email=object.getString("email");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

       @Override
       protected void onPostExecute(String s) {
           super.onPostExecute(s);
           tv_fname.setText(fname);
           tv_lname.setText(lname);
           tv_email.setText(email);
       }
   }
}