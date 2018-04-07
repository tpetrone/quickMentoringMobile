package codexp.br.senai.sp.quick_mentoring_mobile.model;

/**
 * Created by Helena Strada on 22/03/2018.
 */

public class Categoria {

    private int id;
    private String nome;

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria() {

    }

    @Override
    public String toString() {
        return "Categoria: " + nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
