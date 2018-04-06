package codexp.br.senai.sp.quick_mentoring_mobile.views;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.Utilitarios;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Perfil;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Sede;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Usuario;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentor.HomeMentorActivity;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentorado.HomeMentoradoActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarPerfil extends AppCompatActivity implements View.OnClickListener {

    private static int NEW_ACTION = 1;
    private static final int REQUEST_IMAGE_GALERY = 0;
    private static final int REQUEST_GALERY_PERMISSION = 1;
    private ImageView ivPerfil;

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

    private String userRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_perfil);

        ivPerfil = findViewById(R.id.ivPerfil);
        ivPerfil.setOnClickListener(new salvarImagem());

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

                        for (int i = 0; i < sedeAdapter.getCount(); i++) {
                            if (sedeSelecionada.toString().equals(sedeAdapter.getItem(i).toString())) {
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
        Call<Usuario> call = new RetrofitConfig(token).getRestInterface().lerUsuario(usuarioId);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario usuario = response.body();
                    if (usuario != null) {
                        perfilApi = usuario.getPerfil();
                        etNome.setText(perfilApi.getNome());
                        etMiniBio.setText(perfilApi.getMiniBio());
                        etCep.setText(perfilApi.getCep());
                        userRole = usuario.getRole();
                        sedeSelecionada = perfilApi.getSede();
                    } else {
                        Toast.makeText(getApplicationContext(), "Não há mentorias cadastradas.", Toast.LENGTH_LONG).show();
                    }
                    carregarSedes();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

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
                    if (userRole.equals("Mentor")) {
                        Intent intent = new Intent(VisualizarPerfil.this, HomeMentorActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(VisualizarPerfil.this, HomeMentoradoActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private class salvarImagem implements View.OnClickListener{
        @Override
        public void onClick(View view) {
                abrirGalery();
        }
    }
    private void abrirGalery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        if (intent.resolveActivity(getPackageManager()) != null) {
            if ((ContextCompat.checkSelfPermission(getBaseContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALERY_PERMISSION);
            } else {
                startActivityForResult(Intent.createChooser(intent, "Selecione a Foto"),
                        REQUEST_IMAGE_GALERY);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_GALERY) { // Recebe a Foto da Galeria de Fotos
            if (data != null) {
                try {
                    Uri imageURI = data.getData();

                    Bitmap bitmap = Utilitarios.setPic(ivPerfil.getWidth(), ivPerfil.getHeight(), imageURI, this);
                    ivPerfil.setImageBitmap(bitmap);
                    ivPerfil.invalidate();
                } catch (IOException ex) {
                    Toast.makeText(this, "Falha ao abrir a Foto", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}