package codexp.br.senai.sp.quick_mentoring_mobile.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import codexp.br.senai.sp.quick_mentoring_mobile.MainActivity;
import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Usuario;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentor.HomeMentorActivity;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentorado.HomeMentoradoActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilLoginUsuario;
    private TextInputLayout tilLoginSenha;
    private Button btnRealizarLogin;
    private String accessToken;
    private int usuarioLogado;
    private String respostaDoLogin;

    private SharedPreferences sharedPreferences;
    private String token;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilLoginUsuario = findViewById(R.id.tilLoginUsuario);
        tilLoginSenha = findViewById(R.id.tilLoginSenha);
        btnRealizarLogin = findViewById(R.id.btnRealizarLogin);

        sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);

        try {
            email = getIntent().getExtras().getString("email");
            password = getIntent().getExtras().getString("password");
        } catch(Exception e) {
            email = "";
            password= "";
        }

        tilLoginUsuario.getEditText().setText(email);
        tilLoginSenha.getEditText().setText(password);


        btnRealizarLogin.setOnClickListener(new buscaLogin());

    }

    private class buscaLogin implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            Call<ResponseBody> call = new RetrofitConfig().getRestInterface().realizarLogin(new Usuario(
                    tilLoginUsuario.getEditText().getText().toString()
                    , tilLoginSenha.getEditText().getText().toString()
            ));

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            JSONObject objeto = new JSONObject(response.body().string());
                            accessToken = objeto.getString("accessToken");

                            JSONObject usuarioObjeto = new JSONObject(String.valueOf(objeto.getJSONObject("usuario")));
                            usuarioLogado = (int) usuarioObjeto.get("usuarioId");
                            String perfil = (String) usuarioObjeto.get("role");

                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("token", "Bearer " + accessToken);
                            editor.putInt("usuarioId", usuarioLogado);

                            editor.apply();

                            if (perfil.equals("Mentor")) {
                                Intent intent = new Intent(LoginActivity.this, HomeMentorActivity.class);
                                startActivity(intent);
                            } else if (perfil.equals("Mentorado")) {
                                Intent intent = new Intent(LoginActivity.this, HomeMentoradoActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Seu perfil não coincide.", Toast.LENGTH_LONG).show();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro ao fazer login.", Toast.LENGTH_LONG).show();
                    Log.d("ErroLogin: ", t.getMessage().toString());
                }
            });

        }
    }
}
