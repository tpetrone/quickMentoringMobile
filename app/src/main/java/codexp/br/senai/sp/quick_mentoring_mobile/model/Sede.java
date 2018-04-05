package codexp.br.senai.sp.quick_mentoring_mobile.model;

/**
 * Created by Helena Strada on 22/03/2018.
 */

public class Sede {

    private int id;
    private String nome;

    public Sede(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Sede(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Nome da Sede: " + nome;
    }

    public int getSedeId() {
        return id;
    }

    public void setSedeId(int sedeId) {
        this.id = sedeId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
