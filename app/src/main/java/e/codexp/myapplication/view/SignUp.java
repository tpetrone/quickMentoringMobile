package e.codexp.myapplication.view;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.widget.Button;

        import e.codexp.myapplication.R;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btCadastrar;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        role = intent.getExtras().getString("role");

        btCadastrar = findViewById(R.id.btcadastrar);
        btCadastrar.setOnClickListener(this);

        if (role.equals("mentor")) {
            this.setTitle("Cadastrar-se como mentor");
        } else if (role.equals("mentorado")) {
            setTitle("Cadastrar-se como mentorado");
        }
    }

    @Override
    public void onClick(View v) {
        if (role.equals("mentor")) {
            performMentorSignUp();
        } else if (role.equals("mentorado")) {
            performMentoradoSignUp();
        }
        Intent it4 = new Intent(SignUp.this, Profile.class);
        startActivity(it4);
    }

    private void performMentoradoSignUp() {
    }

    private void performMentorSignUp() {
    }
}
