import java.util.ArrayList;
import java.util.HashSet;

public class ZonaSur extends Zona {
    public ZonaSur() {
        valor = 20;
        nombre = "ZonaSur";
        terminales = new HashSet<>();
        terminales.add(new Terminal(this, "Terminal 1"));

    }

    @Override
    public int getValor() {
        return super.getValor();
    }
}
