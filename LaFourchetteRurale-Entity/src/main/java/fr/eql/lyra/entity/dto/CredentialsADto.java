package fr.eql.lyra.entity.dto;

import java.io.Serializable;

public class CredentialsADto implements Serializable {

    private String username;
    private String password;

    /// Constructeur vide (par défaut ici) nécessaire pour la réification ///

    /// Getters ///
    public String getUsername() { return username;}
    public String getPassword() { return password;}

    /// Setters (pour réifier) ///
    public void setUsername(String username) { this.username = username;}
    public void setPassword(String password) { this.password = password;}
}
