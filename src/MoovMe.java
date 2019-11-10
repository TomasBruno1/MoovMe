import java.io.*;
import java.time.DateTimeException;
import java.time.LocalTime;

public class MoovMe {
    static OperadorDeUsuarios operadorDeUsuarios = new OperadorDeUsuarios();
    static OperadorDeZonas operadorDeZonas = new OperadorDeZonas();
    static LocalTime horaDelSistema = LocalTime.now();

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
                    horaDelSistema.toString() + "\n" +
                    "MOOVME" + "\n" +
                    "\n" +
                    "1. Crear Usuario" + "\n" +
                    "2. Iniciar sesion como Cliente" + "\n" +
                    "3. Iniciar sesion como Administrador" + "\n" +
                    "4. Salir" + "\n");
            switch  (Scanner.getInt("Ingrese una opcion: ")){
                case 1:
                    clearScreen();
                    register();
                    break;
                case 2:
                    clearScreen();
                    userLogin();
                    break;
                case 3:
                    clearScreen();
                    adminLogin();
                    break;
                case 4:
                    clearScreen();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
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
        clearScreen();
        System.out.println("Inicio de sesion exitosa");
        while(true){
            System.out.println("\n" + "------------------------------------" + "\n" +
                    horaDelSistema.toString() + "\n" +
                    "MOOVME ADMIN" + "\n" +
                    "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +
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
                    "13. Agregar terminal" + "\n" +
                    "14. Eliminar terminal" + "\n" +
                    "15. Ver ranking" + "\n" +
                    "16. Reiniciar ranking" + "\n" +
                    "17. Cerrar sesion" + "\n");

            switch  (Scanner.getInt("Ingrese una opcion: ")){
                case 1:
                    clearScreen();
                    crearAdmin();
                    break;
                case 2:
                    clearScreen();
                    eliminarAdmin();
                    break;
                case 3:
                    clearScreen();
                    bloquearCliente();
                    break;
                case 4:
                    clearScreen();
                    desbloquearCliente();
                    break;
                case 5:
                    clearScreen();
                    eliminarCliente();
                    break;
                case 6:
                    clearScreen();
                    verListaAdmins();
                    break;
                case 7:
                    clearScreen();
                    verListaClientes();
                    break;
                case 8:
                    clearScreen();
                    crearTipoActivo();
                    break;
                case 9:
                    clearScreen();
                    comprarLoteActivosParaZona();
                    break;
                case 10:
                    clearScreen();
                    cambiarHoraScreen();
                    break;
                case 11:
                    clearScreen();
                    agregarDescuentoScreen();
                    break;
                case 12:
                    clearScreen();
                    eliminarDescuentoScreen();
                    break;
                case 13:
                    clearScreen();
                    agregarTerminalScreen();
                    break;
                case 14:
                    clearScreen();
                    eliminarTerminalScreen();
                    break;
                case 15:
                    clearScreen();
                    rankingScreen();
                    break;
                case 16:
                    clearScreen();
                    try {
                        operadorDeUsuarios.resetRanking();
                        System.out.println("Ranking reinciado");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 17:
                    clearScreen();
                    operadorDeUsuarios.cerrarSesion();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void eliminarTerminalScreen() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                horaDelSistema.toString() + "\n" +
                "MOOVME ADMIN" + "\n" +
                "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                "Eliminar terminal"+ "\n"+
                "1. Ingresar nombre de la terminal para eliminarla" + "\n" +
                "2. Volver" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    try {

                        String nombreZona = Scanner.getString("Ingrese Zona de la Terminal: ");
                        Zona zona = operadorDeZonas.getZona(nombreZona);

                        String nombreTerminal = Scanner.getString("Ingrese el nombre de la Terminal: ");
                        zona.eliminarTerminal(nombreTerminal);
                        clearScreen();
                        System.out.println("Terminal eliminada");

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                case 2:
                    clearScreen();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void agregarTerminalScreen() {
        while (true) {
            System.out.println("\n" + "------------------------------------" + "\n" +
                    horaDelSistema.toString() + "\n" +
                    "MOOVME ADMIN" + "\n" +
                    "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                    "Agregar Terminal"+ "\n"+
                    "1. Ingresar zona de la terminal y su nombre" + "\n" +
                    "2. Volver" + "\n");


            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    try {
                        String nombreZona = Scanner.getString("Ingrese Zona de la Terminal: ");
                        Zona zona = operadorDeZonas.getZona(nombreZona);

                        String nombreTerminal = Scanner.getString("Ingrese el nombre de la Terminal: ");
                        zona.agregarTerminal(nombreTerminal, zona);
                        clearScreen();
                        System.out.println("Terminal agregada");

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    clearScreen();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void rankingScreen() {
        try {
            operadorDeUsuarios.imprimirRanking();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void eliminarDescuentoScreen() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                horaDelSistema.toString() + "\n" +
                "MOOVME ADMIN" + "\n" +
                "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                "Eliminar descuento"+ "\n"+
                "1. Ingresar descripcion para eliminar descuento" + "\n" +
                "2. Volver" + "\n");

        while (true) {
            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    try {

                        String desDescuento = Scanner.getString("Ingrese descripcion del descuento: ");

                        operadorDeUsuarios.eliminarDescuento(desDescuento);
                        clearScreen();
                        System.out.println("Descuento eliminado");

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    clearScreen();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void agregarDescuentoScreen() {
        while (true) {
            System.out.println("\n" + "------------------------------------" + "\n" +
                    horaDelSistema.toString() + "\n" +
                "MOOVME ADMIN" + "\n" +
                    "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                "Agregar descuento"+ "\n"+
                "1. Ingresar descripcion, tipo de activo, puntos minimo para usar, zona para descuento, descuento numerico" + "\n" +
                "2. Volver" + "\n");


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
                        clearScreen();
                        System.out.println("Descuento agregado");

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                case 2:
                    clearScreen();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void cambiarHoraScreen() {
        while (true) {

            System.out.println("\n" + "------------------------------------" + "\n" +
                    horaDelSistema.toString() + "\n" +
                "MOOVME ADMIN" + "\n" +
                    "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                "Cambiar Hora"+ "\n"+
                "1. Ingresar hora" + "\n" +
                "2. Volver" + "\n");

            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    int hora = Scanner.getInt("Ingrese el hora: ");
                    int minuto = Scanner.getInt("Ingrese los minutos: ");
                    try {
                        horaDelSistema = ((Administrador) operadorDeUsuarios.getUsuarioActivo()).cambiarHora(hora, minuto);
                    }catch (DateTimeException e){
                        System.out.println("La hora ingresada no es valida. ");
                    }
                    clearScreen();
                    System.out.println("Hora cambiada");
                    return;
                case 2:
                    clearScreen();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void comprarLoteActivosParaZona() {
        while (true) {

            System.out.println("\n" + "------------------------------------" + "\n" +
                    horaDelSistema.toString() + "\n" +
                "MOOVME ADMIN" + "\n" +
                    "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                "Comprar lote de activos para zona"+ "\n"+
                "1. Ingresar  tipo de Activo, cantidad, nombre de la zona, nombre de la terminal, precio, tarifa, puntos" + "\n" +
                "2. Volver" + "\n");

            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    try{

                        String tipoActivoNombre = Scanner.getString("Ingrese el tipo del activo: ");
                        TipoDeActivo tipoDeActivo =  operadorDeZonas.getTipoActivo(tipoActivoNombre);

                        int cantidad = Scanner.getInt("Ingrese la cantidad de activos: ");
                        String nombreZona = Scanner.getString("Ingrese el nombre de la zona: ");
                        Zona suZona = operadorDeZonas.getZona(nombreZona);

                        String nombreTerminal = Scanner.getString("Ingrese el nombre de la terminal: ");
                        Terminal suTerminal = suZona.getTerminal(nombreTerminal);

                        int precio = Scanner.getInt("Ingrese el precio: ");
                        int tarifa = Scanner.getInt("Ingrese la tarifa: ");
                        int puntos = Scanner.getInt("Ingrese la puntos: ");


                        operadorDeZonas.agregarLoteAZona(((Administrador)operadorDeUsuarios.getUsuarioActivo()).crearLoteDeCompraDeActivos( tipoDeActivo, cantidad, suTerminal, precio, tarifa,puntos, operadorDeZonas.getCodigoLoteActual(), operadorDeZonas.getCodigoActivoActual()), suZona.getNombre(), suTerminal);
                        clearScreen();
                        System.out.println("Lote agregado a "+ nombreZona);
                        return;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                case 2:
                    clearScreen();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void crearTipoActivo() {
        while (true) {

            System.out.println("\n" + "------------------------------------" + "\n" +
                    horaDelSistema.toString() + "\n" +
                "MOOVME ADMIN" + "\n" +
                    "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" +
                "Dar de alta tipo de activo"+ "\n"+
                "1. Ingresar nombre del activo" + "\n" +
                "2. Volver" + "\n");

            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    String nombreActivo = Scanner.getString("Ingrese el nombre del activo: ");
                    try {
                        operadorDeZonas.agregarTipoDeActivo(nombreActivo);
                        clearScreen();
                        System.out.println("Tipo de Activo dado de alta");
                        return;
                    } catch (IOException e) {
                        System.out.println("Tipo de activo ya existe");
                    }
                    return;
                case 2:
                    clearScreen();
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
        while (true) {

            System.out.println("\n" + "------------------------------------" + "\n" +
                    horaDelSistema.toString() + "\n" +
                "MOOVME ADMIN" + "\n" +
                    "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +
                "Desbloquear Cliente"+ "\n"+
                "1. Ingresar nombre del cliente para desbloquear" + "\n" +
                "2. Volver" + "\n");

            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    String nombreCliente = Scanner.getString("Ingrese el nombre del cliente a desbloquear: ");
                    try {
                        ((Administrador) operadorDeUsuarios.getUsuarioActivo()).desbloquearCliente((Cliente) operadorDeUsuarios.getUsuario(nombreCliente));
                        clearScreen();
                        System.out.println("Cliente desbloqueado");
                        return;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                case 2:
                    clearScreen();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void bloquearCliente() {
        while (true){

            System.out.println("\n" + "------------------------------------" + "\n" +
                    horaDelSistema.toString() + "\n" +
                    "MOOVME ADMIN" + "\n" +
                    "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n"+
                    "Bloquear Cliente"+ "\n"+
                    "1. Ingresar nombre del cliente para bloquear" + "\n" +
                    "2. Volver" + "\n");

                switch  (Scanner.getInt("Ingrese una opcion: ")){
                    case 1:
                        String nombreCliente = Scanner.getString("Ingrese el nombre del cliente a bloquear: ");
                        try {
                            ((Administrador)operadorDeUsuarios.getUsuarioActivo()).bloquearCliente((Cliente) operadorDeUsuarios.getUsuario(nombreCliente));
                            clearScreen();
                            System.out.println("Cliente bloqueado");
                            return;
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                        return;

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
            clearScreen();
            System.out.println("Cliente eliminado");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void eliminarAdmin() {
        String nombreIngresado = Scanner.getString("Ingrese un nombre de usuario: ");
        try {
            operadorDeUsuarios.eliminarAdmin(nombreIngresado);
            clearScreen();
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
            clearScreen();
            System.out.println("Admin agregado");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void userLogin() {
        String nombreIngresado = Scanner.getString("Ingrese su nombre de usuario: ");
        String contrasenaIngresada = Scanner.getString("Ingrese su contraseña: ");
        clearScreen();

        try {
            operadorDeUsuarios.clienteCheck(nombreIngresado, contrasenaIngresada);
            if(((Cliente)operadorDeUsuarios.getUsuario(nombreIngresado)).getBlocked()) showMultaPaymentScreen();
            showClientScreen();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showMultaPaymentScreen() {
        while (true) {

            System.out.println("\n" + "------------------------------------" + "\n" +
                operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() + "! TU USUARIO ESTA BLOQUEADO!" + "\n" +
                "Puedes pagar tu multa de " +
                ((Cliente)operadorDeUsuarios.getUsuarioActivo()).multa.getValorDeMulta() + " pesos para desbloquearte." + "\n"+
                "1. Pagar Multa" + "\n" +
                "2. Cancelar" + "\n");

            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    ((Cliente) operadorDeUsuarios.getUsuarioActivo()).pagarMulta();
                    clearScreen();
                    System.out.println("Multa pagada");

                case 2:
                    clearScreen();
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
                horaDelSistema.toString() + "\n" +
                "MOOVME CLIENTE" + "\n" +
                "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() +"\n" );
        if(((Cliente) operadorDeUsuarios.getUsuarioActivo()).tieneActivoEnUso()) {
            try {
                System.out.println("Su activo actual es: "+
                        ((Cliente) operadorDeUsuarios.getUsuarioActivo()).getActivoEnUso().getTipoDeActivo().getNombre()+"\n"+
                "Codigo: "+ ((Cliente) operadorDeUsuarios.getUsuarioActivo()).getActivoEnUso().getCodigo()+"\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(
                        "1. Alquilar Activo" + "\n"+
                        "2. Devolver Activo" + "\n"+
                        "3. Ver Puntos acumulados por zona" + "\n"+
                        "4. Ver Ranking" + "\n"+
                        "5. Cerrar sesion" + "\n");

            switch  (Scanner.getInt("Ingrese una opcion: ")){
                case 1:
                    if (((Cliente)operadorDeUsuarios.getUsuarioActivo()).tieneActivoEnUso()){
                        clearScreen();
                        System.out.println("Ya tiene un activo alquilado");
                        break;
                    }
                    clearScreen();
                    showAlquilarActivoScreen();
                    break;
                case 2:
                    if (((Cliente)operadorDeUsuarios.getUsuarioActivo()).tieneActivoEnUso()){
                        int resultado = showDevolverActivoScreen();
                        if(resultado == 1) {
                            clearScreen();
                            System.out.println("Fuiste bloqueado");
                            return;
                        }
                        break;
                    }else System.out.println("No hay un activo alquilado");
                    break;
                case 3:
                    clearScreen();
                    puntosScreen();
                    break;
                case 4:
                    clearScreen();
                    rankingScreen();
                    break;
                case 5:
                    clearScreen();
                    operadorDeUsuarios.cerrarSesion();
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }

        }
    }

    private static void puntosScreen() {
        try {
            System.out.println("Puntos Zona Sur: " + ((Cliente) operadorDeUsuarios.getUsuarioActivo()).getPuntosPorZona(operadorDeZonas.getZona("ZonaSur")));
            System.out.println("Puntos Zona Norte: " + ((Cliente) operadorDeUsuarios.getUsuarioActivo()).getPuntosPorZona(operadorDeZonas.getZona("ZonaNorte")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }




    private static int showDevolverActivoScreen() {

        try {
            System.out.println("Su activo costara: "+ ((Cliente)operadorDeUsuarios.getUsuarioActivo()).getPrecio(horaDelSistema));

            String nombreZona = Scanner.getString("Ingrese su zona: ");

            Zona unaZona = operadorDeZonas.getZona(nombreZona);
            String nombreTerminal = Scanner.getString("Ingrese el nombre de la terminal a la que devuelve su activo: ");
            Terminal unaTerminal = unaZona.getTerminal(nombreTerminal);
            if (((Cliente) operadorDeUsuarios.getUsuarioActivo()).tieneActivoEnUso()) {
                Activo suActivo = ((Cliente) operadorDeUsuarios.getUsuarioActivo()).getActivoEnUso();
                clearScreen();
                showDescuentoScreen();
                if (((Cliente) operadorDeUsuarios.getUsuarioActivo()).getDescuentoEnUso() != null) {
                    System.out.println("El precio con el descuento aplicado es de: " + ((Cliente)operadorDeUsuarios.getUsuarioActivo()).getPrecio(horaDelSistema));
                    ((Cliente) operadorDeUsuarios.getUsuarioActivo()).eliminarDescuento();
                }
                suActivo.checkEstaEnZona(unaTerminal);

                if (!(suActivo.getEstaEnZona())){
                    ((Cliente) operadorDeUsuarios.getUsuarioActivo()).bloquearCliente();
                    return 1;
                }else {

                    ((Cliente) operadorDeUsuarios.getUsuarioActivo()).devolverActivo(((Cliente) operadorDeUsuarios.getUsuarioActivo()).getActivoEnUso().devolverActivoATerminal(unaTerminal, horaDelSistema));
                    System.out.println("Activo devuelto");
                    return  0;
                }
            }
            return  0;
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        return  0;
    }


    private static void showDescuentoScreen() {
        while (true) {
            System.out.println("\n" + "------------------------------------" + "\n" +
                    horaDelSistema.toString() + "\n" +
                    "MOOVME CLIENTE" + "\n" +
                    "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() + "\n" +
                    "1. Usar descuentos" + "\n" +
                    "2. No usar descuento" + "\n");

            switch (Scanner.getInt("Ingrese una opcion: ")) {
                case 1:
                    try {
                        if (operadorDeUsuarios.ofrecerDescuentos((Cliente) operadorDeUsuarios.getUsuarioActivo()).size() != 0) {
                            for (Descuento descuento : operadorDeUsuarios.ofrecerDescuentos((Cliente) operadorDeUsuarios.getUsuarioActivo())) {
                                System.out.println(descuento.getDescripcion() + " " + descuento.getDescuentoNumerico());
                            }
                            String descDescuento = Scanner.getString("Ingrese el descuento que quiere usar: ");
                            operadorDeUsuarios.darDescuentoACliente((Cliente) operadorDeUsuarios.getUsuarioActivo(), descDescuento);

                        } else System.out.println("No tiene descuentos aplicables");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                case 2:
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private static void showAlquilarActivoScreen() {
        System.out.println("\n" + "------------------------------------" + "\n" +
                horaDelSistema.toString() + "\n" +
                "ALQUILER DE ACTIVOS" + "\n" +
                "Usuario: " +operadorDeUsuarios.getUsuarioActivo().getNombreDeUsuario() + "\n");
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
                        try {
                            LocalTime horaEstimadaDeDevolucion = LocalTime.of(hora, minuto);
                            TipoDeActivo tipoDeActivo=  operadorDeZonas.getTipoActivo(nombreDelTipoDeActivoElegido);
                            ((Cliente) operadorDeUsuarios.getUsuarioActivo()).setActivoEnUso(suZona.getTerminal(nombreDeSuTerminal).activosDeTerminalPorTipo(tipoDeActivo).get(0), horaDelSistema, horaEstimadaDeDevolucion);
                        }catch(DateTimeException e){
                            System.out.println("La hora ingresada no es valida");
                        }
                        clearScreen();
                        System.out.println("Activo alquilado");
                        return;
                    case 2:
                        clearScreen();
                        return;
                    default:
                        System.out.println("Opcion invalida");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException | IndexOutOfBoundsException e){
            System.out.println("No hay activos disponibles");
        }

    }

    private static void register() {
        String nombreIngresado = Scanner.getString("Ingrese su nombre de usuario: ");
        int numTelIngresado = Scanner.getInt("Ingrese su telefono: ");
        String contrasenaIngresada = Scanner.getString("Ingrese su contraseña: ");
        try {
            operadorDeUsuarios.agregarCliente(nombreIngresado, numTelIngresado,contrasenaIngresada);
            clearScreen();
            System.out.println("Cliente "+nombreIngresado + " agregado");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
