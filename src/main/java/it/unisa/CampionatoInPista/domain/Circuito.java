package it.unisa.CampionatoInPista.domain;

public class Circuito {
    //CIRCUITO (ID, Nome, PaeseResidenza, Lunghezza, #Curve)
    private String ID; //(Primary Key)
    private String nome;
    private String paeseResidenza;
    private int lunghezza;
    private int numero_Curve;

    public Circuito() {
    }

    public Circuito(String ID, String nome, String paeseResidenza, int lunghezza, int numero_Curve) {
        this.ID = ID;
        this.nome = nome;
        this.paeseResidenza = paeseResidenza;
        this.lunghezza = lunghezza;
        this.numero_Curve = numero_Curve;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPaeseResidenza() {
        return paeseResidenza;
    }

    public void setPaeseResidenza(String paeseResidenza) {
        this.paeseResidenza = paeseResidenza;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public void setLunghezza(int lunghezza) {
        this.lunghezza = lunghezza;
    }

    public int getNumero_Curve() {
        return numero_Curve;
    }

    public void setNumero_Curve(int numero_Curve) {
        this.numero_Curve = numero_Curve;
    }

    @Override
    public String toString() {
        return "Circuito{" +
                "ID='" + ID + '\'' +
                ", nome='" + nome + '\'' +
                ", paeseResidenza='" + paeseResidenza + '\'' +
                ", lunghezza=" + lunghezza +
                ", numero_Curve=" + numero_Curve +
                '}';
    }
}
