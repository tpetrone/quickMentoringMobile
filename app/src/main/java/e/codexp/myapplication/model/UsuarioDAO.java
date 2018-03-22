package e.codexp.myapplication.model;

/**
 * Created by CodeXP on 21/03/2018.
 */

public class UsuarioDAO {
    public static UsuarioDAO instance = new UsuarioDAO();
    private Usuario usuario;

    private long id = 0;
    
    private UsuarioDAO() {
        usuario = new Usuario(
                id++,
                "email",
                "senha",
                "mentor",
                null
        );
    }

    public void salvar(Usuario obj) {
        usuario = obj;
    }

    public Usuario localizar() {
        return usuario;
    }

}
