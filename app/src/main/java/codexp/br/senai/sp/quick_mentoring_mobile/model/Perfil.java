package codexp.br.senai.sp.quick_mentoring_mobile.model;

/**
 * Created by Helena Strada on 22/03/2018.
 */

public class Perfil {

    private int perfilId;
    private String nome;
    private String miniBio;
    private String cep;
    private int sedeId;

    public Perfil() {

    }

    @Override
    public String toString() {
        return "Perfil{" +
                "perfilId=" + perfilId +
                ", nome='" + nome + '\'' +
                ", miniBio='" + miniBio + '\'' +
                ", cep='" + cep + '\'' +
                ", sedeId='" + sedeId + '\'' +
                '}';
    }

    public Perfil(String nome, String miniBio, String cep, int sedeId) {
        this.nome = nome;
        this.miniBio = miniBio;
        this.cep = cep;
        this.sedeId = sedeId;
    }

    public int getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(int perfilId) {
        this.perfilId = perfilId;
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
}
