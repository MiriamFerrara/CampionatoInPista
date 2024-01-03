package it.unisa.CampionatoInPista.domain;

import java.sql.Date;

public class Pilota {
    private int ID;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String nazionalita;
    private String tipoPilota;
    private int licenzePossedute;
    private Date dataLicenza;
    private boolean finanziatoreGD;

    public Pilota() {
    }

    /**
     * Pilota
     * @param ID
     * @param nome
     * @param cognome
     * @param dataNascita
     * @param nazionalita
     * @param tipoPilota
     * @param licenzePossedute
     * @param dataLicenza
     * @param finanziatoreGD
     */
    public Pilota(int ID, String nome, String cognome, Date dataNascita, String nazionalita, String tipoPilota, int licenzePossedute, Date dataLicenza, boolean finanziatoreGD) {
        this.ID = ID;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.nazionalita = nazionalita;
        this.tipoPilota = tipoPilota;
        this.licenzePossedute = licenzePossedute;
        this.dataLicenza = dataLicenza;
        this.finanziatoreGD = finanziatoreGD;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public String getTipoPilota() {
        return tipoPilota;
    }

    public void setTipoPilota(String tipoPilota) {
        this.tipoPilota = tipoPilota;
    }

    public int getLicenzePossedute() {
        return licenzePossedute;
    }

    public void setLicenzePossedute(int licenzePossedute) {
        this.licenzePossedute = licenzePossedute;
    }

    public Date getDataLicenza() {
        return dataLicenza;
    }

    public void setDataLicenza(Date dataLicenza) {
        this.dataLicenza = dataLicenza;
    }

    public boolean isFinanziatoreGD() {
        return finanziatoreGD;
    }

    public void setFinanziatoreGD(boolean finanziatoreGD) {
        this.finanziatoreGD = finanziatoreGD;
    }

    @Override
    public String toString() {
        return "Pilota{" +
                "ID=" + ID +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", nazionalita='" + nazionalita + '\'' +
                ", tipoPilota='" + tipoPilota + '\'' +
                ", licenzePossedute=" + licenzePossedute +
                ", dataLicenza=" + dataLicenza +
                ", finanziatoreGD=" + finanziatoreGD +
                '}';
    }
}
