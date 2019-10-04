import java.io.IOException;

public class MoovMe {
    static OperadorDeUsuarios joe = new OperadorDeUsuarios();

    public static void main(String[] args) {
        while(true){

            System.out.println("\n" + "------------------------------------" + "\n" +
                    "MOOVME" + "\n" +
                    "\n" +
                    "1. Crear Usuario" + "\n" +
                    "2. Iniciar sesion como Cliente" + "\n" +
                    "3. Iniciar sesion como Administrador" + "\n" +
                    "4. Salir" + "\n");
            switch  (Scanner.getInt("Ingrese una opcion: ")){
                case 1:
                    register();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    adminLogin();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void adminLogin() {
        String nombreIngresado = Scanner.getString("Ingrese su nombre de usuario: ");
        String contrasenaIngresada = Scanner.getString("Ingrese su contraseña: ");
        try {
            joe.adminCheck(nombreIngresado, contrasenaIngresada);
            showAdminScreen();
        } catch (ContrasenaIncorrectaException e) {
            System.out.println("La contraseña es incorrecta.");
        } catch (UsuarioIncorrectoException e) {
            System.out.println("El usuario es incorrecto.");
        }
    }

    private static void showAdminScreen() {
        System.out.println("Inicio de sesion exitosa");
        while(true){
            System.out.println("\n" + "------------------------------------" + "\n" +
                    "MOOVME ADMIN" + "\n" +
                    "\n" +
                    "1. Crear Nuevo Admin" + "\n" +
                    "2. Eliminar Admin" + "\n" +
                    "3. Bloquear Cliente" + "\n" +
                    "4. Desbloquear Cliente" + "\n" +
                    "5. Ver lista Admins" + "\n" +
                    "6. Ver lista Clientes" + "\n" +
                    "7. Cerrar sesion" + "\n");

            switch  (Scanner.getInt("Ingrese una opcion: ")){
                case 1:
                    crearAdmin();
                    break;
                case 2:
                    eliminarAdmin();
                    break;
                case 3:
                    bloquearCliente();
                    break;
                case 4:
                    desbloquearCliente();
                    break;
                case 5:
                    verListaAdmins();
                    break;
                case 6:
                    verListaClientes();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void verListaClientes() {
        for (Cliente cliente: joe.getClientes()) {
            System.out.println("-" + cliente.getNombreDeUsuario());
        }
    }

    private static void verListaAdmins() {
        for (Administrador admin: joe.getAdmins()) {
            System.out.println("-" + admin.getNombreDeUsuario());
        }
    }

    private static void desbloquearCliente() {
    }

    private static void bloquearCliente() {
    }

    private static void eliminarAdmin() {
        String nombreIngresado = Scanner.getString("Ingrese un nombre de usuario: ");

        try {
            joe.eliminarAdmin(nombreIngresado);
            System.out.println("Admin eliminado");
        } catch (IOException e) {
            System.out.println("Nombre no encontrado");
        }
    }

    private static void crearAdmin() {
        String nombreIngresado = Scanner.getString("Ingrese un nombre de usuario: ");
        String contrasenaIngresada = Scanner.getString("Ingrese una contraseña: ");
        joe.agregarAdmin(nombreIngresado, contrasenaIngresada);
        System.out.println("Admin agregado");
    }

    private static void userLogin() {

    }

    private static void register() {
        //todo requisitos de contraseña y telefono valido, chequeo de unico usuario con persistencia
    }
}
