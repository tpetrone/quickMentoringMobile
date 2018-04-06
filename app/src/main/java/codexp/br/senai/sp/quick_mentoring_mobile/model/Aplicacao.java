package codexp.br.senai.sp.quick_mentoring_mobile.model;

/**
 * Created by tpetrone on 03/04/18.
 */

public class Aplicacao {

    // Para fazer o GET
    private int id;
    private Usuario usuario;
    private Mentoria mentoria;
    private String justificativa;
    private boolean aceite;

    public Aplicacao(Usuario usuario, Mentoria mentoria, String justificativa, boolean aceite) {
        this.usuario = usuario;
        this.mentoria = mentoria;
        this.aceite = aceite;
        this.justificativa = justificativa;
    }

    // Para fazer o POST
    private int usuarioId;
    private int mentoriaId;

    public Aplicacao(int usuarioId, int mentoriaId, String justificativa) {
        this.usuarioId = usuarioId;
        this.mentoriaId = mentoriaId;
        this.justificativa = justificativa;
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
                ", justificativa=" + justificativa +
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

    public String getJustificativa() { return justificativa; }

    public int getMentoriaId() {
        return mentoriaId;
    }
}
