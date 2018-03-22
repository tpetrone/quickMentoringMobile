package e.codexp.myapplication.model;

/**
 * Created by tpetrone on 19/03/18.
 */

public class PerfilDAO {
    public static PerfilDAO instance = new PerfilDAO();
    private Perfil perfil;

    private long id = 0;

    private PerfilDAO() {
        perfil = new Perfil(
            id++,
            "Nome da pessoa",
            "Bio da pessoa",
            "Masculino",
            "São Paulo",
            "1984"
        );
    }

    public void salvar(Perfil obj) {
        perfil = obj;
    }

    public Perfil localizar() {
        return perfil;
    }
}
