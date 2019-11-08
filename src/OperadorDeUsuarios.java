import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

//todo usar polimorfismo para instanceof

public class OperadorDeUsuarios implements Serializable {
    ArrayList<Usuario> usuarios;
    Usuario usuarioActivo;
    OperadorDeZonas operadorDeZonas;
    HashSet<Descuento> descuentosDisponibles;

    public OperadorDeUsuarios(){
        usuarios = new ArrayList<>();
        usuarios.add(new Administrador("admin", "admin"));
        usuarioActivo = null;
        descuentosDisponibles = new HashSet<>();
    }

    public void setOperadorDeZonas(OperadorDeZonas operadorDeZonas) {
        this.operadorDeZonas = operadorDeZonas;
    }

    public void adminCheck(String nombreIngresado, String contrasenaIngresada) throws IOException {
        //todo ver si se puede reutilizar codigo con clienteCheck
        for (Usuario usuario : usuarios) {
            if(usuario.isAdmin()){
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
            if (!usuario.isAdmin()) {
                if (usuario.getNombreDeUsuario().equals(nombreIngresado) && usuario.getContrasena().equals(contrasenaIngresada)){
                    usuarioActivo = usuario;
                    return;
                }else if (usuario.getNombreDeUsuario().equals(nombreIngresado)&& !usuario.getContrasena().equals(contrasenaIngresada)){
                    throw new IOException("Contraseña incorrecta. ");
                }
            }
        }throw new IOException("Nombre de usuario incorrecto. ");

    }

    public ArrayList<Descuento> ofrecerDescuentos (Cliente unCliente) throws IOException {
        ArrayList<Descuento> descuentosDisponiblesAMostrar = new ArrayList<>();
        for (Descuento descuento: descuentosDisponibles) {
            if(descuento.getUnTipoDeActivo().getNombre().equals(unCliente.getActivoEnUso().getTipoDeActivo().getNombre())&&
                    descuento.getZonaParaDescuento().getNombre().equals(unCliente.getActivoEnUso().getTerminalDeOrigen().getZona().getNombre())){
                descuentosDisponiblesAMostrar.add(descuento);
            }
        }
        return descuentosDisponiblesAMostrar;
    }

    public Descuento getDescuentoPorDescripcion (String descripcion) throws IOException {
        Iterator<Descuento>  iteratorDescuento = descuentosDisponibles.iterator();
        while ( iteratorDescuento.hasNext()){
            if (descripcion.equals(iteratorDescuento.next().getDescripcion())) return iteratorDescuento.next();
        }throw new IOException("Descuento no econtrado");
    }

    public HashSet<Descuento> getDescuentosDisponibles() {
        return descuentosDisponibles;
    }

    public void agregarDescuento(Administrador admin, String descripcion, TipoDeActivo unTipoDeActivo, int puntosMinParaUsar, Zona zonaParaDescuento, int descuentoNumerico){
        descuentosDisponibles.add(admin.crearDescuento(descripcion, unTipoDeActivo, puntosMinParaUsar, zonaParaDescuento, descuentoNumerico)) ;
    }

    public void eliminarDescuento(String desDescuento) throws IOException {
        descuentosDisponibles.remove(getDescuentoPorDescripcion(desDescuento));
    }

    public void darDescuentoACliente (Cliente unCliente, String descripcion) throws IOException {
        unCliente.setDescuentoEnUso(getDescuentoPorDescripcion(descripcion));
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
            if (usuario.isAdmin()) administradores.add((Administrador) usuario);
        }
        return  administradores;
    }
    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (Usuario usuario: usuarios) {
            if (!usuario.isAdmin()) clientes.add((Cliente) usuario);
        }
        return  clientes;
    }

    public void agregarCliente (String nombre, int numeroDeTelefono, String contrasena) throws IOException {
        for (Usuario usuario: usuarios) {
            if(usuario.getNombreDeUsuario().equals(nombre)) throw new IOException("El nombre ya fue utilizado. ");
        }
        Cliente nuevoCliente = new Cliente(nombre, numeroDeTelefono, contrasena);
        usuarios.add(nuevoCliente);
        nuevoCliente.agregarZonas(operadorDeZonas.getZonas());
    }

    public void eliminarCliente (String nombre) throws IOException {
        for (Usuario usuario: usuarios) {
            if (!usuario.isAdmin()){
                if (usuario.getNombreDeUsuario().equals(nombre)){
                    usuarios.remove(usuario);
                    return;
                }
            }
        }throw new IOException("Nombre no encontrado. ");
    }

    public void eliminarAdmin (String nombre) throws IOException {
        for (Usuario usuario: usuarios) {
            if (usuario.isAdmin()){
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
