import java.io.Serializable;

public class Terminal implements Serializable {
    Zona zona;
    public Terminal(Zona zona){
        this.zona = zona;
        zona.agregarTerminal(this);
    }
    public Zona getZona(){
        return this.zona;
    }
}