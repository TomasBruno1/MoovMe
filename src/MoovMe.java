import java.io.*;
import java.time.LocalTime;

public class MoovMe {
    static OperadorDeUsuarios operadorDeUsuarios = new OperadorDeUsuarios();
    static OperadorDeZonas operadorDeZonas = new OperadorDeZonas();
    static LocalTime horaDelSistema = LocalTime.of(0,0);
    //todo ranking de puntos
    //todo devolver activo en clienteScreen

    public static void main(String[] args) {
        loadFiles();
        homeScreen();
        saveFiles();
    }

    private static void saveFiles() {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("Repositorio.ser");//creates a serial file in output stream
            ObjectOutputStream out = new ObjectOutputStream(fileOut);//routs an object into the output stream.
            out.writeObject(operadorDeUsuarios);// we designate our user operator to be routed
            out.writeObject(operadorDeZonas);// we designate our zone operator to be routed
            out.close();// closes the data paths
            fileOut.close();// closes the data paths
        }catch(IOException i)//exception stuff
        {
            i.printStackTrace();
        }
    }

    private static void loadFiles() {
        try //If this doesnt work throw an exception
        {
            FileInputStream fileIn = new FileInputStream("Repositorio.ser");// Read serial file.
            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            operadorDeUsuarios = (OperadorDeUsuarios) in.readObject();// allocate it to the object file already instanciated.
            operadorDeZonas = (OperadorDeZonas) in.readObject();// allocate it to the object file already instanciated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }catch(FileNotFoundException e){
            return;
        }catch(IOException i)//exception stuff
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)//more exception stuff
        {
            System.out.println("Error");
            c.printStackTrace();
            return;

        }finally {
            operadorDeUsuarios.setOperadorDeZonas(operadorDeZonas);
            operadorDeZonas.setOperadorDeUsuarios(operadorDeUsuarios);
        }
    }

    private static void homeScreen() {
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
                    return;
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
            showAdminScreen();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showAdminScreen() {
        System.out.println("Inicio de sesion exitosa");
        while(true){
            System.out.println("\n" + "------------------------------------" + "\n" +
                    "MOOVME ADMIN" + "\n" +
                    operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +
                    "\n" +
                    "1. Crear Nuevo Admin" + "\n" +
                    "2. Eliminar Admin" + "\n" +
                    "3. Reportar Robo" + "\n" +
                    "4. Desbloquear Cliente" + "\n" +
                    "5. Eliminar Cliente" + "\n" +
                    "6. Ver lista Admins" + "\n" +
                    "7. Ver lista Clientes" + "\n" +
                    "8. Dar de alta nuevo tipo de activo" + "\n" +
                    "9. Comprar activos para zona" + "\n" +
                    "10. Cambiar hora" + "\n" +
                    "11. Agregar descuento" + "\n" +
                    "12. Eliminar descuento" + "\n" +
                    "13. Cerrar sesion" + "\n");

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
                    eliminarCliente();
                    break;
                case 6:
                    verListaAdmins();
                    break;
                case 7:
                    verListaClientes();
                    break;
                case 8:
                    crearTipoActivo();
                    break;
                case 9:
                    comprarLoteActivosParaZona();
                    break;
                case 10:
                    cambiarHoraScreen();
                    return;
                case 11:
                    agregarDescuentoScreen();
                    return;
                case 12:
                    eliminarDescuentoScreen();
                    return;
                case 13:
                    operadorDeUsuarios.cerrarSesion();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void eliminarDescuentoScreen() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                "MOOVME ADMIN" + "\n" +
                operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                "Agregar descuento"+ "\n"+
                "1. Ingresar descripcion, tipo de activo, puntos minimo para usar, zona para descuento, descuento numerico" + "\n" +
                "2. Volver" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    try {

                        String desDescuento = Scanner.getString("Ingrese descripcion del descuento: ");

                        operadorDeUsuarios.eliminarDescuento(desDescuento);

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
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

    private static void agregarDescuentoScreen() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                "MOOVME ADMIN" + "\n" +
                operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                "Agregar descuento"+ "\n"+
                "1. Ingresar descripcion, tipo de activo, puntos minimo para usar, zona para descuento, descuento numerico" + "\n" +
                "2. Volver" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    try {

                        String desDescuento = Scanner.getString("Ingrese descripcion del descuento: ");
                        String nombreTipoDeActivo = Scanner.getString("Ingrese tipo de activo: ");
                        TipoDeActivo tipoDeActivo = operadorDeZonas.getTipoActivo(nombreTipoDeActivo);
                        int puntosMin = Scanner.getInt("Ingrese los puntos minimos para usar: ");
                        String nombreZona = Scanner.getString("Ingrese Zona: ");
                        Zona zona = operadorDeZonas.getZona(nombreZona);
                        int descuentoNum = Scanner.getInt("Ingrese el descuento: ");

                        operadorDeUsuarios.agregarDescuento(((Administrador)operadorDeUsuarios.getUsuarioActivo()),desDescuento, tipoDeActivo,puntosMin,zona,descuentoNum);

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
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

    private static void cambiarHoraScreen() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                "MOOVME ADMIN" + "\n" +
                operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                "Cambiar Hora"+ "\n"+
                "1. Ingresar hora" + "\n" +
                "2. Volver" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    int hora = Scanner.getInt("Ingrese el hora: ");
                    int minuto = Scanner.getInt("Ingrese los minutos: ");
                    horaDelSistema = ((Administrador) operadorDeUsuarios.getUsuarioActivo()).cambiarHora(hora, minuto);
                    break;
                case 2:
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
                operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                "Comprar lote de activos para zona"+ "\n"+
                "1. Ingresar nombre del Lote, tipo de Activo, cantidad, nombre de la zona, precio, tarifa" + "\n" +
                "2. Volver" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    String tipoActivoNombre = Scanner.getString("Ingrese el tipo del activo: ");
                    int cantidad = Scanner.getInt("Ingrese la cantidad de activos: ");
                    String nombreZona = Scanner.getString("Ingrese el nombre de la zona: ");
                    String nombreTerminal = Scanner.getString("Ingrese el nombre de la terminal: ");
                    int precio = Scanner.getInt("Ingrese el precio: ");
                    int tarifa = Scanner.getInt("Ingrese la tarifa: ");
                    int puntos = Scanner.getInt("Ingrese la puntos: ");

                    try{
                        TipoDeActivo tipoDeActivo =  operadorDeZonas.getTipoActivo(tipoActivoNombre);
                        Zona suZona = operadorDeZonas.getZona(nombreZona);
                        Terminal suTerminal = suZona.getTerminal(nombreTerminal);

                        operadorDeZonas.agregarLoteAZona(((Administrador)operadorDeUsuarios.getUsuarioActivo()).crearLoteDeCompraDeActivos( tipoDeActivo, cantidad, suTerminal, precio, tarifa,puntos, operadorDeZonas.getCodigoLoteActual(), operadorDeZonas.getCodigoActivoActual()), suZona.getNombre(), suTerminal);

                        System.out.println("Lote agregado a "+ nombreZona);
                        return;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
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
                operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                "Dar de alta tipo de activo"+ "\n"+
                "1. Ingresar nombre del activo" + "\n" +
                "2. Volver" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    String nombreActivo = Scanner.getString("Ingrese el nombre del activo: ");
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
                operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +
                "Desbloquear Cliente"+ "\n"+
                "1. Ingresar nombre del cliente para desbloquear" + "\n" +
                "2. Volver" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    String nombreCliente = Scanner.getString("Ingrese el nombre del cliente a desbloquear: ");
                    try {
                        ((Administrador) operadorDeUsuarios.getUsuarioActivo()).desbloquearCliente((Cliente) operadorDeUsuarios.getUsuario(nombreCliente));
                        System.out.println("Cliente desbloqueado");
                        return;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
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
                    operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n"+
                    "Bloquear Cliente"+ "\n"+
                    "1. Ingresar nombre del cliente para bloquear" + "\n" +
                    "2. Volver" + "\n");

            while (true){
                switch  (Scanner.getInt("Ingrese una opcion: ")){
                    case 1:
                        String nombreCliente = Scanner.getString("Ingrese el nombre del cliente a bloquear: ");
                        try {
                            ((Administrador)operadorDeUsuarios.getUsuarioActivo()).bloquearCliente((Cliente) operadorDeUsuarios.getUsuario(nombreCliente));
                            System.out.println("Cliente bloqueado");
                            return;
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
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

    private static void eliminarCliente() {
        String nombreIngresado = Scanner.getString("Ingrese un nombre de usuario: ");
        try {
            operadorDeUsuarios.eliminarCliente(nombreIngresado);
            System.out.println("Cliente eliminado");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void eliminarAdmin() {
        String nombreIngresado = Scanner.getString("Ingrese un nombre de usuario: ");
        try {
            operadorDeUsuarios.eliminarAdmin(nombreIngresado);
            System.out.println("Admin eliminado");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void crearAdmin() {
        String nombreIngresado = Scanner.getString("Ingrese un nombre de usuario: ");
        String contrasenaIngresada = Scanner.getString("Ingrese una contraseña: ");
        try {
            operadorDeUsuarios.agregarAdmin(nombreIngresado, contrasenaIngresada);
            System.out.println("Admin agregado");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void userLogin() {
        String nombreIngresado = Scanner.getString("Ingrese su nombre de usuario: ");
        String contrasenaIngresada = Scanner.getString("Ingrese su contraseña: ");

        try {
            operadorDeUsuarios.clienteCheck(nombreIngresado, contrasenaIngresada);
            if(((Cliente)operadorDeUsuarios.getUsuario(nombreIngresado)).getBlocked()) showMultaPaymentScreen();
            showClientScreen();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showMultaPaymentScreen() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() + "! TU USUARIO ESTA BLOQUEADO!" + "\n" +
                "Puedes pagar tu multa de " +
                ((Cliente)operadorDeUsuarios.getUsuarioActivo()).multa.getValorDeMulta() + " pesos para desbloquearte." + "\n"+
                "1. Pagar Multa" + "\n" +
                "2. Cancelar" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    ((Cliente) operadorDeUsuarios.getUsuarioActivo()).pagarMulta();

                case 2:
                    homeScreen();
                default:
                    System.out.println("Opcion invalida");
                    break;

            }
        }
    }

    private static void showClientScreen() {

        System.out.println("Inicio de sesion exitosa");
        while (true){
        System.out.println("\n" + "------------------------------------" + "\n" +
                "MOOVME CLIENTE" + "\n" +
                operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n"+
                "1. Alquilar Activo" + "\n"+
                "2. Devolver Activo" + "\n"+
                "3. Cerrar sesion" + "\n");

            switch  (Scanner.getInt("Ingrese una opcion: ")){
                case 1:
                    if (((Cliente)operadorDeUsuarios.getUsuarioActivo()).tieneActivoEnUso()){
                        System.out.println("Ya tiene un activo alquilado");
                        break;
                    }
                    showAlquilarActivoScreen();
                    break;
                case 2:
                    if (((Cliente)operadorDeUsuarios.getUsuarioActivo()).tieneActivoEnUso()){
                        showDevolverActivoScreen();
                        break;
                    }else System.out.println("No hay un activo alquilado");
                    break;
                case 3:
                    operadorDeUsuarios.cerrarSesion();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }

        }
    }

    private static void showDevolverActivoScreen() {


        String nombreZona = Scanner.getString("Ingrese su zona: ");
        try {
            Zona unaZona = operadorDeZonas.getZona(nombreZona);
            String nombreTerminal = Scanner.getString("Ingrese el nombre de la terminal a la que devuelve su activo: ");
            Terminal unaTerminal = unaZona.getTerminal(nombreTerminal);
            if (((Cliente) operadorDeUsuarios.getUsuarioActivo()).tieneActivoEnUso()) {
                Activo suActivo = ((Cliente) operadorDeUsuarios.getUsuarioActivo()).getActivoEnUso();
                showDescuentoScreen();

                ((Cliente) operadorDeUsuarios.getUsuarioActivo()).devolverActivo(((Cliente) operadorDeUsuarios.getUsuarioActivo()).getActivoEnUso().devolverActivoATerminal(unaTerminal, horaDelSistema));
                System.out.println("Activo devuelto");
                if (!suActivo.estaEnZona()) ((Cliente) operadorDeUsuarios.getUsuarioActivo()).bloquearCliente();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }


    private static void showDescuentoScreen() {
        while (true) {
            System.out.println("\n" + "------------------------------------" + "\n" +
                    "MOOVME CLIENTE" + "\n" +
                    operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() + "\n" +
                    "1. Usar descuentos" + "\n" +
                    "2. No usar descuentos" + "\n");

            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    try {
                        if (operadorDeUsuarios.ofrecerDescuentos((Cliente) operadorDeUsuarios.getUsuarioActivo()).size() != 0) {
                            for (Descuento descuento : operadorDeUsuarios.ofrecerDescuentos((Cliente) operadorDeUsuarios.getUsuarioActivo())) {
                                System.out.println(descuento.getDescripcion());
                            }
                            String descDescuento = Scanner.getString("Ingrese el descuento que quiere usar: ");
                            operadorDeUsuarios.darDescuentoACliente((Cliente) operadorDeUsuarios.getUsuarioActivo(), descDescuento);

                        } else System.out.println("No tiene descuento aplicables");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void showAlquilarActivoScreen() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                "ALQUILER DE ACTIVOS" + "\n" +
                operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() + "\n");
        String nombreDeZona = Scanner.getString("En que zona se encuentra: ");
        try {
            Zona suZona = operadorDeZonas.getZona(nombreDeZona);

            System.out.println("Estas son las terminales de su zona: ");
            for (Terminal terminal: suZona.getTerminales()) {
                System.out.println(terminal.getNombre());
            }
            String nombreDeSuTerminal = Scanner.getString("Ingrese la terminal en la que se encuentra: ");

            System.out.println("Estos son los activos: ");
            suZona.getTerminal(nombreDeSuTerminal).mostrarActivosDeTerminal();

            System.out.println("1. Ingresar el tipo de activo: " + "\n" +
                    "2. Volver: " + "\n");

            while (true) {
                switch (Scanner.getInt("Ingrese una opcion: ")) {
                    case 1:
                        String nombreDelTipoDeActivoElegido = Scanner.getString("Ingrese un tipo de Activo: ");
                        int hora = Scanner.getInt("Ingrese hora estimada de devolucion: ");
                        int minuto = Scanner.getInt("Ingrese minuto estimado de devolucion: ");
                        LocalTime horaEstimadaDeDevolucion = LocalTime.of(hora, minuto);
                        TipoDeActivo tipoDeActivo=  operadorDeZonas.getTipoActivo(nombreDelTipoDeActivoElegido);
                        ((Cliente) operadorDeUsuarios.getUsuarioActivo()).setActivoEnUso(suZona.getTerminal(nombreDeSuTerminal).activosDeTerminalPorTipo(tipoDeActivo).get(0), horaDelSistema, horaEstimadaDeDevolucion);
                        System.out.println("Activo alquilado");
                        return;
                    case 2:
                        return;
                    default:
                        System.out.println("Opcion invalida");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e){
            System.out.println("No hay activos disponibles");
        }catch (IndexOutOfBoundsException e){
            System.out.println("No hay activos disponibles");
        }

    }

    private static void register() {
        String nombreIngresado = Scanner.getString("Ingrese su nombre de usuario: ");
        int numTelIngresado = Scanner.getInt("Ingrese su telefono: ");
        String contrasenaIngresada = Scanner.getString("Ingrese su contraseña: ");
        try {
            operadorDeUsuarios.agregarCliente(nombreIngresado, numTelIngresado,contrasenaIngresada);
            System.out.println("Cliente agregado");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
