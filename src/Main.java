public class Main {
    public static void Main (String[]Args){
        Zona zona = new ZonaNorte();
        Activo auto = new Auto(zona);
        System.out.println(auto.tarifaMin);

        
    }
}
