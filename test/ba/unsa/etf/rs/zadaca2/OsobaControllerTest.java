package ba.unsa.etf.rs.zadaca2;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javax.xml.soap.Text;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(ApplicationExtension.class)
class OsobaControllerTest {
    public OsobeModel model;

    @Start
    public void start (Stage stage) throws Exception {
        model = new OsobeModel();
        model.napuni();
        OsobaController ctrl = new OsobaController(model);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adresar.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();

        stage.setScene(new Scene(root, 600, 300));
        stage.setTitle("Adresar");
        stage.show();
        stage.toFront();
    }

    @Test
    void tableTest(FxRobot robot) {
        TableView<Osoba> tabela = robot.lookup("#tabelaOsobe").queryAs(TableView.class);
        ObservableList<Osoba> osobe = tabela.getItems();
        String s = "";
        for(Osoba o : osobe)
            s += o.toString();
        String expected = "Semso SemsicHamo HamicNedo NedicMirso MirsicVlasta VlasticMilada MiladicFata FaticHamida HamidicPero Peric";
        assertEquals(expected, s);
    }

    @Test
    void promjena(FxRobot robot) {
        robot.clickOn("Nedo");
        robot.clickOn("#imeText").write("aaaa");
        robot.clickOn("Fata");
        TableView<Osoba> tabela = robot.lookup("#tabelaOsobe").queryAs(TableView.class);
        ObservableList<Osoba> osobe = tabela.getItems();
        assertEquals("Nedoaaaa Nedic", osobe.get(2).toString());
    }

    @Test
    void promjenaModel(FxRobot robot) {
        robot.clickOn("Nedo");
        robot.clickOn("#imeText").write("aaaa");
        ObservableList<Osoba> osobe = model.getOsobe();
        assertEquals("Nedoaaaa Nedic", osobe.get(2).toString());
    }

    @Test
    void dodavanje(FxRobot robot) {
        robot.clickOn("#btnDodaj");
        robot.clickOn("#rodjendanText").write("06/15/1998");
        robot.clickOn("#imeText").write("Amra");
        robot.clickOn("#prezimeText").write("Delic");
        robot.clickOn("#ulicaText").write("Nova ulica");
        robot.clickOn("#postanskiBrojText").write("6543");
        robot.clickOn("#gradText").write("Sarajevo");
        // DatePicker mora izgubiti fokus da bi se prihvatila vrijednost
        //robot.clickOn("#gradText");

        TableView<Osoba> tabela = robot.lookup("#tabelaOsobe").queryAs(TableView.class);
        ObservableList<Osoba> osobe = tabela.getItems();
        assertEquals(10, osobe.size());
        assertEquals("Amra", osobe.get(9).getIme());
        assertEquals("Delic", osobe.get(9).getPrezime());
        assertEquals("Nova ulica", osobe.get(9).getUlica());
        assertEquals(6543, osobe.get(9).getPostanskiBroj());
        assertEquals("Sarajevo", osobe.get(9).getGrad());
        assertEquals(LocalDate.of(1998, 6, 15), osobe.get(9).getRodjendan());
    }

    @Test
    void dodavanjeModel(FxRobot robot) {
        robot.clickOn("#btnDodaj");
        robot.clickOn("#rodjendanText").write("06/15/1998");
        robot.clickOn("#imeText").write("Amra");
        robot.clickOn("#prezimeText").write("Delic");
        robot.clickOn("#ulicaText").write("Nova ulica");
        robot.clickOn("#postanskiBrojText").write("6543");
        robot.clickOn("#gradText").write("Sarajevo");
        // DatePicker mora izgubiti fokus da bi se prihvatila vrijednost
        //robot.clickOn("Nedo");

        ObservableList<Osoba> osobe = model.getOsobe();
        assertEquals(10, osobe.size());
        assertEquals("Amra", osobe.get(9).getIme());
        assertEquals("Delic", osobe.get(9).getPrezime());
        assertEquals("Nova ulica", osobe.get(9).getUlica());
        assertEquals(6543, osobe.get(9).getPostanskiBroj());
        assertEquals("Sarajevo", osobe.get(9).getGrad());
        assertEquals(LocalDate.of(1998, 6, 15), osobe.get(9).getRodjendan());
    }

    @Test
    void brisanje(FxRobot robot) {
        robot.clickOn("Nedo");
        robot.clickOn("#btnObrisi");
        TableView<Osoba> tabela = robot.lookup("#tabelaOsobe").queryAs(TableView.class);
        ObservableList<Osoba> osobe = tabela.getItems();
        assertEquals(8, osobe.size());
        for(Osoba o : osobe)
            if (o.getIme().equals("Nedo")) fail();
    }

    @Test
    void brisanjeModel(FxRobot robot) {
        robot.clickOn("Nedo");
        robot.clickOn("#btnObrisi");
        ObservableList<Osoba> osobe = model.getOsobe();
        assertEquals(8, osobe.size());
        for(Osoba o : osobe)
            if (o.getIme().equals("Nedo")) fail();
    }

    @Test
    void formatBroja(FxRobot robot) {
        robot.clickOn("#postanskiBrojText");
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.A).release(KeyCode.CONTROL);
        robot.write("7845");

        TextField postanskiBroj = robot.lookup("#postanskiBrojText").queryAs(TextField.class);
        assertEquals("7845", postanskiBroj.getText());
    }

    @Test
    void formatBrojaDodavanje(FxRobot robot) {
        robot.clickOn("#btnDodaj");
        robot.clickOn("#imeText").write("Amra");
        robot.clickOn("#prezimeText").write("Delic");
        robot.clickOn("#ulicaText").write("Nova ulica");
        robot.clickOn("#postanskiBrojText").write("654321");
        robot.clickOn("#gradText").write("Sarajevo");
        robot.clickOn("#rodjendanText").write("06/15/1998");

        robot.clickOn("Semsic");
        robot.clickOn("Amra");

        TextField postanskiBroj = robot.lookup("#postanskiBrojText").queryAs(TextField.class);
        assertEquals("654321", postanskiBroj.getText());
    }
}
