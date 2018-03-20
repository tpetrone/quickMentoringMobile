package e.codexp.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import e.codexp.myapplication.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btLogin = findViewById(R.id.btlogin);
        btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(Login.this, CriarMentoria.class);
        startActivity(it);
    }
}
