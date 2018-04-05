package codexp.br.senai.sp.quick_mentoring_mobile.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Categoria;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Perfil;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Sede;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentor.CadastrarMentoriaActivity;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentor.HomeMentorActivity;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentorado.VisualizarMentoria;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarPerfil extends AppCompatActivity implements View.OnClickListener {

    private String token;
    private int usuarioId;
    private EditText etNome;
    private EditText etMiniBio;
    private EditText etCep;
    private Button btSalvarPerfil;

    private Sede sedeSelecionada;
    private List<Sede> sedesConsultadas;
    private Spinner spCadastroSedes;
    private ArrayAdapter<Sede> sedeAdapter;
    private Sede sedePerfil;
    private Perfil perfilApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_perfil);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        usuarioId = sharedPreferences.getInt("usuarioId", 0);

        etNome = findViewById(R.id.etNome);
        etMiniBio = findViewById(R.id.etMiniBio);
        etCep = findViewById(R.id.etCep);
        btSalvarPerfil = findViewById(R.id.btSalvarPerfil);

        btSalvarPerfil.setOnClickListener(this);

        spCadastroSedes = findViewById(R.id.spSede);

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


        carregaPerfil();
    }

    private void carregarSedes() {
        Call<List<Sede>> call = new RetrofitConfig().getRestInterface().listarSedes();
        call.enqueue(new Callback<List<Sede>>() {
            @Override
            public void onResponse(Call<List<Sede>> call, Response<List<Sede>> response) {
                if (response.isSuccessful()) {
                    sedesConsultadas = response.body();
                    if (sedesConsultadas != null) {
                        sedeAdapter = new ArrayAdapter<Sede>(getApplicationContext(), android.R.layout.simple_list_item_1, sedesConsultadas);
                        spCadastroSedes.setAdapter(sedeAdapter);

                        for(int i=0; i < sedeAdapter.getCount(); i++) {
                            if(sedeSelecionada.toString().equals(sedeAdapter.getItem(i).toString())){
                                spCadastroSedes.setSelection(i);
                                break;
                            }
                        }

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


    private void carregaPerfil() {
        Call<Perfil> call = new RetrofitConfig(token).getRestInterface().lerPerfil(usuarioId);

        call.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                if (response.isSuccessful()) {
                    perfilApi = response.body();
                    if (perfilApi != null) {
                        etNome.setText(perfilApi.getNome());
                        etMiniBio.setText(perfilApi.getMiniBio());
                        etCep.setText(perfilApi.getCep());
                        sedeSelecionada = perfilApi.getSede();
                    } else {
                        Toast.makeText(getApplicationContext(), "Não há mentorias cadastradas.", Toast.LENGTH_LONG).show();
                    }
                    carregarSedes();
                }
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Perfil perfil = perfilApi;
        perfilApi.setSede(sedeSelecionada);

        Call<Perfil> call = new RetrofitConfig(token).getRestInterface().updatePerfil(perfil, usuarioId);
        call.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(VisualizarPerfil.this, HomeMentorActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
