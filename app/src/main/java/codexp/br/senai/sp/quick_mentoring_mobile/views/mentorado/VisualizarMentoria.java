package codexp.br.senai.sp.quick_mentoring_mobile.views.mentorado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Aplicacao;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Categoria;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Sede;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Usuario;
import codexp.br.senai.sp.quick_mentoring_mobile.views.LoginActivity;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentor.CadastrarMentoriaActivity;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentor.HomeMentorActivity;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentor.VisualizarAplicacoes;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarMentoria extends AppCompatActivity {

    private int mentoriaId;
    private String token;
    private int usuarioId;
    private TextView tvNomeMentoria;
    private TextView tvNomeCategoria;
    private TextView tvNomeSede;
    private TextView tvOnline;
    private TextView tvPresencial;
    private EditText mtFormulario;
    private Button btContinuar;
    private Mentoria mentoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_mentoria);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        usuarioId = sharedPreferences.getInt("usuarioId", 0);

        tvNomeMentoria = findViewById(R.id.tvNomeMentoria);
        tvNomeCategoria = findViewById(R.id.tvNomeCategoria);
        tvNomeSede = findViewById(R.id.tvNomeSede);
        tvOnline = findViewById(R.id.tvOnline);
        tvPresencial = findViewById(R.id.tvPresencial);
        mtFormulario = findViewById(R.id.mtFormulario);
        btContinuar = findViewById(R.id.btContinuar);

        btContinuar.setOnClickListener(new confirmaAplicacao());

        mentoriaId = getIntent().getExtras().getInt("mentoriaId");
        carregarInformacoesDaMentoria();
    }

    private class confirmaAplicacao implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            Call<Aplicacao> call = new RetrofitConfig(token).getRestInterface().cadastrarAplicacao(
                    new Aplicacao(usuarioId, mentoriaId, mtFormulario.getEditableText().toString()
                    ));

            call.enqueue(new Callback<Aplicacao>() {
                @Override
                public void onResponse(Call<Aplicacao> call, Response<Aplicacao> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(VisualizarMentoria.this, MinhasAplicacoes.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Aplicacao> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro ao fazer login.", Toast.LENGTH_LONG).show();
                    Log.d("ErroLogin: ", t.getMessage().toString());
                }
            });

        }
    }

    private void carregarInformacoesDaMentoria() {
        Call<Mentoria> call = new RetrofitConfig(token).getRestInterface().listarMentoria(mentoriaId);

        call.enqueue(new Callback<Mentoria>() {
            @Override
            public void onResponse(Call<Mentoria> call, Response<Mentoria> response) {
                if (response.isSuccessful()) {
                    mentoria = response.body();
                    if (mentoria != null) {
                        tvNomeMentoria.setText(mentoria.getNome());

                        Categoria categoria = mentoria.getCategoria();
                        tvNomeCategoria.setText(categoria.getNome());
                        Sede sede = mentoria.getSede();
                        tvNomeSede.setText(sede.getNome());

                        if (mentoria.isOnline()) {
                            tvOnline.setText(String.valueOf(tvOnline));
                            tvPresencial.setText(String.valueOf(""));
                        } else {
                            tvPresencial.setText(String.valueOf("Presencial"));
                            tvOnline.setText(String.valueOf(""));
                        }
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
