package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */
import java.util.ArrayList;
import java.util.List;

public class Ranking {

    private long id;
    private String nome;
    private int pontos;
    private List lista = new ArrayList();

    public Ranking(long id, String nome, int pontos) {
        this.id = id;
        this.nome = nome;
        this.pontos = pontos;
    }
    public Ranking() {
        this.id = 0;
        this.nome = "";
        this.pontos = 0;

    }
    public List<Integer> ListaRanking(List lista){

        this.lista.add(getId()+", "+getNome()+", " + getPontos());

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

    public double getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }


}
