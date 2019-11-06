import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;

public abstract class Zona implements Serializable {
String nombre;
int valor;
HashSet<Terminal> terminales;

    public String getNombre() {
        return nombre;
    }

    public int getValor(){
    return valor;
}

    public void agregarTerminal(Terminal terminal){
        terminales.add(terminal);
    }

    public void eliminarTerminal(Terminal terminal){
        terminales.remove(terminal);
    }

    public HashSet<Terminal> getTerminales() {
        return terminales;
    }

    public Terminal getTerminal (String nombre) throws IOException {
        for (Terminal terminal: terminales) {
            if (terminal.getNombre().equals(nombre))return terminal;
        }throw new IOException("Terminal no encontrada");
    }
}
