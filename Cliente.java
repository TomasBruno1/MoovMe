import java.util.*;

public class Cliente {

    public Cliente(String apodo){

    puntosMios= 0;
    activosUsados= null;
    activoEnUso = null;
    multa = null;
    isBlocked = false;
    this.apodo = apodo;

    }

    Multa multa;
    int puntosMios;
    Boolean isBlocked;
    ArrayList<Activo> activosUsados;
    Activo activoEnUso;
    String apodo;

    //--------Métodos

    public void registrarse(){

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
