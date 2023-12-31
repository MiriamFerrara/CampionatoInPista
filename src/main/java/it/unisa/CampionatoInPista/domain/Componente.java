package it.unisa.CampionatoInPista.domain;


import java.sql.Date;

public class Componente {
    private int codice;
    private Date dataInstallazione;
    private float costo;
    private String tipoComponente;
    private String tipoMateriale;
    private Float peso;
    private String numMarce;
    private String cilindrata;
    private String tipoMotore;
    private Integer numCilindri;
    private String nomeCostruttore;
    private String targaVettura;

    public Componente() {
    }

    /**
     * Componente
     * @param codice
     * @param dataInstallazione
     * @param costo
     * @param tipoComponente
     * @param tipoMateriale
     * @param peso
     * @param numMarce
     * @param cilindrata
     * @param tipoMotore
     * @param numCilindri
     * @param nomeCostruttore
     * @param targaVettura
     */
    public Componente(int codice, Date dataInstallazione, float costo, String tipoComponente, String tipoMateriale, Float peso, String numMarce, String cilindrata, String tipoMotore, Integer numCilindri, String nomeCostruttore, String targaVettura) {
        this.codice = codice;
        this.dataInstallazione = dataInstallazione;
        this.costo = costo;
        this.tipoComponente = tipoComponente;
        this.tipoMateriale = tipoMateriale;
        this.peso = peso;
        this.numMarce = numMarce;
        this.cilindrata = cilindrata;
        this.tipoMotore = tipoMotore;
        this.numCilindri = numCilindri;
        this.nomeCostruttore = nomeCostruttore;
        this.targaVettura = targaVettura;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public Date getDataInstallazione() {
        return dataInstallazione;
    }

    public void setDataInstallazione(Date dataInstallazione) {
        this.dataInstallazione = dataInstallazione;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getTipoComponente() {
        return tipoComponente;
    }

    public void setTipoComponente(String tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    public String getTipoMateriale() {
        return tipoMateriale;
    }

    public void setTipoMateriale(String tipoMateriale) {
        this.tipoMateriale = tipoMateriale;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getNumMarce() {
        return numMarce;
    }

    public void setNumMarce(String numMarce) {
        this.numMarce = numMarce;
    }

    public String getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(String cilindrata) {
        this.cilindrata = cilindrata;
    }

    public String getTipoMotore() {
        return tipoMotore;
    }

    public void setTipoMotore(String tipoMotore) {
        this.tipoMotore = tipoMotore;
    }

    public Integer getNumCilindri() {
        return numCilindri;
    }

    public void setNumCilindri(Integer numCilindri) {
        this.numCilindri = numCilindri;
    }

    public String getNomeCostruttore() {
        return nomeCostruttore;
    }

    public void setNomeCostruttore(String nomeCostruttore) {
        this.nomeCostruttore = nomeCostruttore;
    }

    public String getTargaVettura() {
        return targaVettura;
    }

    public void setTargaVettura(String targaVettura) {
        this.targaVettura = targaVettura;
    }

    @Override
    public String toString() {
        return "Componente{" +
                "codice=" + codice +
                ", dataInstallazione=" + dataInstallazione +
                ", costo=" + costo +
                ", tipoComponente='" + tipoComponente + '\'' +
                ", tipoMateriale='" + tipoMateriale + '\'' +
                ", peso=" + peso +
                ", numMarce='" + numMarce + '\'' +
                ", cilindrata='" + cilindrata + '\'' +
                ", tipoMotore='" + tipoMotore + '\'' +
                ", numCilindri=" + numCilindri +
                ", nomeCostruttore='" + nomeCostruttore + '\'' +
                ", targaVettura='" + targaVettura + '\'' +
                '}';
    }
}