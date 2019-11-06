import java.io.IOException;
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

    public void usarActivo(Activo activo){
        //todo distintos puntos segun el tiempo de entrega
        puntosPorZona.replace(activo.terminalDeOrigen.getZona(), puntosPorZona.get(activo.terminalDeOrigen.getZona()) + activo.getPuntos());
        puntosPorZonaFijo.replace(activo.terminalDeOrigen.getZona(), puntosPorZonaFijo.get(activo.terminalDeOrigen.getZona()) + activo.getPuntos());
    }

    public Activo getActivoEnUso() throws IOException {
        if(activoEnUso == null) throw new IOException("No se encontro un activo asociado.");
        else return activoEnUso;
    }
    
    public void setActivoEnUso(Activo activoEnUso) {
        this.activoEnUso = activoEnUso;
        //todo AGREGAR QUE USA EL ACTIVO ESTADO CAMBIAR AIUDA.
    }

    public Multa getMulta() {
        return multa;
    }

    public void pagarMulta(){
      multa.pagarMulta();
      isBlocked = false;
    }

    public int obtenerPrecioPorUso(int minutos){
        //todo cambiar de resoponsabilidad al activo??
        return activoEnUso.getPrecioFijo() + activoEnUso.getPrecioDeTarifa()* minutos;
    }

    public boolean getBlocked() {
        return isBlocked;
    }
}
