import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;

public class Activo implements Serializable {
    public static final long serialVersionUID = 1L;

    int puntos;
    int tarifaMin;
    Terminal terminalDeOrigen;
    Terminal terminalActual;
    int valorDeMulta;
    boolean estaEnZona;
    int codigoReal;
    int precioFijo;
    TipoDeActivo tipoDeActivo;
    Lote lote;
    Status status;
    LocalTime tiempoEnElQueSeAlquilo;
    LocalTime tiempoEstimadoDeDevolucion;


    public Activo(TipoDeActivo tipoDeActivo, Terminal terminalDeOrigen, int precio, int tarifaMin, int puntos, int codigo) {
        this.tipoDeActivo = tipoDeActivo;
        this.terminalDeOrigen = terminalDeOrigen;
        this.terminalActual = terminalDeOrigen;
        estaEnZona = true;
        this.tarifaMin = tarifaMin;
        precioFijo = precio;
        valorDeMulta = precio*10;
        codigoReal = codigo;
        this.lote = null;
        this.puntos = puntos;
        status = new Disponible();
    }

    //Metodos

    public void enUso() throws IOException {
        status = status.usar();
    }

    public void disponible() throws IOException {
        status = status.devolver();
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

    public LocalTime getTiempoEstimadoDeDevolucion() {
        return tiempoEstimadoDeDevolucion;
    }

    public LocalTime getTiempoEnElQueSeAlquilo() {
        return tiempoEnElQueSeAlquilo;
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

    public boolean getEstaEnZona() {
        return estaEnZona;
    }

    public int getPrecioFijo(){
        return precioFijo;
    }

    public void checkEstaEnZona(Terminal unaTerminal){
        if(unaTerminal.getZona().getNombre().equals(this.terminalDeOrigen.getZona().getNombre())) estaEnZona = true;
        else estaEnZona = false;
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

    public TipoDeActivo getTipoDeActivo() {
        return tipoDeActivo;
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