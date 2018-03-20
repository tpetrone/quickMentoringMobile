package e.codexp.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import e.codexp.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btLogin;
    private Button btCadastroMentor;
    private Button btCadastroMentorado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadastroMentor = findViewById(R.id.btcadmentor);
        btCadastroMentorado = findViewById(R.id.btcadmentorado);
        btLogin = findViewById(R.id.btconta);

        btCadastroMentorado.setOnClickListener(this);
        btCadastroMentor.setOnClickListener(this);
        btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btLogin) {
            Intent it3 = new Intent(MainActivity.this, Login.class);
            startActivity(it3);
        } else if(v == btCadastroMentor) {
            Intent it = new Intent(MainActivity.this, SignUp.class);
            it.putExtra("role", "mentor");
            startActivity(it);
        } else if(v == btCadastroMentorado) {
            Intent it = new Intent(MainActivity.this, SignUp.class);
            it.putExtra("role", "mentorado");
            startActivity(it);
        }
    }
}
