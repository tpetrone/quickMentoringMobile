package codexp.br.senai.sp.quick_mentoring_mobile.views.mentor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.adapter.MentoriaAdapter;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.interfaces.OnClickListener;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import codexp.br.senai.sp.quick_mentoring_mobile.views.LoginActivity;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentorado.VisualizarMentoria;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeMentorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rvListagemMentorias;
    private List<Mentoria> mentoriasApi;

    private String token;
    private int usuarioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mentor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvListagemMentorias = findViewById(R.id.rvListagemMentorias);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        usuarioId = sharedPreferences.getInt("usuarioId", 0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastrarMentoria = new Intent(HomeMentorActivity.this, CadastrarMentoriaActivity.class);
                startActivity(cadastrarMentoria);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        carregarInformacoesDasMentorias();
    }

    private void carregarInformacoesDasMentorias() {
        final OnClickListener listener;
        listener = new OnClickListener() {
            @Override
            public void onItemClick(Mentoria mentoria) {
                Intent intent = new Intent(HomeMentorActivity.this, VisualizarMentoria.class);
                intent.putExtra("mentoriaId", mentoria.getMentoriaId());
                startActivity(intent);
            }
        };

        Call<List<Mentoria>> call = new RetrofitConfig(token).getRestInterface().listarMentoriasDoMentor(usuarioId);
        call.enqueue(new Callback<List<Mentoria>>() {
            @Override
            public void onResponse(Call<List<Mentoria>> call, Response<List<Mentoria>> response) {
                if (response.isSuccessful()) {
                    mentoriasApi = response.body();
                    if (mentoriasApi != null) {
                        rvListagemMentorias.setAdapter(new MentoriaAdapter(mentoriasApi, listener, getApplicationContext()));
                        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        rvListagemMentorias.setLayoutManager(layout);
                    } else {
                        Toast.makeText(getApplicationContext(), "Não há mentorias cadastradas.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Mentoria>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_mentor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
