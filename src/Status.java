import java.io.IOException;

public abstract class Status {
    int codigo;



    public int getCodigoStatus() {
        return codigo;
    }

    public abstract EnUso Usar() throws IOException;
    public abstract Disponible Devolver() throws IOException;

}
