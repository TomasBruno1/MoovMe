public abstract class Activo {
    public Activo(TipoDeActivo nombre, Zona zonaDeOrigen, int precio, int tarifaMin) {
        this.nombre = nombre;
        this.zonaDeOrigen = zonaDeOrigen;
        this.zonaActualDeEncuentro = zonaDeOrigen;
        estaEnZona = true;
        this.tarifaMin = tarifaMin;
        precioFijo= precio;
        valorDeMulta= precio/2;
        codigoReal = this.codigo++;
    }

    int puntos;
    int tarifaMin;
    Zona zonaDeOrigen;
    Zona zonaActualDeEncuentro;
    int valorDeMulta;
    boolean estaEnZona;
    public static int codigo = 1;
    int codigoReal;
    int precioFijo;
    TipoDeActivo nombre;

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

    public int getCodigo(){
        return codigoReal;
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
