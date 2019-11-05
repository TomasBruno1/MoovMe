import java.io.IOException;

public class EnUso extends Status {
public EnUso(){
    codigo = 0;
}

    public EnUso Usar()throws IOException {
    throw new IOException("Ya esta en uso");
    }

    public Disponible Devolver(){
    return new Disponible();
    }

}
