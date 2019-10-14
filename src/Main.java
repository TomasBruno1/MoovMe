public class Main {
    public static void Main (String[]Args){
         Zona zona = new ZonaNorte();

        Administrador admin = new Administrador();

        TipoDeActivo auto = new TipoDeActivo("auto");

        Lote lote = admin.crearLoteDeCompraDeActivos("Lote De Zona Sur", auto, 3, new ZonaNorte(),1000,10 );

        Cliente tefi = new Cliente("Tefi");

        int codigo = lote.activosDelLote.get(0).getCodigo();

        tefi.usarActivo(auto);

        admin.adjudicarActivoAlCliente(tefi, auto, lote);

        if(!tefi.getActivoEnUso().estaEnZona()){
            admin.multarCliente(tefi);
            admin.bloquearCliente(tefi);
        }
        
    }
}
