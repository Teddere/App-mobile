package Controller;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homemaplace.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import View.GSONPaser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText login,password;
    Button btnLogin,btnCreate;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**EditText interface authentication*/
        title=(TextView) findViewById(R.id.titleLogin);
        login=(EditText) findViewById(R.id.et_username);
        password=(EditText) findViewById(R.id.et_password);
        /**Button interface authentication */
        btnLogin=(Button) findViewById(R.id.btn_login);
        btnCreate=(Button) findViewById(R.id.btn_create);
        /** code processing*/
        btnLogin.setOnClickListener(this);
        btnCreate.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==btnLogin){
            if(login.getText().toString().equals("") && password.getText().toString().equals("")){
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }else {
                    Login login = new Login();
                    login.execute(this.login.getText().toString(),password.getText().toString());
            }

        }
        if(view==btnCreate) {
            Intent openAccount=new Intent(MainActivity.this,Creation_Account.class);
            startActivityForResult(openAccount,200);
        }
    }
    class Login extends AsyncTask<String,Void,String> {
        ProgressDialog dialog;
        GSONPaser paser= new GSONPaser();
        int status=0,id;
        String message;

        @Override
        protected void onPreExecute() {
            dialog= new ProgressDialog(MainActivity.this);
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
            JSONObject object=paser.makeHttpRequest("http://10.0.2.2/App-mobile/Users/login.php","POST",map);
            try {
                status=object.getInt("status");
                message=object.getString("msg");
                id=object.getInt("id");
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
                Intent open= new Intent(MainActivity.this, Home.class);
                open.putExtra("id",id);
                MainActivity.this.startActivity(open);
            }else{
                dialog.setMessage(message);
                dialog.show();
                dialog.cancel();
                Toast.makeText(MainActivity.this, "Authentification échouée", Toast.LENGTH_SHORT).show();
            }
        }

    }

}