import java.io.IOException;

public class Multa {

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

    public int getValorDeMulta(){
        return valor;
    }
}