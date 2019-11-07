import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ZonaTest {
    ZonaNorte unaZona = new ZonaNorte();
    Terminal unaTerminal = new Terminal(unaZona, "Joe");

    @Test
    public void testAgregarLote() {
        //setup
        ArrayList<Activo> activos = new ArrayList<>();
        TipoDeActivo tipoDeActivo = new TipoDeActivo("auto");
        activos.add(new Activo(tipoDeActivo, unaTerminal,10,10, 10));
        Lote unLote = new Lote (activos, unaTerminal, 1);

        //actions
        unaTerminal.agregarLote(unLote);

        //assertion
        Assert.assertEquals(1, unaTerminal.getLotes().size());

    }

    @Test
    public void testEliminarLote() {
        //setup
        ArrayList<Activo> activos = new ArrayList<>();
        TipoDeActivo tipoDeActivo = new TipoDeActivo("auto");
        activos.add(new Activo(tipoDeActivo, unaTerminal,10,10,10));
        Lote unLote = new Lote (activos, unaTerminal,1);
        unaTerminal.agregarLote(unLote);

        //actions
        unaTerminal.eliminarLote(unLote);

        //assertion
        Assert.assertEquals(0, unaTerminal.getLotes().size());
    }


    @Test
    public void testActivosDeZona() {
        //setup
        ArrayList<Activo> activos = new ArrayList<>();
        TipoDeActivo tipoDeActivo = new TipoDeActivo("auto");
        activos.add(new Activo(tipoDeActivo, unaTerminal,10,10,10));
        Lote unLote = new Lote (activos, unaTerminal,41);
        unaTerminal.agregarLote(unLote);
        ArrayList<Activo> activosEncontrados = new ArrayList<>();

        //actions
        activosEncontrados = unaTerminal.activosDeTerminal();

        //assertion
        Assert.assertEquals(1, activosEncontrados.size());
        Assert.assertEquals("auto", activosEncontrados.get(0).getTipoDeActivo().getNombre());

    }
}