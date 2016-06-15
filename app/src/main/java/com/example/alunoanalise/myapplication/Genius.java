package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */
import java.util.ArrayList;
import java.util.List;

public class Genius {

    private long id;
    private String nome;
    private String email;
    private String telefone;
    private List lista = new ArrayList();

    public Genius(long id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
    public Genius() {
        this.id = 0;
        this.nome = "";
        this.email = "";
        this.telefone = "";

    }
    public List<Integer> ListaGenius(List lista){

        this.lista.add(getId()+", "+getNome()+", " + getEmail()+", " + getTelefone());

        return lista;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
