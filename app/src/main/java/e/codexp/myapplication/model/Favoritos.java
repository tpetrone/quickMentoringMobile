package e.codexp.myapplication.model;

/**
 * Created by CodeXP on 22/03/2018.
 */

public class Favoritos {
    private Long id;
    private String usuario;
    private String nomeMentoria;

    public Favoritos() {
    }
    public Favoritos(Long id) {this.id = id;}
    public Favoritos(Long id, String usuario, String nomeMentoria) {
        this.id = id;
        this.usuario = usuario;
        this.nomeMentoria = nomeMentoria;
    }
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getNome(){return usuario;}
    public void setNome(String usuario){this.usuario = usuario;}

    public String getNomementoria(){return nomeMentoria;}
    public void setNomementoria(String nomeMentoria){this.nomeMentoria = nomeMentoria;}


}
