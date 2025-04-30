package com.mediaportal.mediaxpusers.models;


import javax.persistence.*;

@Entity
@Table(name = "tvci_identidade")
public class DadosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "assetid")
    private Long assetid;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAssetid() {
        return assetid;
    }

    public void setAssetid(Long assetid) {
        this.assetid = assetid;
    }
}
