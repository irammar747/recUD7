import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio2 {
    private final static int TAM_REG = (50*2+2) + 1 + Integer.BYTES + Double.BYTES;
    private final static int TAM_CABECERA = Integer.BYTES;

    public static void main(String[] args) {
        File f = new File("datos.dat");
        if(!f.exists()){
            System.out.println("Creando el fichero...");
            try(RandomAccessFile raf = new RandomAccessFile("entrada.dat", "rw")){
                //Inicialmente no habrá ningún peso
                raf.writeInt(0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            try(RandomAccessFile raf = new RandomAccessFile("entrada.dat", "rw")){
                int opcion;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
