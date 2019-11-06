import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;

public class Activo implements Serializable {
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
    Status status;
    LocalTime tiempoEnElQueSeAlquilo;
    LocalTime tiempoEstimadoDeDevolucion;


    public Activo(TipoDeActivo nombre, Terminal terminalDeOrigen, int precio, int tarifaMin, int puntos) {
        this.nombre = nombre;
        this.terminalDeOrigen = terminalDeOrigen;
        this.terminalActual = terminalDeOrigen;
        estaEnZona = true;
        this.tarifaMin = tarifaMin;
        precioFijo= precio;
        valorDeMulta= precio/2;
        codigoReal = this.codigo++;
        this.lote = null;
        this.puntos= puntos;
        status = new Disponible();
    }

    //Metodos

    public void enUso(){
        status = new EnUso();
    }

    public void disponible(){
        status = new Disponible();
    }

    public void retirarActivoDeTerminal(LocalTime horaEnLaQueSeAlquilo, LocalTime horaEstimadaDeDevolucion) throws IOException {
        terminalActual = null;
        status = status.usar();
        tiempoEnElQueSeAlquilo = horaEnLaQueSeAlquilo;
        tiempoEstimadoDeDevolucion = horaEstimadaDeDevolucion;
    }

    public boolean devolverActivoATerminal (Terminal unaTerminal, LocalTime horaAChequear) throws IOException {
        terminalActual = unaTerminal;
        status = status.devolver();
        if(horaAChequear.isAfter(tiempoEstimadoDeDevolucion)) {
            tiempoEstimadoDeDevolucion = null;
            return true;
        }
        else{
            tiempoEstimadoDeDevolucion = null;
            return false;
        }
    }

    public Terminal getTerminalActual() {
        return terminalActual;
    }

    public Terminal getTerminalDeOrigen() {
        return terminalDeOrigen;
    }

    public int getValorDeMulta(){
        return valorDeMulta;
    }


    public int getPrecioFijo(){
        return precioFijo;
    }

    public boolean estaEnZona(){
        if(!this.terminalActual.getZona().equals(this.terminalDeOrigen.getZona())){
            estaEnZona = false;
            return false;
        }else {
            estaEnZona = true;
            return true;
        }
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
    public Status getStatus(){
        return this.status;
    }

}