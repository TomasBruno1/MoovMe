public abstract class Activo {
    public Activo(Zona zonaDeOrigen) {
        this.zonaDeOrigen = zonaDeOrigen;
        this.zonaActualDeEncuentro = zonaDeOrigen;
        estaEnZona = true;
        tarifaMin = 0;
    }

    int puntos;
    int tarifaMin;
    Zona zonaDeOrigen;
    Zona zonaActualDeEncuentro;
    int valorDeMulta;
    boolean estaEnZona;
    int ponderacion;
    int precioFijo;


    //Metodos

    public int getValorDeMulta(){
        return valorDeMulta;
    }

    public boolean estaEnZona(){
        if(!this.zonaActualDeEncuentro.equals(this.zonaDeOrigen)){
            estaEnZona = false;
            return false;
        }return true;
    }

    public void setPrecioDeTarifa(){
        tarifaMin = zonaDeOrigen.getValor()*ponderacion;
    }

    public int getPrecioDeTarifa(){
        return this.tarifaMin;
    }

    public int getPuntos(){
        return this.puntos;
    }

    public boolean getSiEstaEnZona(){
        return estaEnZona;
    }


}
