package it.unisa.CampionatoInPista.domain;

public class Scuderia {
    private String nome;
    private String sede;
    private String paese;
    private int numFinanziamenti;
    private String targaVettura;

    public Scuderia() {
    }

    /**
     * Scuderia
     * @param nome
     * @param sede
     * @param paese
     * @param numFinanziamenti
     * @param targaVettura
     */
    public Scuderia(String nome, String sede, String paese, int numFinanziamenti, String targaVettura) {
        this.nome = nome;
        this.sede = sede;
        this.paese = paese;
        this.numFinanziamenti = numFinanziamenti;
        this.targaVettura = targaVettura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public int getNumFinanziamenti() {
        return numFinanziamenti;
    }

    public void setNumFinanziamenti(int numFinanziamenti) {
        this.numFinanziamenti = numFinanziamenti;
    }

    public String getTargaVettura() {
        return targaVettura;
    }

    public void setTargaVettura(String targaVettura) {
        this.targaVettura = targaVettura;
    }

    @Override
    public String toString() {
        return "Scuderia{" +
                "nome='" + nome + '\'' +
                ", sede='" + sede + '\'' +
                ", paese='" + paese + '\'' +
                ", numFinanziamenti=" + numFinanziamenti +
                ", targaVettura='" + targaVettura + '\'' +
                '}';
    }
}