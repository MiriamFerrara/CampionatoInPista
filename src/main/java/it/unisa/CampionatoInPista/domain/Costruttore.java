package it.unisa.CampionatoInPista.domain;

public class Costruttore {
    private String nome;
    private String ragioneSociale;
    private String sedeFabbrica;
    private Integer numComponenti;

    public Costruttore() {
    }

    /**
     * Costruttore
     * @param nome
     * @param ragioneSociale
     * @param sedeFabbrica
     * @param numComponenti
     */
    public Costruttore(String nome, String ragioneSociale, String sedeFabbrica, Integer numComponenti) {
        this.nome = nome;
        this.ragioneSociale = ragioneSociale;
        this.sedeFabbrica = sedeFabbrica;
        this.numComponenti = numComponenti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getSedeFabbrica() {
        return sedeFabbrica;
    }

    public void setSedeFabbrica(String sedeFabbrica) {
        this.sedeFabbrica = sedeFabbrica;
    }

    public Integer getNumComponenti() {
        return numComponenti;
    }

    public void setNumComponenti(Integer numComponenti) {
        this.numComponenti = numComponenti;
    }

    @Override
    public String toString() {
        return "Costruttore{" +
                "nome='" + nome + '\'' +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", sedeFabbrica='" + sedeFabbrica + '\'' +
                ", numComponenti=" + numComponenti +
                '}';
    }
}