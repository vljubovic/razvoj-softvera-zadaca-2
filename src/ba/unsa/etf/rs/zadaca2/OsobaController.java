package ba.unsa.etf.rs.zadaca2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;

public class OsobaController {

    public TextField imeText;
    public TextField prezimeText;
    public TextField ulicaText;
    public TextField gradText;
    public TextField postanskiBrojText;
    public TextField rodjendanText;
    public TableView<Osoba> osobaTabela;
    public TableColumn<Osoba, String> imeKolona;
    public TableColumn<Osoba, String> prezimeKolona;

    private OsobeModel model;

    public OsobaController(OsobeModel model) {
        this.model = model;
    }

    @FXML
    public void initialize() {
        // Inicijaliziraj tabelu osoba sa dvije kolone
        imeKolona.setCellFactory(new PropertyValueFactory("ime"));
        prezimeKolona.setCellFactory(new PropertyValueFactory("prezime"));

        //imeKolona.setCellValueFactory(cellData -> cellData.getValue().imeProperty());
        //prezimeKolona.setCellValueFactory(cellData -> cellData.getValue().prezimeProperty());

        // Ocisti detalje o osobi
        //prikaziOsobu(null);

        // Prikazi detalje o osobi koja je selektovana u tabeli
        //osobaTabela.getSelectionModel().selectedItemProperty().addListener(
        //        (observable, oldValue, newValue) -> prikaziOsobu(newValue));

        osobaTabela.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Osoba>(){
            @Override
            public void changed(ObservableValue<? extends Osoba> observableValue, Osoba osoba, Osoba t1) {
                model.setTrenutnaOsoba(t1);
                imeText.textProperty().bindBidirectional(model.getTrenutnaOsoba().imeProperty());
                prezimeText.textProperty().bindBidirectional(model.getTrenutnaOsoba().prezimeProperty());
                ulicaText.textProperty().bindBidirectional(model.getTrenutnaOsoba().ulicaProperty());
                gradText.textProperty().bindBidirectional(model.getTrenutnaOsoba().gradProperty());
                postanskiBrojText.textProperty().bindBidirectional(model.getTrenutnaOsoba().postanskiBrojProperty(), new NumberStringConverter());
            }
        });

        imeText.textProperty().bindBidirectional(model.getTrenutnaOsoba().imeProperty());
        prezimeText.textProperty().bindBidirectional(model.getTrenutnaOsoba().prezimeProperty());
        ulicaText.textProperty().bindBidirectional(model.getTrenutnaOsoba().ulicaProperty());
        gradText.textProperty().bindBidirectional(model.getTrenutnaOsoba().gradProperty());
        postanskiBrojText.textProperty().bindBidirectional(model.getTrenutnaOsoba().postanskiBrojProperty(), new NumberStringConverter());

    }


/*    private void prikaziOsobu(Osoba osoba) {
        if (osoba != null) {
            // Ispuni textFields sa vrijednostima iz modela
            imeText.setText(osoba.getIme());
            prezimeText.setText(osoba.getPrezime());
            ulicaText.setText(osoba.getUlica());
            postanskiBrojText.setText(Integer.toString(osoba.getPostanskiBroj()));
            gradText.setText(osoba.getGrad());
            rodjendanText.setText(DateUtil.format(osoba.getRodjendan()));

        } else {
            // Osoba je null, izbrisi sav tekst
            imeText.setText("");
            prezimeText.setText("");
            ulicaText.setText("");
            postanskiBrojText.setText("");
            gradText.setText("");
            rodjendanText.setText("");
        }
    }*/


    public void obrisiOsobu(ActionEvent actionEvent) {
        int selectedIndex = osobaTabela.getSelectionModel().getSelectedIndex();
        osobaTabela.getItems().remove(selectedIndex);
        /*if (selectedIndex >= 0) {
            osobaTabela.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Greska");
            alert.setHeaderText("Niste izabrali ni jednu osobu iz tabele!");
            alert.setContentText("Odaberite osobu koju zelite izbrisati!");

            alert.showAndWait();
        }*/
    }


    public void dodajOsobu(ActionEvent actionEvent) {
        model.getOsobe().add(model.getTrenutnaOsoba());
        //model.setTrenutniKorisnik(model.getKorisnici().get(model.getKorisnici().size()-1));
        osobaTabela.getSelectionModel().selectLast();

        /*Osoba tempOsoba = new Osoba();
        if (isInputValid()) {
            tempOsoba.setIme(imeText.getText());
            tempOsoba.setPrezime(prezimeText.getText());
            tempOsoba.setUlica(ulicaText.getText());
            tempOsoba.setPostanskiBroj(Integer.parseInt(postanskiBrojText.getText()));
            tempOsoba.setGrad(gradText.getText());
            tempOsoba.setRodjendan(DateUtil.parse(rodjendanText.getText()));

        }
        model.getOsobe().add(tempOsoba);*/
    }

    @FXML
    private void promjeniOsobu(ActionEvent actionEvent) {

        /*Osoba odabranaOsoba = osobaTabela.getSelectionModel().getSelectedItem();
        if (odabranaOsoba != null) {
            if (isInputValid()) {
                odabranaOsoba.setIme(imeText.getText());
                odabranaOsoba.setPrezime(prezimeText.getText());
                odabranaOsoba.setUlica(ulicaText.getText());
                odabranaOsoba.setPostanskiBroj(Integer.parseInt(postanskiBrojText.getText()));
                odabranaOsoba.setGrad(gradText.getText());
                odabranaOsoba.setRodjendan(DateUtil.parse(rodjendanText.getText()));

            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Greska");
            alert.setHeaderText("Niste izabrali ni jednu osobu iz tabele!");
            alert.setContentText("Odaberite osobu koju zelite izbrisati!");

            alert.showAndWait();
        }*/
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (imeText.getText() == null || imeText.getText().length() == 0) {
            errorMessage += "Vrijednost za ime nije ispravna!\n";
        }
        if (prezimeText.getText() == null || prezimeText.getText().length() == 0) {
            errorMessage += "Vrijednost za prezime nije ispravna!\n";
        }
        if (ulicaText.getText() == null || ulicaText.getText().length() == 0) {
            errorMessage += "Vrijednost za ulicu nije ispravna!\n";
        }

        if (postanskiBrojText.getText() == null || postanskiBrojText.getText().length() == 0) {
            errorMessage += "Vrijednost za postanski broj nije ispravna!\n";
        } else {
            // pokusaj parsirati vrijednost postanskiBroj u int
            try {
                Integer.parseInt(postanskiBrojText.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Postanski broj mora biti integer!\n";
            }
        }

        if (gradText.getText() == null || gradText.getText().length() == 0) {
            errorMessage += "Vrijednost za grad nije ispravna!\n";
        }

        if (rodjendanText.getText() == null || rodjendanText.getText().length() == 0) {
            errorMessage += "Vrijednost za rodjendan nije ispravna!\n";
        } else {
            if (!DateUtil.validDate(rodjendanText.getText())) {
                errorMessage += "Za rodjendan koristite format dd.mm.yyyy!\n";
            }
        }

        /*if (errorMessage.length() == 0) {
            return true;
        } else {
            // Prikazi poruku
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("neispravan unos");
            alert.setHeaderText("Ispravite neispravan unos!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }*/
        return true;
    }




}
