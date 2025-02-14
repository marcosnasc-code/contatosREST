package br.com.fiap.contatos.model;

public enum usuario_role {

    ADMIN("admin"),
    USER("user");

    private String role;

    usuario_role(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
