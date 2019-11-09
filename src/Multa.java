import java.io.IOException;
import java.io.Serializable;

public class Multa implements Serializable {
    public static final long serialVersionUID = 4L;

    Activo activoAsociado;
    int valor;
    boolean estoyPagada;

    public Multa(Cliente c) {
        estoyPagada = false;
        try {
            activoAsociado = c.getActivoEnUso();
            valor = this.setValorDeMulta();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int setValorDeMulta(){
        return this.activoAsociado.getValorDeMulta();
    }

    public void pagarMulta(){
        estoyPagada =true;
    }

    public int getValorDeMulta(){
        return valor;
    }
}