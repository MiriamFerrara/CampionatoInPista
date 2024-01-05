package it.unisa.CampionatoInPista.domain;

public class Partecipa {
    private int idGara;
    private String targaVettura;
    private String motivoRitiro;
    private int punti;

    public Partecipa() {
    }

    /**
     * Partecipa
     * @param idGara
     * @param targaVettura
     * @param motivoRitiro
     * @param punti
     */
    public Partecipa(int idGara, String targaVettura, String motivoRitiro, int punti) {
        this.idGara = idGara;
        this.targaVettura = targaVettura;
        this.motivoRitiro = motivoRitiro;
        this.punti = punti;
    }

    public int getIdGara() {
        return idGara;
    }

    public void setIdGara(int idGara) {
        this.idGara = idGara;
    }

    public String getTargaVettura() {
        return targaVettura;
    }

    public void setTargaVettura(String targaVettura) {
        this.targaVettura = targaVettura;
    }

    public String getMotivoRitiro() {
        return motivoRitiro;
    }

    public void setMotivoRitiro(String motivoRitiro) {
        this.motivoRitiro = motivoRitiro;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    @Override
    public String toString() {
        return "Partecipa{" +
                "idGara=" + idGara +
                ", targaVettura='" + targaVettura + '\'' +
                ", motivoRitiro='" + motivoRitiro + '\'' +
                ", punti=" + punti +
                '}';
    }
}
