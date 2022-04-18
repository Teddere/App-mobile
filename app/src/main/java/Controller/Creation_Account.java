package Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.homemaplace.R;

import Webservice.Users.CreateBackground;


public class Creation_Account extends AppCompatActivity implements View.OnClickListener {
    EditText nom,email,pwd,pwd2,prenom;
    Button btnCrreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_account);

        prenom=findViewById(R.id.et_creationPrenom);
        nom=findViewById(R.id.et_createNom);
        email=findViewById(R.id.et_createEmail);
        pwd=findViewById(R.id.et_createPwd);
        pwd2=findViewById(R.id.et_createConfirm);
        btnCrreate=findViewById(R.id.btnCreateAccount);

        btnCrreate.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if(view==btnCrreate){
            String fname=prenom.getText().toString();
            String lname=nom.getText().toString();
            String mail=email.getText().toString();
            String pass=pwd.getText().toString();
            String pass2=pwd2.getText().toString();

            CreateBackground bg=new CreateBackground(this);
            bg.execute(lname,fname,mail,pass);

        }
    }
}