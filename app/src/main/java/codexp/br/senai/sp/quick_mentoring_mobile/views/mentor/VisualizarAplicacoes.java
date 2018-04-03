package codexp.br.senai.sp.quick_mentoring_mobile.views.mentor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.adapter.AplicacaoAdapter;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.adapter.MentoriaAdapter;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.interfaces.AplicacaoOnClickListener;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.interfaces.OnClickListener;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Aplicacao;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentorado.HomeMentoradoActivity;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentorado.VisualizarMentoria;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarAplicacoes extends AppCompatActivity {

    private int mentoriaId;
    private int usuarioId;
    private String token;

    private RecyclerView rvListagemAplicacoes;
    private List<Aplicacao> aplicacoesApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_aplicacoes);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        usuarioId = sharedPreferences.getInt("usuarioId", 0);

        rvListagemAplicacoes = findViewById(R.id.rvListagemAplicacoes);

        mentoriaId = getIntent().getExtras().getInt("mentoriaId");
        carregarInformacoesDasAplicacoes();
    }

    private void carregarInformacoesDasAplicacoes() {
        final AplicacaoOnClickListener listener;
        listener = new AplicacaoOnClickListener() {
            @Override
            public void onItemClick(Aplicacao aplicacao) {
                Intent intent = new Intent(VisualizarAplicacoes.this, VisualizarMentoria.class);
                intent.putExtra("aplicacaoId", aplicacao.getId());
                startActivity(intent);
            }
        };

        Call<List<Aplicacao>> call = new RetrofitConfig().getRestInterface().listarAplicacoesDaMentoria(mentoriaId);
        call.enqueue(new Callback<List<Aplicacao>>() {
            @Override
            public void onResponse(Call<List<Aplicacao>> call, Response<List<Aplicacao>> response) {
                if (response.isSuccessful()) {
                    aplicacoesApi = response.body();
                    if (aplicacoesApi != null) {
                        rvListagemAplicacoes.setAdapter(new AplicacaoAdapter(aplicacoesApi, listener, getApplicationContext()));
                        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        rvListagemAplicacoes.setLayoutManager(layout);
                    } else {
                        Toast.makeText(getApplicationContext(), "Não há mentorias cadastradas.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Aplicacao>> call, Throwable t) {

            }
        });
    }
}
