import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class OperadorDeZonasTest {
    OperadorDeZonas operadorDeZonas = new OperadorDeZonas();

    @Test
    public void testAgregarZona() {
        //setup
        Zona unaZona = new ZonaSur();

        //actions
        operadorDeZonas.agregarZona(unaZona);

        //assertion
        Assert.assertEquals(3, operadorDeZonas.getZonas().size());
        Assert.assertEquals("ZonaSur", operadorDeZonas.getZonas().get(2).getNombre());

    }

    @Test
    public void testEliminarZona() {
        //setup
        Zona unaZona = new ZonaSur();
        operadorDeZonas.agregarZona(unaZona);


        //actions
        operadorDeZonas.eliminarZona(unaZona);

        //assertion
        Assert.assertEquals(2, operadorDeZonas.getZonas().size());
        Assert.assertEquals("ZonaNorte", operadorDeZonas.getZonas().get(0).getNombre());
        Assert.assertEquals("ZonaSur", operadorDeZonas.getZonas().get(1).getNombre());

    }

    @Test
    public void testAgregarTipoDeActivo() {
        //setup
       TipoDeActivo unTipoDeActivo = new TipoDeActivo("auto");
       String nombreTipoDeActivo =  unTipoDeActivo.getNombre();


        //actions
        try {
            operadorDeZonas.agregarTipoDeActivo(nombreTipoDeActivo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //assertion
        Assert.assertEquals(1, operadorDeZonas.getTipoDeActivos().size());
        Assert.assertEquals("auto", operadorDeZonas.getTipoDeActivos().get(0).getNombre());
    }

    @Test
    public void testEliminarTipoDeActivo() {
        //setup
        TipoDeActivo unTipoDeActivo = new TipoDeActivo("auto");
        String nombreTipoDeActivo =  unTipoDeActivo.getNombre();
        try {
            operadorDeZonas.agregarTipoDeActivo(nombreTipoDeActivo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //actions
        try {
            operadorDeZonas.eliminarTipoDeActivo(nombreTipoDeActivo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //assertion
        Assert.assertEquals(0, operadorDeZonas.getTipoDeActivos().size());
    }

    @Test
    public void testCheckTipoActivo() {
        //setup
        TipoDeActivo unTipoDeActivo = new TipoDeActivo("auto");
        String nombreTipoDeActivo =  unTipoDeActivo.getNombre();
        try {
            operadorDeZonas.agregarTipoDeActivo(nombreTipoDeActivo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        //actions
        try {
            operadorDeZonas.checkTipoActivo(nombreTipoDeActivo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testGetTipoActivo() {
        //setup
        TipoDeActivo unTipoDeActivo = new TipoDeActivo("auto");
        String nombreTipoDeActivo = unTipoDeActivo.getNombre();
        try {
            operadorDeZonas.agregarTipoDeActivo(nombreTipoDeActivo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        TipoDeActivo tDAEncontrado = null;


        //actions
        try {
            tDAEncontrado = operadorDeZonas.getTipoActivo(nombreTipoDeActivo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //assertion
        assert tDAEncontrado != null;
        Assert.assertEquals("auto", tDAEncontrado.getNombre());
        Assert.assertEquals(tDAEncontrado.getNombre(), unTipoDeActivo.getNombre());

    }

    @Test
    public void testGetZona() {
        //setup
        Zona unaZona = new ZonaSur();
        String nombreZona = unaZona.getNombre();
        operadorDeZonas.agregarZona(unaZona);
        Zona zonaEncontrada = null;


        //actions
        try {
            zonaEncontrada = operadorDeZonas.getZona(nombreZona);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //assertion
        assert zonaEncontrada != null;
        Assert.assertEquals("ZonaSur", zonaEncontrada.getNombre());
    }


    @Test
    public void testAgregarLoteAZona() throws IOException {
        //setup
        ArrayList<Activo> activos = new ArrayList<>();
        TipoDeActivo tipoDeActivo = new TipoDeActivo("auto");
        Iterator it = operadorDeZonas.getZonas().get(0).getTerminales().iterator();
        Iterator it2 = operadorDeZonas.getZonas().get(0).getTerminales().iterator();
        Zona suZona = operadorDeZonas.getZonas().get(0);

        while(it.hasNext()){
            activos.add(new Activo(tipoDeActivo, (Terminal) it.next(),10,10, 10));
        }
        Lote unLote = null;
        while(it2.hasNext()){
            unLote = new Lote (activos, (Terminal) it.next(),"Lote1");
        }

        //actions
        try {
            operadorDeZonas.agregarLoteAZona(unLote, suZona.getNombre());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //assertion
        Assert.assertEquals(1, operadorDeZonas.getZona(suZona.getNombre()).getLotes().size());
        Assert.assertEquals(1, operadorDeZonas.getZona(suZona.getNombre()).getLotes().get(0).getCodigo());
        Assert.assertEquals("Lote1", operadorDeZonas.getZona(suZona.getNombre()).getLotes().get(0).getNombreDelLote());
    }
}