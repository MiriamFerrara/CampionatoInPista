package it.unisa.CampionatoInPista.domain;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Gara {
   //GARA (ID, Nome, DataEvento, DurataOre, TipoGara(asciutta o bagnata), ID_CIRCUITO)

    private int id;
    private String nome;
    private Date dataEvento;
    private Time durataOre;
    private String tipoGara;
    private int idCircuito;

    public Gara() {
    }

    public Gara(int id, String nome, Date dataEvento, Time durataOre, String tipoGara, int idCircuito) {
        this.id = id;
        this.nome = nome;
        this.dataEvento = dataEvento;
        this.durataOre = durataOre;
        this.tipoGara = tipoGara;
        this.idCircuito = idCircuito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Time getDurataOre() {
        return durataOre;
    }

    public void setDurataOre(Time durataOre) {
        this.durataOre = durataOre;
    }

    public String getTipoGara() {
        return tipoGara;
    }

    public void setTipoGara(String tipoGara) {
        this.tipoGara = tipoGara;
    }

    public int getIdCircuito() {
        return idCircuito;
    }

    public void setIdCircuito(int idCircuito) {
        this.idCircuito = idCircuito;
    }

    @Override
    public String toString() {
        return "Gara{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataEvento=" + dataEvento +
                ", durataOre=" + durataOre +
                ", tipoGara='" + tipoGara + '\'' +
                ", idCircuito=" + idCircuito +
                '}';
    }
}
