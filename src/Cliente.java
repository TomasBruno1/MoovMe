import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.*;

public class Cliente extends Usuario {
    Multa multa;
    HashMap<Zona, Integer> puntosPorZona;
    HashMap<Zona, Integer> puntosPorZonaFijo;
    boolean isBlocked;
    ArrayList<Activo> activosUsados;
    Activo activoEnUso;
    int numeroDeTelefono;

    public Cliente(String nombre, int numeroDeTelefono, String contrasena){

        puntosPorZona = new HashMap<>();
        puntosPorZonaFijo = new HashMap<>();
        activosUsados= new ArrayList<>();
        activoEnUso = null;
        multa = null;
        isBlocked = false;
        this.nombreDeUsuario = nombre;
        this.numeroDeTelefono = numeroDeTelefono;
        this.contrasena = contrasena;
    }


    public int getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    public void agregarZonas(ArrayList<Zona> zonas){
       for (Zona zona : zonas) {
           puntosPorZonaFijo.put(zona, 0);
           puntosPorZona.put(zona, 0);
       }
    }

    public void bloquearCliente() throws IOException {
        multa = new Multa(this);
        activoEnUso.devolverActivoATerminal(activoEnUso.getTerminalDeOrigen(), LocalTime.now());
    }

    public void devolverActivo(boolean devolvioActivoATiempo){
        if (devolvioActivoATiempo){
            puntosPorZona.replace(activoEnUso.terminalDeOrigen.getZona(), (int) (puntosPorZona.get(activoEnUso.terminalDeOrigen.getZona()) + activoEnUso.getPuntos()*1.2));
            puntosPorZonaFijo.replace(activoEnUso.terminalDeOrigen.getZona(), (int) (puntosPorZonaFijo.get(activoEnUso.terminalDeOrigen.getZona()) + activoEnUso.getPuntos()*1.2));
        }else{
            puntosPorZona.replace(activoEnUso.terminalDeOrigen.getZona(),(puntosPorZona.get(activoEnUso.terminalDeOrigen.getZona()) + activoEnUso.getPuntos()));
            puntosPorZonaFijo.replace(activoEnUso.terminalDeOrigen.getZona(),(puntosPorZonaFijo.get(activoEnUso.terminalDeOrigen.getZona()) + activoEnUso.getPuntos()));
        }
        activoEnUso = null;
    }

    public Activo getActivoEnUso() throws IOException {
        if(activoEnUso == null) throw new IOException("No se encontro un activo asociado.");
        else return activoEnUso;
    }
    
    public void setActivoEnUso(Activo activoEnUso, LocalTime horaEnQueSeAlquilo,LocalTime horaEstimadaDeDevolucion) throws IOException {
        this.activoEnUso = activoEnUso;
        this.activoEnUso.retirarActivoDeTerminal(horaEnQueSeAlquilo, horaEstimadaDeDevolucion);
    }

    public Multa getMulta() {
        return multa;
    }

    public void pagarMulta(){
      multa.pagarMulta();
      isBlocked = false;
    }

    public boolean tieneActivoEnUso (){
        if (activoEnUso != null) return true;
        else return false;
    }

    public int obtenerPrecioPorUso(int minutos){
        return activoEnUso.getPrecioFijo() + activoEnUso.getPrecioDeTarifa()* minutos;
    }

    public boolean getBlocked() {
        return isBlocked;
    }


    @Override
    public boolean isAdmin() {
        return false;
    }
}
