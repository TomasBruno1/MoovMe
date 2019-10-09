import java.io.IOException;
import java.util.ArrayList;

//Provisorio. todo implementar persistencia

public class OperadorDeUsuarios {
    ArrayList<Usuario> usuarios;
    public OperadorDeUsuarios(){
        usuarios = new ArrayList<>();
        usuarios.add(new Administrador("admin", "admin"));
    }

    public void adminCheck(String nombreIngresado, String contrasenaIngresada) throws ContrasenaIncorrectaException, UsuarioIncorrectoException {
        for (Usuario usuario : usuarios) {
            if(usuario instanceof Administrador){
                if (usuario.getNombreDeUsuario().equals(nombreIngresado) && usuario.getContrasena().equals(contrasenaIngresada)){
                    return;
                }else if (usuario.getNombreDeUsuario().equals(nombreIngresado)&& !usuario.getContrasena().equals(contrasenaIngresada)){
                    throw new ContrasenaIncorrectaException();
                }
            }
        }throw new UsuarioIncorrectoException();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
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

    public void eliminarAdmin (String nombre) throws IOException {
        //todo verificar que por lo menos quede 1 admin
        for (Usuario usuario: usuarios) {
            if (usuario instanceof Administrador){
                if (usuario.getNombreDeUsuario().equals(nombre)){
                    usuarios.remove(usuario);
                    return;
                }
            }
        }throw new IOException("Nombre no encontrado");
    }

    public void agregarAdmin (String nombre, String contrasena) {
        //todo verificar que ya no exista
        Administrador nuevoAdmin = new Administrador(nombre, contrasena);
        usuarios.add(nuevoAdmin);

    }
}
