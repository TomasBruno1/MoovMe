import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

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

    public void agregarTerminal(String nombreTerminal, Zona suZona) throws IOException {
        for (Terminal terminal : terminales) {
            if (terminal.getNombre().equals(nombreTerminal)) throw new IOException("La terminal ya existe");
        }
        terminales.add(new Terminal(suZona, nombreTerminal));
    }

    public void eliminarTerminal(String nombreTerminal) throws IOException {
        for (Terminal terminal : terminales) {
            if (terminal.getNombre().equals(nombreTerminal)) {
                terminales.remove(terminal);
                return;
            }
        }throw new IOException("No se pudo encontrar la Terminal");

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
