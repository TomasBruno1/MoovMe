public class Multa {

    public Multa(Cliente c) {
        estoyPagada = false;
        activoAsociado = c.getActivoEnUso();
        valor = this.setValorDeMulta();
    }

    Activo activoAsociado;
    int valor;
    boolean estoyPagada;

    public int setValorDeMulta(){
        return this.activoAsociado.getValorDeMulta();

    }

    public int getValorDeMulta(){
        return valor;
    }
}
