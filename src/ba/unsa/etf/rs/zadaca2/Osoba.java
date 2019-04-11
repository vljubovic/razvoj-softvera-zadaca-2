package ba.unsa.etf.rs.zadaca2;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Osoba {
    private SimpleStringProperty ime;
    private SimpleStringProperty prezime;
    private SimpleStringProperty ulica;
    private SimpleIntegerProperty postanskiBroj;
    private SimpleStringProperty grad;
    private SimpleObjectProperty<LocalDate> rodjendan;

    /**
     * Default constructor.
     */
    public Osoba() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param ime
     * @param prezime
     */
    public Osoba(String ime, String prezime) {
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);

        // Inicijalni dummy podaci
        this.ulica = new SimpleStringProperty("neka ulica");
        this.postanskiBroj = new SimpleIntegerProperty(1234);
        this.grad = new SimpleStringProperty("neki grad");
        this.rodjendan = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public Osoba(String ime, String prezime, String ulica, int postanskiBroj, String grad, LocalDate rodjendan) {
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.ulica = new SimpleStringProperty(ulica);
        this.postanskiBroj = new SimpleIntegerProperty(postanskiBroj);
        this.grad = new SimpleStringProperty(grad);
        this.rodjendan = new SimpleObjectProperty<LocalDate>(rodjendan);
    }


    public String getIme() {
        return ime.get();
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public SimpleStringProperty imeProperty() {
        return ime;
    }


    public String getPrezime() {
        return prezime.get();
    }

    public void setPrezime(String prezime) {
        this.prezime.set(prezime);
    }

    public SimpleStringProperty prezimeProperty() {
        return prezime;
    }


    public String getUlica() {
        return ulica.get();
    }

    public void setUlica(String ulica) {
        this.ulica.set(ulica);
    }

    public SimpleStringProperty ulicaProperty() {
        return ulica;
    }


    public int getPostanskiBroj() {
        return postanskiBroj.get();
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj.set(postanskiBroj);
    }

    public SimpleIntegerProperty postanskiBrojProperty() {
        return postanskiBroj;
    }


    public String getGrad() {
        return grad.get();
    }

    public void setGrad(String grad) {
        this.grad.set(grad);
    }

    public SimpleStringProperty gradProperty() {
        return grad;
    }


    public LocalDate getRodjendan() {
        return rodjendan.get();
    }

    public void setRodjendan(LocalDate rodjendan) {
        this.rodjendan.set(rodjendan);
    }

    public SimpleObjectProperty<LocalDate> rodjendanProperty() {
        return rodjendan;
    }
}
