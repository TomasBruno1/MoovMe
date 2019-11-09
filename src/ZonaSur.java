import java.util.HashSet;

public class ZonaSur extends Zona {
    public static final long serialVersionUID = 17L;

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
