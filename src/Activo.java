public class Activo {
    int puntos;
    int tarifaMin;
    Terminal terminalDeOrigen;
    Terminal terminalActual;
    int valorDeMulta;
    boolean estaEnZona;
    public static int codigo = 1;
    int codigoReal;
    int precioFijo;
    TipoDeActivo nombre;
    Lote lote;

    public Activo(TipoDeActivo nombre, Terminal terminalDeOrigen, int precio, int tarifaMin) {
        this.nombre = nombre;
        this.terminalDeOrigen = terminalDeOrigen;
        this.terminalActual = terminalDeOrigen;
        estaEnZona = true;
        this.tarifaMin = tarifaMin;
        precioFijo= precio;
        valorDeMulta= precio/2;
        codigoReal = this.codigo++;
        this.lote = null;
    }
    //Metodos
    public int getValorDeMulta(){
        return valorDeMulta;
    }


    public int getPrecioFijo(){
        return precioFijo;
    }

    public boolean estaEnZona(){
        if(!this.terminalActual.equals(this.terminalDeOrigen)){
            estaEnZona = false;
            return false;
        }return true;
    }
    public int getCodigo(){
        return codigoReal;
    }
    public int getPrecioDeTarifa(){
        return this.tarifaMin;
    }
    public int getPuntos(){
        return this.puntos;
    }
    public boolean getSiEstaEnZona(){
        return estaEnZona;
    }
    public TipoDeActivo getNombre() {
        return nombre;
    }

    public Lote getLote() {
        return lote;
    }
    public void cambiarLote (Lote lote){
        this.lote = lote;
    }
}