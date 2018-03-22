package e.codexp.myapplication.model;

import java.text.SimpleDateFormat;

/**
 * Created by CodeXP on 20/03/2018.
 */

public class MentoriaDAO {
    public static MentoriaDAO instance = new MentoriaDAO();
    private Mentoria Mentoria;

    private long id = 0;

    private MentoriaDAO() {
        Mentoria = new Mentoria(
                id++,
                "Nome da Mentoria",
                "inglês",
                "Aulas Online",
                "Aulas Presenciais",
                "Endereço",
                null,
                20,
                "sobre",
                "Individual",
                "Em grupo"
    );
    }
    public void salvar(Mentoria obj){
        Mentoria = obj;
    }
    public Mentoria localizar(){
        return Mentoria;
    }
}

