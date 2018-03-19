package e.codexp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String mentor = intent.getExtras().getString("mentor");
        String mentorado = intent.getExtras().getString("mentorado");
        Button cadastrar = (Button) findViewById(R.id.btcadastrar);
        if(mentor != null)
            setTitle("Cadastrar-se como mentor");
         if(mentorado != null)
             setTitle("Cadastrar-se como mentorado");

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it4 = new Intent(SignUp.this, Profile.class);
                startActivity(it4);
    }

});
    }}