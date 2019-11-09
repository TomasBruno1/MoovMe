import java.util.HashSet;

public class ZonaNorte extends Zona{
    public static final long serialVersionUID = 16L;

    public ZonaNorte() {
        valor = 50;
        nombre = "ZonaNorte";
        terminales = new HashSet<>();
        terminales.add(new Terminal(this, "Terminal 1"));

    }

    @Override
    public int getValor() {
        return super.getValor();
    }
}
