import java.io.IOException;
import java.util.ArrayList;

public class Cliente extends Usuario {
    Multa multa;
    int puntosMios;
    int puntosTotales;
    Boolean isBlocked;
    ArrayList<Activo> activosUsados;
    Activo activoEnUso;
    int numeroDeTelefono;

    public Cliente(String nombre, int numeroDeTelefono, String contrasena){

        puntosMios= 0;
        puntosTotales = 0;
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

    public void usarActivo(Activo activo){
        puntosMios++;
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
