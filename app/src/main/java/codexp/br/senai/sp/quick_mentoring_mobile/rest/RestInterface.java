package codexp.br.senai.sp.quick_mentoring_mobile.rest;

import java.util.List;

import codexp.br.senai.sp.quick_mentoring_mobile.model.Categoria;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Mentoria;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Sede;
import codexp.br.senai.sp.quick_mentoring_mobile.model.Usuario;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestInterface {

    @GET("sede")
    Call<List<Sede>> listarSedes();

    @POST("usuario/cadastrar")
    Call<Usuario> salvarUsuario(@Body Usuario usuario);

    @POST("usuario/login")
    Call<ResponseBody> realizarLogin(@Body Usuario usuario);

    @GET("mentoria")
    Call<List<Mentoria>> listarMentoriasDosMentores();

    @GET("categoria")
    Call<List<Categoria>> listarCategoriasDosMentores();

    @POST("mentoria")
    Call<Mentoria> cadastrarMentoria(@Body Mentoria mentoria);

}