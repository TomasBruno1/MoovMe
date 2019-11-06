import java.io.Serializable;

public abstract class Usuario implements Serializable {
 String nombreDeUsuario;
 String contrasena;

    public String getContrasena() {
        return contrasena;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public abstract boolean isAdmin();
}
