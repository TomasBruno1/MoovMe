import java.io.IOException;

public class Main {
    public static void main (String[]Args) throws IOException {
         Zona zona = new ZonaNorte();

        Administrador admin = new Administrador("admin", "admin");

        TipoDeActivo auto = new TipoDeActivo("auto");

        Lote lote = admin.crearLoteDeCompraDeActivos("Lote De Zona Sur", auto, 3, new ZonaNorte(),1000,10 );

        Cliente tefi = new Cliente("Tefi", 1234, "contrasena");

        int codigo = lote.activosDelLote.get(0).getCodigo();

        try {
            tefi.usarActivo(lote.getActivoPorCodigo(codigo));
        } catch (IOException e) {
            System.out.println("No se encontro el activo");
        }

        admin.adjudicarActivoAlCliente(tefi, auto, lote);

        if(!tefi.getActivoEnUso().estaEnZona()){
            admin.multarCliente(tefi);
            admin.bloquearCliente(tefi);
        }
        
    }
}
