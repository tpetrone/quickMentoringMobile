package codexp.br.senai.sp.quick_mentoring_mobile.views.mentor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Aplicacao;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarAplicacao extends AppCompatActivity {

    private int aplicacaoId;
    private String token;
    private int usuarioId;
    private ImageView ivPerfil;
    private TextView tvNome;
    private TextView tvMiniBio;
    private TextView tvCEP;
    private TextView tvSede;
    private TextView tvFormulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_aplicacao);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        usuarioId = sharedPreferences.getInt("usuarioId", 0);

        tvNome = findViewById(R.id.tvNome);
        tvMiniBio = findViewById(R.id.tvMiniBio);
        tvCEP = findViewById(R.id.tvCEP);
        tvSede = findViewById(R.id.tvSede);
        tvFormulario = findViewById(R.id.tvFormulario);

        aplicacaoId = getIntent().getExtras().getInt("aplicacaoId");
        carregarInformacoesDaAplicacao();
    }
        private void carregarInformacoesDaAplicacao(){
            Call<Aplicacao> call = new RetrofitConfig(token).getRestInterface().lerAplicacao(aplicacaoId);

            call.enqueue(new Callback<Aplicacao>() {
                @Override
                public void onResponse(Call<Aplicacao> call, Response<Aplicacao> response) {
                    if (response.isSuccessful()) {
                        Aplicacao aplicacao = response.body();
                        if (aplicacao != null) {
                            Mentoria mentoria = aplicacao.getMentoria();
                            Log.d("mentoria", mentoria.getNome());
                        } else {
                            Toast.makeText(getApplicationContext(), "Não há aplicacaos cadastradas.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Aplicacao> call, Throwable t) {

                }
            });
        }

}