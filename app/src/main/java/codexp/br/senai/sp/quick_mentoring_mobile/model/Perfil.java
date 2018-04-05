package codexp.br.senai.sp.quick_mentoring_mobile.model;

/**
 * Created by Helena Strada on 22/03/2018.
 */

public class Perfil {

    private int id;
    private String nome;
    private String miniBio;
    private String cep;

    private int sedeId;
    private Sede sede;

    @Override
    public String toString() {
        return "Perfil{" +
                "perfilId=" + id +
                ", nome='" + nome + '\'' +
                ", miniBio='" + miniBio + '\'' +
                ", cep='" + cep + '\'' +
                ", sede='" + sede + '\'' +
                '}';
    }

    public Perfil(String nome, String miniBio, String cep, Sede sede) {
        this.nome = nome;
        this.miniBio = miniBio;
        this.cep = cep;
        this.sede = sede;
    }

    public Perfil(String nome, String miniBio, String cep, int sedeId) {
        this.nome = nome;
        this.miniBio = miniBio;
        this.cep = cep;
        this.sedeId = sedeId;
    }


    public int getPerfilId() {
        return id;
    }

    public void setPerfilId(int perfilId) {
        this.id = perfilId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMiniBio() {
        return miniBio;
    }

    public void setMiniBio(String miniBio) {
        this.miniBio = miniBio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getSedeId() {
        return sedeId;
    }

    public void setSedeId(int sedeId) {
        this.sedeId = sedeId;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
}
