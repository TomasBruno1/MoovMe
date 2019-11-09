import java.io.IOException;
import java.io.Serializable;

public abstract class Status implements Serializable {
    public static final long serialVersionUID = 7L;

    int codigo;

    public int getCodigoStatus() {
        return codigo;
    }

    public abstract EnUso usar() throws IOException;
    public abstract Disponible devolver() throws IOException;

}
