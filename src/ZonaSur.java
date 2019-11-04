import java.util.ArrayList;
import java.util.HashSet;

public class ZonaSur extends Zona {
    public ZonaSur() {
        valor = 20;
        lotes = new ArrayList<>();
        nombre = "ZonaSur";
        terminales = new HashSet<>();
        terminales.add(new Terminal(this));

    }

    @Override
    public int getValor() {
        return super.getValor();
    }
}
