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

    }


    public void dodajOsobu(ActionEvent actionEvent) {
        Osoba novaOsoba = new Osoba();
        model.getOsobe().add(novaOsoba);
        osobaTabela.getSelectionModel().selectLast();
        osobaTabela.refresh();
    }


}
