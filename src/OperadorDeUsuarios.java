import java.io.IOException;
import java.util.ArrayList;

//Provisorio. todo implementar persistencia

public class OperadorDeUsuarios {
    ArrayList<Usuario> usuarios;
    Usuario usuarioActivo;

    public OperadorDeUsuarios(){
        usuarios = new ArrayList<>();
        usuarios.add(new Administrador("admin", "admin"));
        usuarioActivo = null;
    }

    public void adminCheck(String nombreIngresado, String contrasenaIngresada) throws IOException {
        //todo ver si se puede reutilizar codigo con clienteCheck
        for (Usuario usuario : usuarios) {
            if(usuario instanceof Administrador){
                if (usuario.getNombreDeUsuario().equals(nombreIngresado) && usuario.getContrasena().equals(contrasenaIngresada)){
                    usuarioActivo = usuario;
                    return;
                }else if (usuario.getNombreDeUsuario().equals(nombreIngresado)&& !usuario.getContrasena().equals(contrasenaIngresada)){
                    throw new IOException("Contraseña incorrecta. ");
                }
            }
        }throw new IOException("Nombre de usuario incorrecto. ");
    }

    public void clienteCheck (String nombreIngresado, String contrasenaIngresada) throws IOException {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente) {
                if (usuario.getNombreDeUsuario().equals(nombreIngresado) && usuario.getContrasena().equals(contrasenaIngresada)){
                    usuarioActivo = usuario;
                    return;
                }else if (usuario.getNombreDeUsuario().equals(nombreIngresado)&& !usuario.getContrasena().equals(contrasenaIngresada)){
                    throw new IOException("Contraseña incorrecta. ");
                }
            }
        }throw new IOException("Nombre de usuario incorrecto. ");

    }

    public void cerrarSesion (){
        usuarioActivo = null;
    }

    public Usuario getUsuarioActivo()  {
        return usuarioActivo;

    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getUsuario (String nombre) throws IOException {
        for (Usuario usuario: usuarios) {
            if (usuario.getNombreDeUsuario().equals(nombre)) return usuario;
        } throw new IOException("Usuario no encontrado. ");
    }

    public ArrayList<Administrador> getAdmins(){
        ArrayList<Administrador> administradores = new ArrayList<>();
        for (Usuario usuario: usuarios) {
            if (usuario instanceof Administrador) administradores.add((Administrador) usuario);
        }
        return  administradores;
    }
    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (Usuario usuario: usuarios) {
            if (usuario instanceof Cliente) clientes.add((Cliente) usuario);
        }
        return  clientes;
    }

    public void agregarCliente (String nombre, int numeroDeTelefono, String contrasena) throws IOException {
        for (Usuario usuario: usuarios) {
            if(usuario.getNombreDeUsuario().equals(nombre)) throw new IOException("El nombre ya fue utilizado. ");
        }
        Cliente nuevoCliente = new Cliente(nombre, numeroDeTelefono, contrasena);
        usuarios.add(nuevoCliente);
    }

    public void eliminarCliente (String nombre) throws IOException {
        for (Usuario usuario: usuarios) {
            if (usuario instanceof Cliente){
                if (usuario.getNombreDeUsuario().equals(nombre)){
                    usuarios.remove(usuario);
                    return;
                }
            }
        }throw new IOException("Nombre no encontrado. ");
    }

    public void eliminarAdmin (String nombre) throws IOException {
        for (Usuario usuario: usuarios) {
            if (usuario instanceof Administrador){
                if (usuario.getNombreDeUsuario().equals(nombre)){
                    if (getAdmins().size() == 1) throw new IOException("No se pueden eliminar todos los admins. ");
                    if (usuarioActivo.getNombreDeUsuario().equals(nombre)) throw new IOException("No te puedes eliminar a ti mismo. ");
                    usuarios.remove(usuario);
                    return;
                }
            }
        }throw new IOException("Nombre no encontrado. ");
    }

    public void agregarAdmin (String nombre, String contrasena) throws IOException {
        for (Usuario usuario: usuarios) {
            if(usuario.getNombreDeUsuario().equals(nombre)) throw new IOException("El nombre ya fue utilizado. ");
        }
        Administrador nuevoAdmin = new Administrador(nombre, contrasena);
        usuarios.add(nuevoAdmin);

    }
}
