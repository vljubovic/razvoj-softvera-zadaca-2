package ba.unsa.etf.rs.zadaca2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalDateStringConverter;
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

        imeKolona.setCellValueFactory(cellData -> cellData.getValue().imeProperty());
        prezimeKolona.setCellValueFactory(cellData -> cellData.getValue().prezimeProperty());
        osobaTabela.setItems(model.getOsobe());

        osobaTabela.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Osoba>(){
            @Override
            public void changed(ObservableValue<? extends Osoba> observableValue, Osoba osobaOld, Osoba osobaNew) {
                model.setTrenutnaOsoba(osobaNew);
                osobaTabela.refresh();
            }
            });

        model.trenutnaOsoba().addListener((obs, oldOsoba, newOsoba) -> {
            if (oldOsoba != null) {
                imeText.textProperty().unbindBidirectional(oldOsoba.imeProperty() );
                prezimeText.textProperty().unbindBidirectional(oldOsoba.prezimeProperty() );
                ulicaText.textProperty().unbindBidirectional(oldOsoba.ulicaProperty() );
                gradText.textProperty().unbindBidirectional(oldOsoba.gradProperty() );
                postanskiBrojText.textProperty().unbindBidirectional(oldOsoba.postanskiBrojProperty() );
                rodjendanText.textProperty().unbindBidirectional(oldOsoba.rodjendanProperty() );
            }
            if (newOsoba == null) {
                imeText.setText("");
                prezimeText.setText("");
                ulicaText.setText("");
                gradText.setText("");
                postanskiBrojText.setText("");
                rodjendanText.setText("");
            }
            else {
                imeText.textProperty().bindBidirectional(newOsoba.imeProperty() );
                prezimeText.textProperty().bindBidirectional(newOsoba.prezimeProperty() );
                ulicaText.textProperty().bindBidirectional(newOsoba.ulicaProperty() );
                gradText.textProperty().bindBidirectional(newOsoba.gradProperty() );
                postanskiBrojText.textProperty().bindBidirectional(newOsoba.postanskiBrojProperty(), new NumberStringConverter());
                rodjendanText.textProperty().bindBidirectional(newOsoba.rodjendanProperty(), new LocalDateStringConverter() );
            }
        });



    }


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
        Osoba novaOsoba = new Osoba();
        model.getOsobe().add(novaOsoba);
        osobaTabela.getSelectionModel().selectLast();
        osobaTabela.refresh();

        /*Osoba novaOsoba = new Osoba("","");
        model.getOsobe().add(novaOsoba);
        model.setTrenutnaOsoba(novaOsoba);
        osobaTabela.getSelectionModel().select(novaOsoba);*/

        //model.setTrenutnaOsoba(model.getOsobe().get(model.getOsobe().size()-1));
        //osobaTabela.getSelectionModel().selectLast();

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
    /*@FXML
    private void promjeniOsobu(ActionEvent actionEvent) {

        Osoba odabranaOsoba = osobaTabela.getSelectionModel().getSelectedItem();
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
        }
    }*/

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
