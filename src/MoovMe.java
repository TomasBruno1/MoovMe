import java.io.IOException;

public class MoovMe {
    static OperadorDeUsuarios operadorDeUsuarios = new OperadorDeUsuarios();
    static Usuario usuarioActivo = null;
    static OperadorDeZonas operadorDeZonas = new OperadorDeZonas();

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
            operadorDeUsuarios.adminCheck(nombreIngresado, contrasenaIngresada);
            usuarioActivo = operadorDeUsuarios.getUsuario(nombreIngresado);
            showAdminScreen();
        } catch (ContrasenaIncorrectaException e) {
            System.out.println("La contraseña es incorrecta.");
        } catch (UsuarioIncorrectoException e) {
            System.out.println("El usuario es incorrecto.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showAdminScreen() {
        System.out.println("Inicio de sesion exitosa");
        while(true){
            System.out.println("\n" + "------------------------------------" + "\n" +
                    "MOOVME ADMIN" + "\n" +
                    usuarioActivo.getNombreDeUsuario() +
                    "\n" +
                    "1. Crear Nuevo Admin" + "\n" +
                    "2. Eliminar Admin" + "\n" +
                    "3. Bloquear Cliente (Declarar robo)" + "\n" +
                    "4. Desbloquear Cliente" + "\n" +
                    "5. Ver lista Admins" + "\n" +
                    "6. Ver lista Clientes" + "\n" +
                    "7. Dar de alta nuevo tipo de activo" + "\n" +
                    "8. Comprar activos para zona" + "\n" +
                    "9. Cerrar sesion" + "\n");

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
                    crearTipoActivo();
                    break;
                case 8:
                    comprarLoteActivosParaZona();
                    break;
                case 9:
                    usuarioActivo = null;
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void comprarLoteActivosParaZona() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                "MOOVME ADMIN" + "\n" +
                usuarioActivo.getNombreDeUsuario() +"\n" +
                "Comprar lote de activos para zona"+ "\n"+
                "1. Ingresar nombre del Lote, tipo de Activo, cantidad, nombre de la zona, precio, tarifa" + "\n" +
                "2. Volver" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    String nombreLote = Scanner.getString("Ingrese el nombre del lote: ");
                    String tipoActivoNombre = Scanner.getString("Ingrese el tipo del activo: ");
                    int cantidad = Scanner.getInt("Ingrese la cantidad de activos: ");
                    String nombreZona = Scanner.getString("Ingrese el nombre de la zona: ");
                    int precio = Scanner.getInt("Ingrese el precio: ");
                    int tarifa = Scanner.getInt("Ingrese la tarifa: ");

                    try{
                        TipoDeActivo tipoDeActivo =  operadorDeZonas.getTipoActivo(tipoActivoNombre);
                        Zona suZona = operadorDeZonas.getZona(nombreZona);
                        operadorDeZonas.agregarLoteAZona(((Administrador)usuarioActivo).crearLoteDeCompraDeActivos(nombreLote, tipoDeActivo, cantidad, suZona, precio, tarifa), suZona.getNombre());
                        System.out.println("Lote agregado a "+ nombreZona);
                        return;
                    } catch (IOException e) {
                        System.out.println("Lote invalido");
                    }
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void crearTipoActivo() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                "MOOVME ADMIN" + "\n" +
                usuarioActivo.getNombreDeUsuario() +"\n" +
                "Dar de alta tipo de activo"+ "\n"+
                "1. Ingresar nombre del activo" + "\n" +
                "2. Volver" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    String nombreActivo = Scanner.getString("Ingrese el nombre del activo");
                    try {
                        operadorDeZonas.agregarTipoDeActivo(nombreActivo);
                        System.out.println("Tipo de Activo dado de alta");
                        return;
                    } catch (IOException e) {
                        System.out.println("Tipo de activo ya existe");
                    }
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void verListaClientes() {
        for (Cliente cliente: operadorDeUsuarios.getClientes()) {
            System.out.println("-" + cliente.getNombreDeUsuario());
        }
    }

    private static void verListaAdmins() {
        for (Administrador admin: operadorDeUsuarios.getAdmins()) {
            System.out.println("-" + admin.getNombreDeUsuario());
        }
    }

    private static void desbloquearCliente() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                "MOOVME ADMIN" + "\n" +
                usuarioActivo.getNombreDeUsuario() +
                "Desbloquear Cliente"+ "\n"+
                "1. Ingresar nombre del cliente para desbloquear" + "\n" +
                "2. Volver" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    String nombreCliente = Scanner.getString("Ingrese el nombre del cliente a desbloquear");
                    try {
                        ((Administrador) usuarioActivo).desbloquearCliente((Cliente) operadorDeUsuarios.getUsuario(nombreCliente));
                        System.out.println("Cliente desbloqueado");
                        return;
                    } catch (IOException e) {
                        System.out.println("Cliente no encontrado");
                    }
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void bloquearCliente() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                "MOOVME ADMIN" + "\n" +
                usuarioActivo.getNombreDeUsuario() +"\n"+
                "Bloquear Cliente"+ "\n"+
                "1. Ingresar nombre del cliente para bloquear" + "\n" +
                "2. Volver" + "\n");

        while (true){
            switch  (Scanner.getInt("Ingrese una opcion: ")){
            case 1:
                String nombreCliente = Scanner.getString("Ingrese el nombre del cliente a bloquear");
                try {
                    ((Administrador)usuarioActivo).bloquearCliente((Cliente) operadorDeUsuarios.getUsuario(nombreCliente));
                    System.out.println("Cliente bloqueado");
                    //todo multar al cliente
                    return;
                } catch (IOException e) {
                    System.out.println("Cliente no encontrado");
                }
                break;

            case 2:
                return;
                default:
                    System.out.println("Opcion invalida");
                    break;

            }


        }
    }

    private static void eliminarAdmin() {
        String nombreIngresado = Scanner.getString("Ingrese un nombre de usuario: ");

        try {
            operadorDeUsuarios.eliminarAdmin(nombreIngresado);
            System.out.println("Admin eliminado");
        } catch (IOException e) {
            System.out.println("Nombre no encontrado");
        }
    }

    private static void crearAdmin() {
        String nombreIngresado = Scanner.getString("Ingrese un nombre de usuario: ");
        String contrasenaIngresada = Scanner.getString("Ingrese una contraseña: ");
        try {
            operadorDeUsuarios.agregarAdmin(nombreIngresado, contrasenaIngresada);
            System.out.println("Admin agregado");

        } catch (IOException e) {
            System.out.println("El admin ya existe");
        }
    }

    private static void userLogin() {
        String nombreIngresado = Scanner.getString("Ingrese su nombre de usuario: ");
        int numTelIngresado = Scanner.getInt("Ingrese su telefono: ");
        String contrasenaIngresada = Scanner.getString("Ingrese su contraseña: ");
        try {
            operadorDeUsuarios.clienteCheck(nombreIngresado, numTelIngresado, contrasenaIngresada);
            usuarioActivo = operadorDeUsuarios.getUsuario(nombreIngresado);
            showClientScreen();
        } catch (IOException e) {
            System.out.println("El cliente es invalido.");
        }
    }

    private static void showClientScreen() {
        
        //todo ver pantalla si el cliente esta bloqueado, agregar opcion de pago
        System.out.println("Inicio de sesion exitosa");
        while (true){
        System.out.println("\n" + "------------------------------------" + "\n" +
                "MOOVME CLIENTE" + "\n" +
                usuarioActivo.getNombreDeUsuario() +"\n"+
                "1. Ver Activos Disponibles" + "\n"+
                "2. Cerrar sesion" + "\n");

            switch  (Scanner.getInt("Ingrese una opcion: ")){

                case 1:
                    showActivosDisponibles();
                    break;

                case 2:
                    usuarioActivo = null;
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }


        }
    }

    private static void showActivosDisponibles() {
        System.out.println("Estos son los activos");
        //todo mostrar activos
    }

    private static void register() {
        String nombreIngresado = Scanner.getString("Ingrese su nombre de usuario: ");
        int numTelIngresado = Scanner.getInt("Ingrese su telefono: ");
        String contrasenaIngresada = Scanner.getString("Ingrese su contraseña: ");
        try {
            operadorDeUsuarios.agregarCliente(nombreIngresado, numTelIngresado,contrasenaIngresada);
            System.out.println("Cliente agregado");

        } catch (IOException e) {
            System.out.println("El nombre ya fue utilizado");
        }
    }
}
