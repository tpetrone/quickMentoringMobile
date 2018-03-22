package e.codexp.myapplication.model;

/**
 * Created by CodeXP on 21/03/2018.
 */

public class Usuario {
    private Long id;
    private String email;
    private String senha;
    private String role;
    private Boolean ativo;
    private String ano;
    private byte[] foto;

    public Usuario() {}
    public Usuario(Long id) { this.id = id; }
    public Usuario(Long id, String email, String senha, String role, Boolean ativo) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = true; }
}
