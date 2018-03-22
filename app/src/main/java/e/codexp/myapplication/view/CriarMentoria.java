package e.codexp.myapplication.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import e.codexp.myapplication.R;
import e.codexp.myapplication.model.CriarMentoriaDAO;

public class CriarMentoria extends AppCompatActivity {
    private EditText nomeDamentoria;
    private e.codexp.myapplication.model.CriarMentoriaDAO dao = CriarMentoriaDAO.instance;
    private e.codexp.myapplication.model.CriarMentoria CriarMentoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_mentoria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CriarMentoria = dao.localizar();
        nomeDamentoria =findViewById(R.id.NomeDaMentoria);
        nomeDamentoria.setText(CriarMentoria.getNomementoria());
    }
}
