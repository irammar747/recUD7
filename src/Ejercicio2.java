import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio2 {
    private final static int TAM_REG = (50*2+2) + 1 + Integer.BYTES + Double.BYTES;
    private final static int TAM_CABECERA = Integer.BYTES;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                boolean salir = false;
                while(!salir){
                    switch (mostrarMenu(sc)){
                        case 1:
                            introducirPeso(raf, sc);
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 0:
                            salir = true;
                            break;
                        default:
                            System.out.println("Opción incorrecta");
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static int mostrarMenu(Scanner sc){
        int opcion;

        System.out.println("Menú");
        System.out.println("1. Introducir peso");
        System.out.println("2. Mostrar peso");
        System.out.println("3. Generar Informe");
        System.out.println("4. Salir");

        opcion = sc.nextInt();

        return opcion;

    }
    public static void introducirPeso(RandomAccessFile raf, Scanner sc){

    }
}
