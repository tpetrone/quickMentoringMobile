package e.codexp.myapplication.model;

/**
 * Created by CodeXP on 22/03/2018.
 */

public class Aplicacao {
    private long id;
    private String usuario;
    private String nomeMentoria;
    private String justificativa;

    public Aplicacao(){
    }
    public Aplicacao(Long id){this.id = id;}
    public Aplicacao(Long id, String usuario, String nomeMentoria, String justificativa){
        this.id = id;
        this.usuario = usuario;
        this.nomeMentoria = nomeMentoria;
        this.justificativa = justificativa;
    }
    public Long getId(){return id;}
    public void setId(long id) {this.id = id;}

    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}

    public String getNomeMentoria() {return nomeMentoria;}
    public void setNomeMentoria(String nomeMentoria) {this.nomeMentoria = nomeMentoria;}

    public String getJustificativa() {return justificativa;}
    public void setJustificativa(String justificativa) {this.justificativa = justificativa;}
}
