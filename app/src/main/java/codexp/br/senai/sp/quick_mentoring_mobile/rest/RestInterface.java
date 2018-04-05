package codexp.br.senai.sp.quick_mentoring_mobile.rest;

import java.util.List;

import codexp.br.senai.sp.quick_mentoring_mobile.model.Aplicacao;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Categoria;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Sede;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Usuario;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestInterface {

    @GET("sede")
    Call<List<Sede>> listarSedes();

    @POST("usuario")
    Call<Usuario> salvarUsuario(@Body Usuario usuario);

    @POST("usuario/login")
    Call<ResponseBody> realizarLogin(@Body Usuario usuario);

    @GET("mentoria")
    Call<List<Mentoria>> listarMentoriasDosMentores();

    @GET("mentor/{id}/mentorias")
    Call<List<Mentoria>> listarMentoriasDoMentor(@Path("id") int id);

    @GET("mentoria/{id}")
    Call<Mentoria> listarMentoria(@Path("id") int id);

    @GET("mentoria/{id}/aplicacoes")
    Call<List<Aplicacao>> listarAplicacoesDaMentoria(@Path("id") int id);

    @GET("mentorado/{id}/aplicacoes")
    Call<List<Aplicacao>> listarAplicacoesDoMentorado(@Path("id") int id);

    @GET("categoria")
    Call<List<Categoria>> listarCategoriasDosMentores();

    @POST("mentoria")
    Call<Mentoria> cadastrarMentoria(@Body Mentoria mentoria);

    @POST("aplicacao")
    Call<Aplicacao> cadastrarAplicacao(@Body Aplicacao aplicacao);

    @GET("aplicacao/{id}")
    Call<Aplicacao> lerAplicacao(@Path("id") int id);

    @PUT("aplicacao/{id}")
    Call<Aplicacao> updateAplicacao(@Body Aplicacao aplicacao, @Path("id") int id);

}
