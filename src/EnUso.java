import java.io.IOException;

public class EnUso extends Status {
public EnUso(){
    codigo = 0;
}

    public EnUso usar()throws IOException {
    throw new IOException("Ya esta en uso");
    }

    public Disponible devolver(){
    return new Disponible();
    }

}
