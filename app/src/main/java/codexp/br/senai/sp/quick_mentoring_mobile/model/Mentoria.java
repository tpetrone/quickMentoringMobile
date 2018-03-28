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
    private int usuarioId;
    private int categoriaId;
    private int sedeId;

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
                ", usuarioId=" + usuarioId +
                ", categoriaId=" + categoriaId +
                ", sedeId=" + sedeId +
                '}';
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getSedeId() {
        return sedeId;
    }

    public void setSedeId(int sedeId) {
        this.sedeId = sedeId;
    }

    public Mentoria(boolean ativa, boolean online, String nome, int usuarioId, int categoriaId, int sedeId) {
        this.ativa = ativa;
        this.online = online;
        this.nome = nome;
        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
        this.sedeId = sedeId;
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
