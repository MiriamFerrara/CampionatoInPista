package it.unisa.CampionatoInPista.domain;

public class Guidare {
    private String targaVettura;
    private int idPilota;
    private int numComponentiPiloti;

    public Guidare() {
    }

    public Guidare(String targaVettura, int idPilota, int numComponentiPiloti) {
        this.targaVettura = targaVettura;
        this.idPilota = idPilota;
        this.numComponentiPiloti = numComponentiPiloti;
    }

    public String getTargaVettura() {
        return targaVettura;
    }

    public void setTargaVettura(String targaVettura) {
        this.targaVettura = targaVettura;
    }

    public int getIdPilota() {
        return idPilota;
    }

    public void setIdPilota(int idPilota) {
        this.idPilota = idPilota;
    }

    public int getNumComponentiPiloti() {
        return numComponentiPiloti;
    }

    public void setNumComponentiPiloti(int numComponentiPiloti) {
        this.numComponentiPiloti = numComponentiPiloti;
    }

    @Override
    public String toString() {
        return "Guidare{" +
                "targaVettura='" + targaVettura + '\'' +
                ", idPilota=" + idPilota +
                ", numComponentiPiloti=" + numComponentiPiloti +
                '}';
    }
}
