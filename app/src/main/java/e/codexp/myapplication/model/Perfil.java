package e.codexp.myapplication.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tpetrone on 19/03/18.
 */

public class Perfil {
    private Long id;
    private String nome;
    private String bio;
    private String genero;
    private String unidade;
    private String ano;
    private byte[] foto;
    private static SimpleDateFormat fmtData =
            new SimpleDateFormat("yyyy");

    public Perfil() {}
    public Perfil(Long id) { this.id = id; }
    public Perfil(Long id, String nome, String bio, String genero, String unidade, String ano) {
        this.id = id;
        this.nome = nome;
        this.bio = bio;
        this.genero = genero;
        this.unidade = unidade;
        this.ano = ano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }

    public String getAno() { return ano; }
    public void setAno(String ano) { this.ano = ano; }

    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] capa) { this.foto = foto; }

}
