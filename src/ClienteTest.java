import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ClienteTest {
    Cliente unCliente = new Cliente("nombre", 123,"contrasena");
    Zona unaZona = new ZonaSur();
    Activo unActivo = new Activo(new TipoDeActivo("auto"),new Terminal(unaZona,"Terminal1"), 10,10,10,10);

    @Test
    public void devolverActivo() throws IOException {
        //setup
        ArrayList<Zona> zonas = new ArrayList<>();
        zonas.add(unaZona);
        zonas.add(new ZonaNorte());
        unCliente.agregarZonas(zonas);
        unCliente.setActivoEnUso(unActivo, LocalTime.MIDNIGHT,LocalTime.NOON);
        Assert.assertEquals(unActivo,unCliente.getActivoEnUso());

        //action
        unCliente.devolverActivo(true);

        //assertion
        assertNull(unCliente.activoEnUso);


    }

    @Test
    public void getPuntosPorZona() throws IOException {
        //setup
        ArrayList<Zona> zonas = new ArrayList<>();
        zonas.add(unaZona);
        zonas.add(new ZonaNorte());
        unCliente.agregarZonas(zonas);
        unCliente.setActivoEnUso(unActivo, LocalTime.MIDNIGHT,LocalTime.NOON);
        Assert.assertEquals(unActivo,unCliente.getActivoEnUso());
        unCliente.devolverActivo(false);

        //action
        int puntos = unCliente.getPuntosPorZonaFijo(unaZona);

        //assertion

        Assert.assertEquals(10,puntos);

    }

    @Test
    public void bloquearCliente() throws IOException {
        //setup
        ArrayList<Zona> zonas = new ArrayList<>();
        zonas.add(unaZona);
        zonas.add(new ZonaNorte());
        unCliente.agregarZonas(zonas);
        unCliente.setActivoEnUso(unActivo, LocalTime.MIDNIGHT,LocalTime.NOON);

        //action
        unCliente.bloquearCliente();

        //assertion
        assertNull(unCliente.activoEnUso);
        assertNotNull(unCliente.getMulta());
        assertTrue(unCliente.getBlocked());

    }



    @Test
    public void tieneActivoEnUso() throws IOException {
        //setup
        ArrayList<Zona> zonas = new ArrayList<>();
        zonas.add(unaZona);
        zonas.add(new ZonaNorte());
        unCliente.agregarZonas(zonas);
        unCliente.setActivoEnUso(unActivo, LocalTime.MIDNIGHT,LocalTime.NOON);

        //assertion
        assertTrue(unCliente.tieneActivoEnUso());
        unCliente.devolverActivo(true);
        assertFalse(unCliente.tieneActivoEnUso());


    }

}