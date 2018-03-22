package codexp.br.senai.sp.quick_mentoring_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import codexp.br.senai.sp.quick_mentoring_mobile.views.CadastroUsuarioActivity;
import codexp.br.senai.sp.quick_mentoring_mobile.views.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private Button btCadastreseMentor;
    private Button btCadastreseMentorado;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadastreseMentor = findViewById(R.id.btCadastreseMentor);
        btCadastreseMentorado = findViewById(R.id.btCadastreseMentorado);
        btLogin = findViewById(R.id.btLogin);

        btCadastreseMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastrarMentor = new Intent(MainActivity.this, CadastroUsuarioActivity.class);
                cadastrarMentor.putExtra("perfil", "Mentor");
                startActivity(cadastrarMentor);
            }
        });

        btCadastreseMentorado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastrarMentorado = new Intent(MainActivity.this, CadastroUsuarioActivity.class);
                cadastrarMentorado.putExtra("perfil", "Mentorado");
                startActivity(cadastrarMentorado);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fazerLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(fazerLogin);
            }
        });

    }
}
