import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class ActivoTest {
    Zona unaZona = new ZonaSur();
    Activo unActivo = new Activo(new TipoDeActivo("auto"),new Terminal(unaZona,"Terminal1"), 10,10,10,10);

    @Test
    public void enUso() throws IOException {
        Assert.assertEquals(1,unActivo.getStatus().getCodigoStatus());

        unActivo.enUso();

        Assert.assertEquals(0,unActivo.getStatus().getCodigoStatus());

    }

    @Test
    public void disponible() throws IOException {
        unActivo.enUso();
        Assert.assertEquals(0,unActivo.getStatus().getCodigoStatus());

        unActivo.disponible();
        Assert.assertEquals(1,unActivo.getStatus().getCodigoStatus());


    }

    @Test
    public void retirarActivoDeTerminal() throws IOException {
        unActivo.retirarActivoDeTerminal(LocalTime.MIDNIGHT, LocalTime.NOON);


        assertNull(unActivo.getTerminalActual());
        Assert.assertEquals(0,unActivo.getStatus().getCodigoStatus());
        Assert.assertEquals(LocalTime.MIDNIGHT,unActivo.getTiempoEnElQueSeAlquilo());
        Assert.assertEquals(LocalTime.NOON,unActivo.getTiempoEstimadoDeDevolucion());


    }

    @Test
    public void devolverActivoATerminal() throws IOException {
        unActivo.retirarActivoDeTerminal(LocalTime.MIDNIGHT, LocalTime.NOON);

        unActivo.devolverActivoATerminal(unActivo.getTerminalDeOrigen(), LocalTime.NOON);
        Assert.assertEquals(1,unActivo.getStatus().getCodigoStatus());
        Assert.assertEquals(unActivo.getTerminalDeOrigen(),unActivo.getTerminalActual());

    }


    @Test
    public void checkEstaEnZona() throws IOException {
        unActivo.retirarActivoDeTerminal(LocalTime.MIDNIGHT, LocalTime.NOON);
        Terminal terminal2 = new Terminal(unaZona, "Terminal 2");
        unActivo.devolverActivoATerminal(unActivo.getTerminalDeOrigen(), LocalTime.NOON);

        unActivo.checkEstaEnZona(terminal2);

        Assert.assertEquals(true,unActivo.getEstaEnZona());

    }



}