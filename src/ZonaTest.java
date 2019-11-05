import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ZonaTest {
    ZonaNorte unaZona = new ZonaNorte();
    Terminal unaTerminal = new Terminal(unaZona);

    @Test
    public void testAgregarLote() {
        //setup
        ArrayList<Activo> activos = new ArrayList<>();
        TipoDeActivo tipoDeActivo = new TipoDeActivo("auto");
        activos.add(new Activo(tipoDeActivo, unaTerminal,10,10, 10));
        Lote unLote = new Lote (activos, unaTerminal,"Lote1");

        //actions
        unaZona.agregarLote(unLote);

        //assertion
        Assert.assertEquals(1, unaZona.getLotes().size());
        Assert.assertEquals("Lote1", unaZona.getLotes().get(0).getNombreDelLote());

    }

    @Test
    public void testEliminarLote() {
        //setup
        ArrayList<Activo> activos = new ArrayList<>();
        TipoDeActivo tipoDeActivo = new TipoDeActivo("auto");
        activos.add(new Activo(tipoDeActivo, unaTerminal,10,10,10));
        Lote unLote = new Lote (activos, unaTerminal,"Lote1");
        unaZona.agregarLote(unLote);

        //actions
        unaZona.eliminarLote(unLote);

        //assertion
        Assert.assertEquals(0, unaZona.getLotes().size());
    }


    @Test
    public void testActivosDeZona() {
        //setup
        ArrayList<Activo> activos = new ArrayList<>();
        TipoDeActivo tipoDeActivo = new TipoDeActivo("auto");
        activos.add(new Activo(tipoDeActivo, unaTerminal,10,10,10));
        Lote unLote = new Lote (activos, unaTerminal,"Lote1");
        unaZona.agregarLote(unLote);
        ArrayList<Activo> activosEncontrados = new ArrayList<>();

        //actions
        activosEncontrados = unaZona.activosDeZona();

        //assertion
        Assert.assertEquals(1, activosEncontrados.size());
        Assert.assertEquals("auto", activosEncontrados.get(0).getNombre().getNombre());

    }
}