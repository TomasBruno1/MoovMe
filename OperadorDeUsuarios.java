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
                }else throw new UsuarioIncorrectoException();
            }
        }
    }

    public void agregarAdmin (String nombre, String contrasena){

        Administrador nuevoAdmin = new Administrador(nombre, contrasena);
        usuarios.add(nuevoAdmin);

    }
}
