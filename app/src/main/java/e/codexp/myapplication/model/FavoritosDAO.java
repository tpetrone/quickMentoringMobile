package e.codexp.myapplication.model;

/**
 * Created by CodeXP on 22/03/2018.
 */

public class FavoritosDAO {
    public static FavoritosDAO instance = new FavoritosDAO();
    private Favoritos Favoritos;
    private long id = 0;

    private FavoritosDAO() {
        Favoritos = new Favoritos(
                id++,
                "Nome do usuário",
                "Inglês"
        );
    }
    public void salvar(Favoritos obj) {Favoritos = obj;}
    public Favoritos localizar() {return Favoritos;}
}
