package codexp.br.senai.sp.quick_mentoring_mobile.model;

/**
 * Created by Helena Strada on 22/03/2018.
 */

public class Usuario {

    private int id;
    private String email;
    private String password;
    private String role;
    private boolean ativo;
    private Perfil perfil;

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", ativo=" + ativo +
                ", perfil=" + perfil +
                '}';
    }

    public Usuario(String email, String password, String role, boolean ativo, Perfil perfil) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.ativo = ativo;
        this.perfil = perfil;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Usuario(String email, String password, String role, boolean ativo) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.ativo = ativo;
    }
}
