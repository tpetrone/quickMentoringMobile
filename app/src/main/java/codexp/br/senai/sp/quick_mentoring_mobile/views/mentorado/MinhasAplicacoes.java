package codexp.br.senai.sp.quick_mentoring_mobile.views.mentorado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import codexp.br.senai.sp.quick_mentoring_mobile.R;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.adapter.AplicacaoAdapter;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.adapter.MentoriaAdapter;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.interfaces.AplicacaoOnClickListener;
import codexp.br.senai.sp.quick_mentoring_mobile.adapters.interfaces.OnClickListener;
import codexp.br.senai.sp.quick_mentoring_mobile.commons.AppUtils;
import codexp.br.senai.sp.quick_mentoring_mobile.config.RetrofitConfig;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Aplicacao;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import codexp.br.senai.sp.quick_mentoring_mobile.views.mentor.CadastrarMentoriaActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MinhasAplicacoes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String token;
    private int usuarioId;

    private RecyclerView rvListagemAplicacoes;
    private List<Aplicacao> aplicacoesApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_aplicacoes);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        usuarioId = sharedPreferences.getInt("usuarioId", 0);

        rvListagemAplicacoes = findViewById(R.id.rvListagemAplicacoes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.minhas_aplicacoes);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        carregarAplicacoesdoMentorado();
    }

    private void carregarAplicacoesdoMentorado() {
        final AplicacaoOnClickListener listener;
        listener = new AplicacaoOnClickListener() {
            @Override
            public void onItemClick(Aplicacao aplicacao) {
                Intent intent = new Intent(MinhasAplicacoes.this, VisualizarMentoria.class);
                intent.putExtra("aplicacaoId", aplicacao.getId());
                startActivity(intent);
            }
        };

        Call<List<Aplicacao>> call = new RetrofitConfig(token).getRestInterface().listarAplicacoesDoMentorado(usuarioId);
        call.enqueue(new Callback<List<Aplicacao>>() {
            @Override
            public void onResponse(Call<List<Aplicacao>> call, Response<List<Aplicacao>> response) {
                if (response.isSuccessful()) {
                    aplicacoesApi = response.body();
                    if (aplicacoesApi != null) {
                        rvListagemAplicacoes.setAdapter(new AplicacaoAdapter(aplicacoesApi, listener, getApplicationContext()));
                        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        rvListagemAplicacoes.setLayoutManager(layout);
                    } else {
                        Toast.makeText(getApplicationContext(), "Não há aplicações para mostrar.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Aplicacao>> call, Throwable t) {

            }
        });

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mentorias) {
            Intent intent = new Intent(MinhasAplicacoes.this, HomeMentoradoActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.minhas_aplicacoes);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
