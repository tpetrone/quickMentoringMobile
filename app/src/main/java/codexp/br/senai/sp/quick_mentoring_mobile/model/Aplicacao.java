package codexp.br.senai.sp.quick_mentoring_mobile.model;

/**
 * Created by tpetrone on 03/04/18.
 */

public class Aplicacao {

    // Para fazer o GET
    private int id;
    private Usuario usuario;
    private Mentoria mentoria;
    private String formulario;
    private boolean aceite;

    public Aplicacao(Usuario usuario, Mentoria mentoria, String formulario, boolean aceite) {
        this.usuario = usuario;
        this.mentoria = mentoria;
        this.aceite = aceite;
        this.formulario = formulario;
    }

    // Para fazer o POST
    private int usuarioId;
    private int mentoriaId;

    public Aplicacao(int usuarioId, int mentoriaId, String formulario) {
        this.usuarioId = usuarioId;
        this.mentoriaId = mentoriaId;
        this.formulario = formulario;
    }

    //Para fazer o PATCH
    public Aplicacao(boolean aceite) {
        this.aceite = aceite;
    }

    @Override
    public String toString() {
        return "Aplicacao{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", mentoriaId=" + mentoriaId +
                ", formulario=" + formulario +
                ", aceite=" + aceite +
                '}';
    }

    public int getId() {
        return id;
    }

    public boolean getAceite() { return aceite; }

    public String getMentoradoNome() { return usuario.getPerfil().getNome(); }

    public Usuario getUsuario() {
        return usuario;
    }

    public Mentoria getMentoria() {return mentoria;}

    public String getFormulario() { return formulario; }
}
