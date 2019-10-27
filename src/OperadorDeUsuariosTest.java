import org.junit.Assert;

import java.io.IOException;

public class OperadorDeUsuariosTest {
    OperadorDeUsuarios operadorUsuarios = new OperadorDeUsuarios();

    @org.junit.Test
    public void testAgregarAdmin() throws IOException {
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
    public void testAgregarCliente() throws IOException {
       //setup
        String unNombre = "Name";
        String unaContrasena = "1234";
        int unTelefono = 1234;

        //action

        operadorUsuarios.agregarCliente(unNombre,unTelefono, unaContrasena);


        //assertion
        Assert.assertEquals(2, operadorUsuarios.getUsuarios().size());
        Assert.assertEquals("admin", operadorUsuarios.getUsuarios().get(0).getNombreDeUsuario());
        Assert.assertEquals("admin", operadorUsuarios.getUsuarios().get(0).getContrasena());
        Assert.assertEquals("Name", operadorUsuarios.getUsuarios().get(1).getNombreDeUsuario());
        Assert.assertEquals("1234", operadorUsuarios.getUsuarios().get(1).getContrasena());
    }
    @org.junit.Test
    public void testEliminarCliente() throws IOException {
        //setup
        Cliente clienteParaEliminar = new Cliente("Nombre",1234, "contrasena");
        operadorUsuarios.agregarCliente(clienteParaEliminar.getNombreDeUsuario(),clienteParaEliminar.getNumeroDeTelefono() , clienteParaEliminar.getContrasena());

        //action
        operadorUsuarios.eliminarCliente(clienteParaEliminar.getNombreDeUsuario());

        //assertion
        Assert.assertEquals(1, operadorUsuarios.getUsuarios().size());
        Assert.assertEquals("admin", operadorUsuarios.getUsuarios().get(0).getNombreDeUsuario());
        Assert.assertEquals("admin", operadorUsuarios.getUsuarios().get(0).getContrasena());
    }

    @org.junit.Test
    public void testAdminCheck() throws IOException {
        //setup
        String unNombre = "Name";
        String unaContrasena = "1234";

        operadorUsuarios.agregarAdmin(unNombre, unaContrasena);

        //actions
        operadorUsuarios.adminCheck(unNombre,unaContrasena);

    }

    @org.junit.Test
    public void testClienteCheck() throws IOException {
        //setup
        Cliente clienteParaCheckear = new Cliente("Nombre",1234, "contrasena");
        operadorUsuarios.agregarCliente(clienteParaCheckear.getNombreDeUsuario(),clienteParaCheckear.getNumeroDeTelefono() , clienteParaCheckear.getContrasena());

        //action
        operadorUsuarios.clienteCheck(clienteParaCheckear.getNombreDeUsuario(), clienteParaCheckear.getContrasena());

    }

    @org.junit.Test
    public void testGetUsuario() throws IOException {
        //setup
        Cliente unCliente = new Cliente("NombreC",1234, "contrasenaC");
        operadorUsuarios.agregarCliente(unCliente.getNombreDeUsuario(),unCliente.getNumeroDeTelefono() , unCliente.getContrasena());
        Administrador unAdmin = new Administrador("NombreA", "contrasenaA");
        operadorUsuarios.agregarAdmin(unAdmin.getNombreDeUsuario(), unAdmin.getContrasena());

        //action
        Cliente clienteEncontrado = (Cliente)operadorUsuarios.getUsuario(unCliente.getNombreDeUsuario());
        Administrador adminEncontrado = (Administrador) operadorUsuarios.getUsuario(unAdmin.getNombreDeUsuario());

        //assertion
        Assert.assertEquals("NombreC", clienteEncontrado.getNombreDeUsuario());
        Assert.assertEquals("contrasenaC", clienteEncontrado.getContrasena());
        Assert.assertEquals("NombreA", adminEncontrado.getNombreDeUsuario());
        Assert.assertEquals("contrasenaA", adminEncontrado.getContrasena());


    }




}