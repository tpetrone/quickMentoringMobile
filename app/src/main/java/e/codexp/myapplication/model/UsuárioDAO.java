package e.codexp.myapplication.model;

/**
 * Created by CodeXP on 21/03/2018.
 */

public class UsuárioDAO {
    public static UsuárioDAO instance = new UsuárioDAO();
    private Usuário usuário;

    private long id = 0;

    private UsuárioDAO() {
        usuário = new Usuário(
                id++,
                "email",
                "senha",
                "mentor",
                null
        );
    }

    public void salvar(Usuário obj) {
        usuário = obj;
    }

    public Usuário localizar() {
        return usuário;
    }

}
