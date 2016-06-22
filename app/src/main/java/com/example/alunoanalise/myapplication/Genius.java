package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */
import java.util.ArrayList;
import java.util.List;

public class Genius {

    private long id;
    private int fase;
    private int seq_1;
    private int seq_2;
    private int seq_3;
    private int seq_4;
    private int seq_5;
    private int seq_6;
    private int seq_7;
    private int seq_8;
    private List lista = new ArrayList();

    public Genius(long id, int fase, int seq_1, int seq_2, int seq_3, int seq_4, int seq_5, int seq_6, int seq_7, int seq_8) {
        this.id = id;
        this.fase = fase;
        this.seq_1 = seq_1;
        this.seq_2 = seq_2;
        this.seq_3 = seq_3;
        this.seq_4 = seq_4;
        this.seq_5 = seq_5;
        this.seq_6 = seq_6;
        this.seq_7 = seq_7;
        this.seq_8 = seq_8;
    }
    public Genius() {
        this.id = 0;
        this.fase = 0;
        this.seq_1 = 0;
        this.seq_2 = 0;
        this.seq_3 = 0;
        this.seq_4 = 0;
        this.seq_5 = 0;
        this.seq_6 = 0;
        this.seq_7 = 0;
        this.seq_8 = 0;

    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }

    public List<Integer> ListaGenius(List lista){

        this.lista.add(getId() + ", " + getFase() + ", " + getSeq_1() + ", " + getSeq_2() + ", " + getSeq_3() + ", " + getSeq_4() + ", " + getSeq_5() + ", " + getSeq_6() + ", " + getSeq_7() + ", " + getSeq_8());

        return lista;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSeq_1() {
        return seq_1;
    }

    public void setSeq_1(int seq_1) {
        this.seq_1 = seq_1;
    }

    public int getSeq_2() {
        return seq_2;
    }

    public void setSeq_2(int seq_2) {
        this.seq_2 = seq_2;
    }

    public int getSeq_3() {
        return seq_3;
    }

    public void setSeq_3(int seq_3) {
        this.seq_3 = seq_3;
    }

    public int getSeq_4() {
        return seq_4;
    }

    public void setSeq_4(int seq_4) {
        this.seq_4 = seq_4;
    }

    public int getSeq_5() {
        return seq_5;
    }

    public void setSeq_5(int seq_5) {
        this.seq_5 = seq_5;
    }

    public int getSeq_6() {
        return seq_6;
    }

    public void setSeq_6(int seq_6) {
        this.seq_6 = seq_6;
    }

    public int getSeq_7() {
        return seq_7;
    }

    public void setSeq_7(int seq_7) {
        this.seq_7 = seq_7;
    }

    public int getSeq_8() {
        return seq_8;
    }

    public void setSeq_8(int seq_8) {
        this.seq_8 = seq_8;
    }
}
