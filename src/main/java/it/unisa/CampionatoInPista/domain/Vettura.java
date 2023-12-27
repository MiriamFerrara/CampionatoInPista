package it.unisa.CampionatoInPista.domain;

public class Vettura {
    private String targa;

    private Integer numGara;
    private String modello;

    public Vettura() {
    }

    /**
     * Vettura
     * @param targa
     * @param numGara
     * @param modello
     */
    public Vettura(String targa, Integer numGara, String modello) {
        this.targa = targa;
        this.numGara = numGara;
        this.modello = modello;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public Integer getNumGara() {
        return numGara;
    }

    public void setNumGara(Integer numGara) {
        this.numGara = numGara;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }
}
