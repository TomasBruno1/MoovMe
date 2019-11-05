import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Cliente extends Usuario {
    Multa multa;
    HashMap<Zona, Integer> puntosPorZona;
    HashMap<Zona, Integer> puntosPorZonaFijo;
    Boolean isBlocked;
    ArrayList<Activo> activosUsados;
    Activo activoEnUso;
    int numeroDeTelefono;

    public Cliente(String nombre, int numeroDeTelefono, String contrasena){

        puntosPorZona = new HashMap<Zona, Integer>();
        puntosPorZonaFijo = new HashMap<Zona, Integer>();
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

   /* public HashMap<Zona, Integer> incluirZonas(Zona ZonaA, Zona ZonaB){
        HashMap<Zona,Integer> hash = new HashMap<Zona, Integer>();
        hash.put(ZonaA,0);
        hash.put(ZonaB,0);
        return hash;
    }*/

    public void usarActivo(Activo activo){
        puntosPorZona.replace(activo.terminalDeOrigen.getZona(), puntosPorZona.get(activo.terminalDeOrigen.getZona()) + activo.getPuntos());
        puntosPorZonaFijo.replace(activo.terminalDeOrigen.getZona(), puntosPorZonaFijo.get(activo.terminalDeOrigen.getZona()) + activo.getPuntos());
    }

    public Activo getActivoEnUso() throws IOException {
        if(activoEnUso == null) throw new IOException("No se encontro un activo asociado.");
        else return activoEnUso;
    }
    
    public void setActivoEnUso(Activo activoEnUso) {
        this.activoEnUso = activoEnUso;
    }

    public Multa getMulta() {
        return multa;
    }

    public void pagarMulta(){
      multa.estoyPagada = true;
      isBlocked = false;
    }

    public int obtenerPrecioPorUso(int minutos){
        return activoEnUso.getPrecioFijo() + activoEnUso.getPrecioDeTarifa()* minutos;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }
}
