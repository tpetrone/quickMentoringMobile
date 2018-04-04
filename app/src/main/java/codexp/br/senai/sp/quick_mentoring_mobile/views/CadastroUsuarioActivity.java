package codexp.br.senai.sp.quick_mentoring_mobile.views;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Perfil;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Sede;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private TextInputLayout tilCadastroEmail;
    private TextInputLayout tilCadastroSenha;
    private TextInputLayout tilCadastroNome;
    private TextInputLayout tilCadastroMiniBio;
    private TextInputLayout tilCadastroCep;
    private Spinner spCadastroSedes;
    private List<Sede> sedesConsultadas;

    private Bundle extras;
    private String perfil;

    private Button btCadastroTela;

    private Sede sedeSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        tilCadastroEmail = findViewById(R.id.tilCadastroEmail);
        tilCadastroSenha = findViewById(R.id.tilCadastroSenha);
        tilCadastroNome = findViewById(R.id.tilCadastroNome);
        tilCadastroMiniBio = findViewById(R.id.tilCadastroMiniBio);
        tilCadastroCep = findViewById(R.id.tilCadastroCep);
        spCadastroSedes = findViewById(R.id.spCadastroSedes);
        btCadastroTela = findViewById(R.id.btCadastroTela);

        extras = getIntent().getExtras();
        perfil = (extras != null) ? extras.getString("perfil") : null;

        carregarSedes();

        btCadastroTela.setOnClickListener(new salvarNovoUsuario());

        spCadastroSedes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sedeSelecionada = (Sede) spCadastroSedes.getItemAtPosition(spCadastroSedes.getSelectedItemPosition());
                Log.d("sedeId", String.valueOf(sedeSelecionada.getSedeId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void carregarSedes() {
        Call<List<Sede>> call = new RetrofitConfig().getRestInterface().listarSedes();
        call.enqueue(new Callback<List<Sede>>() {
            @Override
            public void onResponse(Call<List<Sede>> call, Response<List<Sede>> response) {
                if (response.isSuccessful()) {
                    sedesConsultadas = response.body();
                    if (sedesConsultadas != null) {
                        ArrayAdapter<Sede> adapter = new ArrayAdapter<Sede>(getApplicationContext(), android.R.layout.simple_list_item_1, sedesConsultadas);
                        spCadastroSedes.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Sede>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_LONG).show();
                Log.d("Erro - Cadastro:", t.getMessage());
            }
        });
    }

    private class salvarNovoUsuario implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Usuario novoUsuario = new Usuario(
                    tilCadastroEmail.getEditText().getText().toString()
                    , tilCadastroSenha.getEditText().getText().toString()
                    , perfil
                    , true
                    , new Perfil(
                                tilCadastroNome.getEditText().getText().toString()
                                , tilCadastroMiniBio.getEditText().getText().toString()
                                , tilCadastroCep.getEditText().getText().toString()
                                , sedeSelecionada.getSedeId()
                            )
            );

            Call<Usuario> call = new RetrofitConfig().getRestInterface().salvarUsuario(novoUsuario);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);
                        String email =  tilCadastroEmail.getEditText().getText().toString();
                        String password =  tilCadastroSenha.getEditText().getText().toString();

                        intent.putExtra("email", email);
                        intent.putExtra("password", password);

                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Algo deu errado ao salvar", Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}
