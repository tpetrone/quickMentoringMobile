package e.codexp.myapplication.model;

/**
 * Created by CodeXP on 20/03/2018.
 */

public class CriarMentoriaDAO {
    public static CriarMentoriaDAO instance = new CriarMentoriaDAO();
    private CriarMentoria CriarMentoria;

    private long id = 0;

    private CriarMentoriaDAO() {
        CriarMentoria = new CriarMentoria(
                id++,
                "Nome da Mentoria",
                "inglês",
                "Aulas Online",
                "Aulas Presenciais",
                "Endereço",
                ,
                20,
                "sobre",
                "Individual",
                "Em grupo"
    );
    }
    public void salvar(CriarMentoria obj){
        CriarMentoria = obj;
    }
    public CriarMentoria localizar(){
        return CriarMentoria;
    }
}

