package e.codexp.myapplication.model;

/**
 * Created by CodeXP on 21/03/2018.
 */

public class Categoria {
    private long id;
    private String categoria;

    public Categoria(){
    }
    public Categoria(Long id){this.id = id;}
    public Categoria(Long id, String categoria){
        this.id = id;
        this.categoria = categoria;
    }
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getCategoria(){return categoria;}
    public void setCategoria(String categoria){this.categoria = categoria;}
}
