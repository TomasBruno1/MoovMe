import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TerminalTest {
    Terminal unaTerminal = new Terminal(new ZonaSur(), "Terminal1");

    @Test
    public void getZona() {
        Assert.assertEquals("ZonaSur", unaTerminal.getZona().getNombre());
    }

    @Test
    public void agregarLote() {
        //setup
        ArrayList<Activo> activos = new ArrayList<>();
        activos.add(new Activo(new TipoDeActivo("auto"), unaTerminal,10,10,10,10 ));
        Lote loteAAgregar = new Lote(activos,unaTerminal,1);

        //action
        unaTerminal.agregarLote(loteAAgregar);

        //assertion
        Assert.assertEquals(1,unaTerminal.getLotes().size());
    }

    @Test
    public void eliminarLote() {
        //setup
        ArrayList<Activo> activos = new ArrayList<>();
        activos.add(new Activo(new TipoDeActivo("auto"), unaTerminal,10,10,10,10 ));
        Lote loteAEliminar = new Lote(activos,unaTerminal,1);
        unaTerminal.agregarLote(loteAEliminar);


        //action
        unaTerminal.eliminarLote(loteAEliminar);

        //assertion
        Assert.assertEquals(0,unaTerminal.getLotes().size());
    }

    @Test
    public void activosDeTerminal() {
        //setup
        ArrayList<Activo> activos = new ArrayList<>();
        activos.add(new Activo(new TipoDeActivo("auto"), unaTerminal,10,10,10,10 ));
        Lote loteAEliminar = new Lote(activos,unaTerminal,1);
        unaTerminal.agregarLote(loteAEliminar);


        //action
        ArrayList<Activo> activosEncontrados = unaTerminal.activosDeTerminal();

        //assertion
        Assert.assertEquals(1,activosEncontrados.size());
    }

    @Test
    public void activosDeTerminalPorTipo() {
        //setup
        ArrayList<Activo> activos = new ArrayList<>();
        TipoDeActivo tipoDeActivo = new TipoDeActivo("auto");
        TipoDeActivo tipoDeActivo2 = new TipoDeActivo("bici");
        activos.add(new Activo(tipoDeActivo, unaTerminal,10,10,10,10 ));
        Lote loteAEliminar = new Lote(activos,unaTerminal,1);
        unaTerminal.agregarLote(loteAEliminar);


        //action
        ArrayList<Activo> activosEncontrados = unaTerminal.activosDeTerminalPorTipo(tipoDeActivo);
        ArrayList<Activo> activosEncontrados2 = unaTerminal.activosDeTerminalPorTipo(tipoDeActivo2);

        //assertion
        Assert.assertEquals(1,activosEncontrados.size());
        Assert.assertEquals(0,activosEncontrados2.size());
    }

}