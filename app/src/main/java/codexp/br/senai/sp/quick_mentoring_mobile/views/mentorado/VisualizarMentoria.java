package codexp.br.senai.sp.quick_mentoring_mobile.views.mentorado;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarMentoria extends AppCompatActivity {

    private int mentoriaId;
    private String token;
    private int usuarioId;
    private TextView tvNomeMentoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_mentoria);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        usuarioId = sharedPreferences.getInt("usuarioId", 0);

        tvNomeMentoria = findViewById(R.id.tvNomeMentoria);

        mentoriaId = getIntent().getExtras().getInt("mentoriaId");
        carregarInformacoesDaMentoria();
    }

    private void carregarInformacoesDaMentoria() {
        Call<Mentoria> call = new RetrofitConfig(token).getRestInterface().listarMentoria(mentoriaId);

        call.enqueue(new Callback<Mentoria>() {
            @Override
            public void onResponse(Call<Mentoria> call, Response<Mentoria> response) {
                if (response.isSuccessful()) {
                    Mentoria mentoria = response.body();
                    if (mentoria != null) {
                        tvNomeMentoria.setText(mentoria.getNome());
                    } else {
                        Toast.makeText(getApplicationContext(), "Não há mentorias cadastradas.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Mentoria> call, Throwable t) {

            }
        });
    }
}
