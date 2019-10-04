import org.junit.Assert;

import java.io.IOException;

public class OperadorDeUsuariosTest {
    OperadorDeUsuarios operadorUsuarios = new OperadorDeUsuarios();

    @org.junit.Test
    public void testAgregarAdmin() {
        //setup
        Administrador administradorParaAgregar = new Administrador("Nombre", "contrasena");

        //action
        operadorUsuarios.agregarAdmin(administradorParaAgregar.getNombreDeUsuario(), administradorParaAgregar.getContrasena());

        //assertion
        Assert.assertEquals(2, operadorUsuarios.getUsuarios().size());
        Assert.assertEquals("admin", operadorUsuarios.getUsuarios().get(0).getNombreDeUsuario());
        Assert.assertEquals("admin", operadorUsuarios.getUsuarios().get(0).getContrasena());
        Assert.assertEquals("Nombre", operadorUsuarios.getUsuarios().get(1).getNombreDeUsuario());
        Assert.assertEquals("contrasena", operadorUsuarios.getUsuarios().get(1).getContrasena());
    }

    @org.junit.Test
    public void testEliminarAdmin() throws IOException {
        //setup
        Administrador administradorParaEliminar = new Administrador("Nombre", "contrasena");
        operadorUsuarios.agregarAdmin(administradorParaEliminar.getNombreDeUsuario(), administradorParaEliminar.getContrasena());

        //action
        operadorUsuarios.eliminarAdmin(administradorParaEliminar.getNombreDeUsuario());

        //assertion
        Assert.assertEquals(1, operadorUsuarios.getUsuarios().size());
        Assert.assertEquals("admin", operadorUsuarios.getUsuarios().get(0).getNombreDeUsuario());
        Assert.assertEquals("admin", operadorUsuarios.getUsuarios().get(0).getContrasena());
    }

    @org.junit.Test
    public void testAdminCheck() throws UsuarioIncorrectoException, ContrasenaIncorrectaException {
        String unNombre = "Name";
        String unaContrasena = "1234";
        operadorUsuarios.agregarAdmin(unNombre, unaContrasena);
        operadorUsuarios.adminCheck(unNombre,unaContrasena);

    }

}