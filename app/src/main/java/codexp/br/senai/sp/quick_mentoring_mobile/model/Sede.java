package codexp.br.senai.sp.quick_mentoring_mobile.model;

/**
 * Created by Helena Strada on 22/03/2018.
 */

public class Sede {

    private int sedeId;
    private String nome;

    public Sede(int sedeId, String nome) {
        this.sedeId = sedeId;
        this.nome = nome;
    }

    public Sede() {

    }

    public Sede(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Nome da Sede: " + nome;
    }

    public int getSedeId() {
        return sedeId;
    }

    public void setSedeId(int sedeId) {
        this.sedeId = sedeId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
