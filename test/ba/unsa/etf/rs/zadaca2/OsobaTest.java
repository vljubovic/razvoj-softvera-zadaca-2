package ba.unsa.etf.rs.zadaca2;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OsobaTest {

    @Test
    void konstruktor() {
        Osoba o = new Osoba("ime", "prezime", "ulica", 1234, "grad", LocalDate.of(1999, 2, 21));
        assertEquals("ime", o.getIme());
        assertEquals("prezime", o.getPrezime());
        assertEquals("ulica", o.getUlica());
        assertEquals(1234, o.getPostanskiBroj());
        assertEquals("grad", o.getGrad());
        assertEquals(LocalDate.of(1999, 2, 21), o.getRodjendan());
    }


    @Test
    void setteri() {
        Osoba o = new Osoba("ime", "prezime", "ulica", 1234, "grad", LocalDate.of(1999, 2, 21));
        o.setIme("novo_ime");
        o.setPrezime("novo_prezime");
        o.setUlica("nova_ulica");
        o.setPostanskiBroj(4321);
        o.setGrad("novi_grad");
        o.setRodjendan(LocalDate.of(1992, 2, 21));

        assertEquals("novo_ime", o.getIme());
        assertEquals("novo_prezime", o.getPrezime());
        assertEquals("nova_ulica", o.getUlica());
        assertEquals(4321, o.getPostanskiBroj());
        assertEquals("novi_grad", o.getGrad());
        assertEquals(LocalDate.of(1992, 2, 21), o.getRodjendan());

    }

}


