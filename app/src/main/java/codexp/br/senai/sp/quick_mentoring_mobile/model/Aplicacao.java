package codexp.br.senai.sp.quick_mentoring_mobile.model;

/**
 * Created by tpetrone on 03/04/18.
 */

public class Aplicacao {
    private int id;
    private Usuario usuario;
    private Mentoria mentoria;
    private String formulario;

    private int usuarioId;
    private int mentoriaId;

    @Override
    public String toString() {
        return "Aplicacao{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", mentoriaId=" + mentoriaId +
                ", formaulario=" + formulario +
                '}';
    }

    public Aplicacao(Usuario usuario, Mentoria mentoria, String formulario) {
        this.usuario = usuario;
        this.mentoria = mentoria;
        this.formulario = formulario;
    }

    public int getId() {
        return id;
    }

    public String getMentoradoNome() { return usuario.getPerfil().getNome(); }
}
