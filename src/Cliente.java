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

    public Activo getActivoEnUso(){
        return activoEnUso;
    }

    public void bloquearme(){
        isBlocked = true;
    }

    public void pagarMulta(){
        //------------------------  multa.estaPagada = null;
    }

    public int obtenerPrecioPorUso(int minutos){
        return activoEnUso.precioFijo + activoEnUso.tarifaMin* minutos;
    }
}