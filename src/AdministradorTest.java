import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AdministradorTest {
    Administrador admin = new Administrador("admin", "admin");

    @Test
    public void testBloquearCliente() {
        //setup
        Cliente unCliente = new Cliente("nomcliente", 123,"contrasena");
        TipoDeActivo unTipoDeActivo = new TipoDeActivo("auto");
        Activo unActivo = new Activo(unTipoDeActivo, new Terminal(new ZonaSur()),10,10);
        unCliente.setActivoEnUso(unActivo);

        //actions
        try {
            admin.bloquearCliente(unCliente);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //assertion
        Assert.assertEquals(true, unCliente.getBlocked());
    }

    @Test
    public void testDesbloquearCliente() {
        //setup
        Cliente unCliente = new Cliente("nomcliente", 123,"contrasena");
        TipoDeActivo unTipoDeActivo = new TipoDeActivo("auto");
        Activo unActivo = new Activo(unTipoDeActivo, new Terminal(new ZonaSur()),10,10);
        unCliente.setActivoEnUso(unActivo);
        try {
            admin.bloquearCliente(unCliente);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //actions
        admin.desbloquearCliente(unCliente);

        //assertion
        Assert.assertEquals(false, unCliente.getBlocked());
    }

    @Test
    public void testMultarCliente() {
        //setup
        Cliente unCliente = new Cliente("nomcliente", 123,"contrasena");
        TipoDeActivo unTipoDeActivo = new TipoDeActivo("auto");
        Activo unActivo = new Activo(unTipoDeActivo, new Terminal(new ZonaSur()),10,10);
        unCliente.setActivoEnUso(unActivo);

        //actions
        admin.multarCliente(unCliente);

        //assertion
        Assert.assertEquals(5, unCliente.getMulta().getValorDeMulta());
    }

    @Test
    public void crearLoteDeCompraDeActivos() throws IOException {
        //setup
        TipoDeActivo tipoDeActivo = new TipoDeActivo("auto");
        Lote unLote = null;

        //actions
        unLote = admin.crearLoteDeCompraDeActivos("Lote1", tipoDeActivo, 10, new Terminal(new ZonaSur()), 10,10);

        //assertion
        Assert.assertEquals("Lote1", unLote.getNombreDelLote());
        Assert.assertEquals(1, unLote.getCodigo());
        Assert.assertEquals("auto", unLote.getActivoPorCodigo(1).getNombre().getNombre());

    }
}