package e.codexp.myapplication.model;

/**
 * Created by CodeXP on 21/03/2018.
 */

public class CategoriaDAO {
    public static CategoriaDAO instance = new CategoriaDAO();
    private Categoria Categoria;

    private long id = 0;

    private CategoriaDAO() {
        Categoria = new Categoria(
                id++,
                "Nome da Mentoria");
    }
}
