package codexp.br.senai.sp.quick_mentoring_mobile.model;

/**
 * Created by Helena Strada on 22/03/2018.
 */

public class Categoria {

    private int categoriaId;
    private String nome;

    public Categoria(int categoriaId, String nome) {
        this.categoriaId = categoriaId;
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

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
