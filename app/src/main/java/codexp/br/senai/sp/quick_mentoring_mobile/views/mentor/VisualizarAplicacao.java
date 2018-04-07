package codexp.br.senai.sp.quick_mentoring_mobile.views.mentor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Aplicacao;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Perfil;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Sede;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarAplicacao extends AppCompatActivity {

    private int aplicacaoId;
    private String token;
    private TextView tvNome;
    private TextView tvMiniBio;
    private TextView tvCEP;
    private TextView tvSede;

    private TextView tvFormulario;
    private ImageView ivPerfil;

    private Button btnAceitar;
    private Button btnRecusar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_aplicacao);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        tvNome = findViewById(R.id.tvNome);
        tvMiniBio = findViewById(R.id.tvMiniBio);
        tvCEP = findViewById(R.id.tvCEP);
        tvSede = findViewById(R.id.tvSede);
        tvFormulario = findViewById(R.id.tvFormulario);

        btnAceitar = findViewById(R.id.btnAceitar);
        btnAceitar.setOnClickListener(new updateAplicacao(true));

        btnRecusar = findViewById(R.id.btnRecusar);
        btnRecusar.setOnClickListener(new updateAplicacao(false));

        aplicacaoId = getIntent().getExtras().getInt("aplicacaoId");
        carregarInformacoesDaAplicacao();
    }

    private class updateAplicacao implements View.OnClickListener {

        private Aplicacao aplicacao;

        public updateAplicacao(boolean aceite) {
            aplicacao = new Aplicacao(aceite);
        }

        @Override
        public void onClick(View v) {
            Call<Aplicacao> call = new RetrofitConfig(token).getRestInterface().updateAplicacao(aplicacao, aplicacaoId);
            call.enqueue(new Callback<Aplicacao>() {
                @Override
                public void onResponse(Call<Aplicacao> call, Response<Aplicacao> response) {
                    Aplicacao aplicacao = response.body();

                    if (response.isSuccessful()) {
                        Intent intent = new Intent(VisualizarAplicacao.this, VisualizarAplicacoes.class);
                        intent.putExtra("mentoriaId", aplicacao.getMentoriaId());
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Aplicacao> call, Throwable t) {

                }
            });
        }
    }

    private void carregarInformacoesDaAplicacao() {
        Call<Aplicacao> call = new RetrofitConfig(token).getRestInterface().lerAplicacao(aplicacaoId);

        call.enqueue(new Callback<Aplicacao>() {
            @Override
            public void onResponse(Call<Aplicacao> call, Response<Aplicacao> response) {
                if (response.isSuccessful()) {
                    Aplicacao aplicacao = response.body();
                    if (aplicacao != null) {
                        Mentoria mentoria = aplicacao.getMentoria();
                        Usuario usuario = aplicacao.getUsuario();
                        Perfil perfil = usuario.getPerfil();
                        Sede sede = mentoria.getSede();

                        tvNome.setText(perfil.getNome());
                        tvMiniBio.setText(perfil.getMiniBio());
                        tvCEP.setText(perfil.getCep());
                        tvSede.setText(sede.getNome());
                        tvFormulario.setText(aplicacao.getJustificativa());
                    } else {
                        Toast.makeText(getApplicationContext(), "Não há aplicacoes cadastradas.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Aplicacao> call, Throwable t) {

            }
        });
    }

}