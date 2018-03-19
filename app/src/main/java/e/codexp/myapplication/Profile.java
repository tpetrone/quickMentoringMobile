package e.codexp.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import e.codexp.myapplication.model.Perfil;
import e.codexp.myapplication.model.PerfilDao;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    // Flags
    private static int RESULT_LOAD_IMAGE = 1;

    // Elementos
    private ImageView ivFoto;
    private Spinner dropdown, dropdown2, dropdown3;
    private EditText edNome;
    private EditText edBio;

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
        dropdown = (Spinner) findViewById(R.id.spngenero);
        ArrayAdapter<String> Adpgenero = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.genero));
        Adpgenero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(Adpgenero);

        dropdown2 =(Spinner) findViewById(R.id.spnnascimento);
        ArrayList<String> years = new ArrayList<String>();
        years.add("Selecione o ano de nascimento");
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1930; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> Adpano = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, years);
        Adpano.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown2.setAdapter(Adpano);

        dropdown3 =(Spinner) findViewById(R.id.spnsede);
        ArrayAdapter<String> Adpsede = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.unidades));
        Adpsede.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown3.setAdapter(Adpsede);


    }


    @Override
    public void onClick(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!= null){
            Uri selectedImage = data.getData();
            ivFoto.setImageURI(selectedImage);
        };
    };

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

    public void confimarClicado(View view) {
        Intent it6 = new Intent(Profile.this, CriarMentoria.class);
        startActivity(it6);
    }
}


