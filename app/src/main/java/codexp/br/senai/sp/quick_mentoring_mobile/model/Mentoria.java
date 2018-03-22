package codexp.br.senai.sp.quick_mentoring_mobile.model;

/**
 * Created by Helena Strada on 22/03/2018.
 */

public class Mentoria {

    private int mentoriaId;
    private Usuario usuario;
    private Categoria categoria;
    private boolean ativa;
    private boolean online;
    private String nome;
    private Sede sede;

    // para fazer o post
    private int usuarioid;
    private int categoriaid;
    private int sedeid;

    public Mentoria() {

    }

    @Override
    public String toString() {
        return "Mentoria{" +
                "mentoriaId=" + mentoriaId +
                ", usuario=" + usuario +
                ", categoria=" + categoria +
                ", ativa=" + ativa +
                ", online=" + online +
                ", nome='" + nome + '\'' +
                ", sede=" + sede +
                ", usuarioid=" + usuarioid +
                ", categoriaid=" + categoriaid +
                ", sedeid=" + sedeid +
                '}';
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public int getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(int categoriaid) {
        this.categoriaid = categoriaid;
    }

    public int getSedeid() {
        return sedeid;
    }

    public void setSedeid(int sedeid) {
        this.sedeid = sedeid;
    }

    public Mentoria(boolean ativa, boolean online, String nome, int usuarioid, int categoriaid, int sedeid) {
        this.ativa = ativa;
        this.online = online;
        this.nome = nome;
        this.usuarioid = usuarioid;
        this.categoriaid = categoriaid;
        this.sedeid = sedeid;
    }

    public Mentoria(Usuario usuario, Categoria categoria, boolean ativa, boolean online, String nome, Sede sede) {
        this.usuario = usuario;
        this.categoria = categoria;
        this.ativa = ativa;
        this.online = online;
        this.nome = nome;
        this.sede = sede;
    }

    public int getMentoriaId() {
        return mentoriaId;
    }

    public void setMentoriaId(int mentoriaId) {
        this.mentoriaId = mentoriaId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Mentoria(int mentoriaId, Usuario usuario, Categoria categoria, boolean ativa, boolean online, String nome, Sede sede) {
        this.mentoriaId = mentoriaId;
        this.usuario = usuario;
        this.categoria = categoria;
        this.ativa = ativa;
        this.online = online;
        this.nome = nome;
        this.sede = sede;
    }
}
