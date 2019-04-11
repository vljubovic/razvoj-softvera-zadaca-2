package ba.unsa.etf.rs.zadaca2;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OsobeModel {
    private ObservableList<Osoba> osobe = FXCollections.observableArrayList();
    private SimpleObjectProperty<Osoba> trenutnaOsoba = new SimpleObjectProperty();

    public OsobeModel() {
    }

    public void napuni() {
        osobe.add(new Osoba("Semso", "Semsic"));
        osobe.add(new Osoba("Hamo", "Hamic"));
        osobe.add(new Osoba("Nedo", "Nedic"));
        osobe.add(new Osoba("Mirso", "Mirsic"));
        osobe.add(new Osoba("Vlasta", "Vlastic"));
        osobe.add(new Osoba("Milada", "Miladic"));
        osobe.add(new Osoba("Fata", "Fatic"));
        osobe.add(new Osoba("Hamida", "Hamidic"));
        osobe.add(new Osoba("Pero", "Peric"));
        trenutnaOsoba.set(osobe.get(0));
    }

    public ObservableList<Osoba> getOsobe() {
        return osobe;
    }

    public void setOsobe(ObservableList<Osoba> osobe) {
        this.osobe = osobe;
    }

    public Osoba getTrenutnaOsoba() {
        return trenutnaOsoba.get();
    }

    public SimpleObjectProperty<Osoba> trenutnaOsoba() {
        return trenutnaOsoba;
    }

    public void setTrenutnaOsoba(Osoba trenutnaOsoba) {
        this.trenutnaOsoba.set(trenutnaOsoba);
    }

    public void setTrenutnaOsoba(int i) {
        this.trenutnaOsoba.set(osobe.get(i));
    }
}
