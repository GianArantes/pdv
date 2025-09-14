package br.com.arantesrepresentacoes.pdv.entities;

public enum UserRole {
    ADMIN("admin"),
    GERENTE("gerente"),
    REPRESENTANTE("representante"),
    CLIENTE("cliente");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

}
