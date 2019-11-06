import java.io.IOException;

public class Disponible extends Status {

    public Disponible(){
        codigo = 1;
    }

    public Disponible devolver()throws IOException {
        throw new IOException("Ya esta devuelto");
    }

    public EnUso usar(){
        return new EnUso();
    }
}
