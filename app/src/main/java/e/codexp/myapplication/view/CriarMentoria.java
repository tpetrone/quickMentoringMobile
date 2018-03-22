package e.codexp.myapplication.view;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import e.codexp.myapplication.R;
import e.codexp.myapplication.model.MentoriaDAO;

public class CriarMentoria extends AppCompatActivity {
    private EditText nomeDamentoria;
    private e.codexp.myapplication.model.MentoriaDAO dao = MentoriaDAO.instance;
    private e.codexp.myapplication.model.Mentoria CriarMentoria;
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
