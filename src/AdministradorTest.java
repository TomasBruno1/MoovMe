import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalTime;

public class AdministradorTest {
    Administrador admin = new Administrador("admin", "admin");

    @Test
    public void testBloquearCliente() throws IOException {
        //setup
        Cliente unCliente = new Cliente("nomcliente", 123,"contrasena");
        TipoDeActivo unTipoDeActivo = new TipoDeActivo("auto");
        Activo unActivo = new Activo(unTipoDeActivo, new Terminal(new ZonaSur(), "Joe"),10,10, 10, 10);
        unCliente.setActivoEnUso(unActivo, LocalTime.MIDNIGHT, LocalTime.NOON);

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
    public void testDesbloquearCliente() throws IOException {
        //setup
        Cliente unCliente = new Cliente("nomcliente", 123,"contrasena");
        TipoDeActivo unTipoDeActivo = new TipoDeActivo("auto");
        Activo unActivo = new Activo(unTipoDeActivo, new Terminal(new ZonaSur(), "Joe"),10,10,10,10);
        unCliente.setActivoEnUso(unActivo, LocalTime.MIDNIGHT, LocalTime.NOON);
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
    public void testMultarCliente() throws IOException {
        //setup
        Cliente unCliente = new Cliente("nomcliente", 123,"contrasena");
        TipoDeActivo unTipoDeActivo = new TipoDeActivo("auto");
        Activo unActivo = new Activo(unTipoDeActivo, new Terminal(new ZonaSur(), "Joe"),10,10, 10,10);
        unCliente.setActivoEnUso(unActivo, LocalTime.MIDNIGHT, LocalTime.NOON);

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
        unLote = admin.crearLoteDeCompraDeActivos( tipoDeActivo, 10, new Terminal(new ZonaSur(), "Joe"), 10,10,10,1,1);

        //assertion
        Assert.assertEquals(1, unLote.getCodigo());
        Assert.assertEquals("auto", unLote.getActivoPorCodigo(1).getTipoDeActivo().getNombre());

    }
}