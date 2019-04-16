package ba.unsa.etf.rs.zadaca2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OsobeModelTest {

    @Test
    void konstruktor() {
        OsobeModel m = new OsobeModel();
        assertNull(m.getTrenutnaOsoba());
        assertTrue(m.getOsobe().isEmpty());
    }

    @Test
    void napuni1() {
        OsobeModel m = new OsobeModel();
        m.napuni();
        assertEquals(9, m.getOsobe().size());
        assertNull(m.getTrenutnaOsoba());
    }

    @Test
    void napuni2() {
        OsobeModel m = new OsobeModel();
        m.napuni();
        assertEquals("Semso", m.getOsobe().get(0).getIme());
        assertEquals("Semsic", m.getOsobe().get(0).getPrezime());
    }

    @Test
    void napuni3() {
        OsobeModel m = new OsobeModel();
        m.napuni();
        String s = "";
        for(Osoba o : m.getOsobe().subList(2,4))
            s += o.getIme()+ " " + o.getPrezime();
        String expected = "Nedo NedicMirso Mirsic";
        assertEquals(expected, s);
    }

    @Test
    void trenutnaOsoba() {
        OsobeModel m = new OsobeModel();
        m.napuni();
        m.setTrenutnaOsoba(m.getOsobe().get(5));
        String s = "" + m.getTrenutnaOsoba().getIme() +" " + m.getTrenutnaOsoba().getPrezime();
        assertEquals("Milada Miladic", s);
    }


    @Test
    void promijeniTrenutniKorisnik() {
        OsobeModel m = new OsobeModel();
        m.napuni();
        m.setTrenutnaOsoba(m.getOsobe().get(2));
        m.getTrenutnaOsoba().setIme("novo_ime");
        assertEquals("novo_ime", m.getOsobe().get(2).getIme());
    }
}