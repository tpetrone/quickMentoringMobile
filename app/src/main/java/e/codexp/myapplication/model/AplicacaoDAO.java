package e.codexp.myapplication.model;

/**
 * Created by CodeXP on 22/03/2018.
 */

public class AplicacaoDAO {
    public static AplicacaoDAO instance = new AplicacaoDAO();
    private Aplicacao Aplicacao;
    private long id = 0;
    private AplicacaoDAO(){
        Aplicacao = new Aplicacao(
                id++,
                "Nome do usuário",
                "Nome da mentoria",
                "Lorem ipsum"
        );
    }
    public void salvar(Aplicacao obj){Aplicacao = obj;}
    public Aplicacao localizar(){return Aplicacao;}
}
