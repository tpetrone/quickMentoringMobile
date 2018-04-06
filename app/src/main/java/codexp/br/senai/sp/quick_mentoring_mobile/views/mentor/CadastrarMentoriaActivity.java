package codexp.br.senai.sp.quick_mentoring_mobile.views.mentor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Categoria;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Sede;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarMentoriaActivity extends AppCompatActivity {

    private TextInputLayout tilNomeMentoria;
    private Spinner spCategoriasMentoria;
    private Spinner spSedesMentoria;
    private RadioButton rbOnline;
    private RadioButton rbPresencial;
    private int usuarioId;
    private Button btCadastrarMentoria;
    private Sede sedeSelecionada;
    private Categoria categoriaSelecionada;
    private String token;
    private boolean online;

    private List<Sede> sedesConsultadas;
    private List<Categoria> categoriasConsultadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_mentoria);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        usuarioId = sharedPreferences.getInt("usuarioId", 0);

        tilNomeMentoria = findViewById(R.id.tilNomeMentoria);
        spCategoriasMentoria = findViewById(R.id.spCategoriasMentoria);
        spSedesMentoria = findViewById(R.id.spSedesMentoria);
        rbOnline = findViewById(R.id.rbOnline);
        rbPresencial = findViewById(R.id.rbPresencial);
        btCadastrarMentoria = findViewById(R.id.btCadastrarMentoria);

        btCadastrarMentoria.setOnClickListener(new SalvarNovaMentoria());

        carregarInformacoesParaCadastroDeMentoria();

        spSedesMentoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sedeSelecionada = (Sede) spSedesMentoria.getItemAtPosition(spSedesMentoria.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spCategoriasMentoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoriaSelecionada = (Categoria) spCategoriasMentoria.getItemAtPosition(spCategoriasMentoria.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void carregarInformacoesParaCadastroDeMentoria() {

        Call<List<Sede>> call = new RetrofitConfig().getRestInterface().listarSedes();
        call.enqueue(new Callback<List<Sede>>() {
            @Override
            public void onResponse(Call<List<Sede>> call, Response<List<Sede>> response) {
                if (response.isSuccessful()) {
                    sedesConsultadas = response.body();
                    if (sedesConsultadas != null) {
                        ArrayAdapter<Sede> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, sedesConsultadas);
                        spSedesMentoria.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Sede>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_LONG).show();
                Log.d("Erro - Cadastro:", t.getMessage());
            }
        });

        Call<List<Categoria>> callCategorias = new RetrofitConfig().getRestInterface().listarCategoriasDosMentores();
        callCategorias.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful()) {
                    categoriasConsultadas = response.body();
                    if (categoriasConsultadas != null) {
                        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, categoriasConsultadas);
                        spCategoriasMentoria.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_LONG).show();
                Log.d("Erro - Cadastro:", t.getMessage());

            }
        });

    }

    private class SalvarNovaMentoria implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (rbOnline.isChecked()) {
                online = true;
            } else {
                online = false;
            }

            Mentoria mentoriaASalvar = new Mentoria(
                // boolean ativa, boolean online, String nome, int usuarioid, int categoriaid, int sedeid
                    true
                    , online
                    , tilNomeMentoria.getEditText().getText().toString()
                    , usuarioId
                    , categoriaSelecionada.getId()
                    , sedeSelecionada.getSedeId()
            );

            Call<Mentoria> call = new RetrofitConfig(token).getRestInterface().cadastrarMentoria(mentoriaASalvar);
            call.enqueue(new Callback<Mentoria>() {
                @Override
                public void onResponse(Call<Mentoria> call, Response<Mentoria> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(CadastrarMentoriaActivity.this, HomeMentorActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Mentoria> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}
