package ro.ase.pdm.sub1;

import java.io.Serializable;

public class HomeExchange implements Serializable {
    public enum TipLocuinta{
        Casa, Apartament;
    }

    private String adresa;
    private int nrCamere;
    private float suprafata;
    private String perioada;
    private TipLocuinta tipLocuinta;

    public HomeExchange(){

    }

    public HomeExchange(String adresa, int nrCamere, float suprafata, String perioada, TipLocuinta tipLocuinta) {
        this.adresa = adresa;
        this.nrCamere = nrCamere;
        this.suprafata = suprafata;
        this.perioada = perioada;
        this.tipLocuinta = tipLocuinta;
    }


    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getNrCamere() {
        return nrCamere;
    }

    public void setNrCamere(int nrCamere) {
        this.nrCamere = nrCamere;
    }

    public float getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(float suprafata) {
        this.suprafata = suprafata;
    }

    public String getPerioada() {
        return perioada;
    }

    public void setPerioada(String perioada) {
        this.perioada = perioada;
    }

    public TipLocuinta getTipLocuinta() {
        return tipLocuinta;
    }

    public void setTipLocuinta(TipLocuinta tipLocuinta) {
        this.tipLocuinta = tipLocuinta;
    }

    @Override
    public String toString() {
        return "HomeExchange{" +
                "adresa='" + adresa + '\'' +
                ", nrCamere=" + nrCamere +
                ", suprafata=" + suprafata +
                ", perioada='" + perioada + '\'' +
                ", tipLocuinta=" + tipLocuinta +
                '}';
    }
}
