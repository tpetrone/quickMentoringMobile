package e.codexp.myapplication.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import e.codexp.myapplication.R;
import e.codexp.myapplication.lib.Utilitarios;
import e.codexp.myapplication.model.Perfil;
import e.codexp.myapplication.model.PerfilDao;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    // Flags
    private static int NEW_ACTION = 1;
    private static final int REQUEST_IMAGE_GALERY = 0;
    private static final int REQUEST_GALERY_PERMISSION = 1;

    // Elementos
    private Spinner dropdown, dropdown2, dropdown3;
    private EditText edNome;
    private EditText edBio;

    private ImageView ivFoto;
    private Button btnConfirma;


    // Models
    private PerfilDao dao = PerfilDao.instance;
    private Perfil perfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        perfil = dao.localizar();

        ivFoto = findViewById(R.id.imgperfil);
        ivFoto.setOnClickListener(this);

        ArrayAdapter<String> AdpAno = setSpinnerAdapter(generateYears());;
        ArrayAdapter<String> AdpSede = setSpinnerAdapter(getSede());
        ArrayAdapter<String> AdpGenero = setSpinnerAdapter(getGeneros());

        dropdown = findViewById(R.id.spngenero);
        dropdown.setAdapter(AdpGenero);

        dropdown2 = findViewById(R.id.spnnascimento);
        dropdown2.setAdapter(AdpAno);

        dropdown3 =(Spinner) findViewById(R.id.spnsede);
        dropdown3.setAdapter(AdpSede);

        btnConfirma = findViewById(R.id.btnok);
        btnConfirma.setOnClickListener(this);

        edNome = findViewById(R.id.nome);
        edNome.setText(perfil.getNome());
    }

    private List<String> getSede() {
        return Arrays.asList(getResources().getStringArray(R.array.unidades));
    }

    private List<String> getGeneros() {
        return Arrays.asList(getResources().getStringArray(R.array.genero));
    }

    private List<String> generateYears() {
        List<String> years = new ArrayList<String>();
        years.add("Selecione o ano de nascimento");
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1930; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        return years;
    }

    private ArrayAdapter<String> setSpinnerAdapter(List<String> data) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    @Override
    public void onClick(View view) {
        if(view.equals(ivFoto)) {
            // Abrir a Galeria de Fotos
            abrirGalery();
        } else if(view.equals(btnConfirma)) {
            //TODO: terminar de salvar atributos
            perfil.setNome(String.valueOf(edNome.getText()));
            //TODO fim

            dao.salvar(perfil);

            Intent tela = new Intent(getBaseContext(), CriarMentoria.class);
            startActivityForResult(tela, NEW_ACTION);
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
            if(data != null) {
                try {
                    Uri imageURI = data.getData();

                    Bitmap bitmap = Utilitarios.setPic(ivFoto.getWidth(), ivFoto.getHeight(), imageURI, this);
                    ivFoto.setImageBitmap(bitmap);
                    ivFoto.invalidate();
                } catch (IOException ex) {
                    Toast.makeText(this, "Falha ao abrir a Foto", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.perfil:
                Intent perfil = new Intent(this, Profile.class);
                startActivity(perfil);
                break;
        }
        return true;
    }
}
