package it.unisa.CampionatoInPista.domain;

import java.sql.Date;

public class Finanziare {
    private float quantitaDenaro;
    private Date data;
    private int idPilota;
    private String nomeScuderia;

    public Finanziare() {
    }


    /**
     * Finanziare
     * @param quantitaDenaro
     * @param data
     * @param idPilota
     * @param nomeScuderia
     */
    public Finanziare(float quantitaDenaro, Date data, int idPilota, String nomeScuderia) {
        this.quantitaDenaro = quantitaDenaro;
        this.data = data;
        this.idPilota = idPilota;
        this.nomeScuderia = nomeScuderia;
    }

    public float getQuantitaDenaro() {
        return quantitaDenaro;
    }

    public void setQuantitaDenaro(float quantitaDenaro) {
        this.quantitaDenaro = quantitaDenaro;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdPilota() {
        return idPilota;
    }

    public void setIdPilota(int idPilota) {
        this.idPilota = idPilota;
    }

    public String getNomeScuderia() {
        return nomeScuderia;
    }

    public void setNomeScuderia(String nomeScuderia) {
        this.nomeScuderia = nomeScuderia;
    }

    @Override
    public String toString() {
        return "Finanziare{" +
                "quantitaDenaro=" + quantitaDenaro +
                ", data=" + data +
                ", idPilota=" + idPilota +
                ", nomeScuderia='" + nomeScuderia + '\'' +
                '}';
    }
}
