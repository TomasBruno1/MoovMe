import java.io.IOException;

public class Disponible extends Status {

    public Disponible(){
        codigo = 1;
    }

    public Disponible Devolver()throws IOException {
        throw new IOException("Ya esta devuelto");
    }

    public EnUso Usar(){
        return new EnUso();
    }
}
