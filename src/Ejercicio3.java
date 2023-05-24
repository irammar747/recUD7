import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio3 {
    private final static int TAM_REG = Character.BYTES + Integer.BYTES + Double.BYTES;
    private final static int numMedidas = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = new File("medidas.dat");
        if (!f.exists()) {
            System.out.println("Creando el fichero...");
            try (RandomAccessFile raf = new RandomAccessFile("medidas.dat", "rw")) {
                //Inicialmente no habrá ningún peso
                for (int i = 0; i < numMedidas; i++) {
                    raf.writeChar('N');
                    raf.writeInt(-1);
                    raf.writeDouble(Double.NaN);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (RandomAccessFile raf = new RandomAccessFile("medidas.dat", "rw")) {
            boolean salir = false;
            while (!salir) {
                switch (mostrarMenu(sc)) {
                    case 1:
                        introducirPeso(raf, sc);
                        break;
                    case 2:
                        mostrarDatos(raf);
                        break;
                    case 3:
                        mostrarPesoMedioSexo(raf, sc);
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


    public static int mostrarMenu(Scanner sc) {
        int opcion;

        System.out.println("Menú");
        System.out.println("1. Introducir datos de una persona");
        System.out.println("2. Mostrar todos los datos");
        System.out.println("3. Mostrar peso medio según sexo");
        System.out.println("0. Salir");

        System.out.print("Opción: ");
        opcion = sc.nextInt();

        return opcion;

    }

    public static void introducirPeso(RandomAccessFile raf, Scanner sc) throws IOException {
        int num;
        String sexo;
        int edad;
        double peso;

        System.out.print("Número de persona: ");
        num = sc.nextInt();
        raf.seek(TAM_REG * (num-1));

        while(true){
            System.out.print("Sexo(M(mujer)-H(hombre)): ");
            sexo = sc.next();
            if(sexo.equals("M") || sexo.equals("H")) break;
        }
        while(true){
            System.out.print("Edad (Mayor o igual a 0): ");
            edad = sc.nextInt();
            if(edad >= 0) break;
        }
        while(true){
            System.out.print("Peso (Mayor o igual a 0): ");
            peso = sc.nextDouble();
            if(peso >= 0) break;
        }
        raf.writeChar(sexo.charAt(0));
        raf.writeInt(edad);
        raf.writeDouble(peso);

        System.out.println("Registro añadido");

    }

    public static void mostrarDatos(RandomAccessFile raf) throws IOException {

        for (int i = 0; i < numMedidas; i++) {
            raf.seek(TAM_REG * i);
            char sexo = raf.readChar();
            if (sexo == 'N') {
                System.out.printf("Persona %d: No hay datos registrados\n", i + 1);
            } else {
                System.out.printf("Persona %d: Sexo: %c, Edad: %d, Peso: %.2f\n", i+1, sexo, raf.readInt(), raf.readDouble());
            }

        }

    }

    public static void mostrarPesoMedioSexo(RandomAccessFile raf, Scanner sc) throws IOException {
        String sexo;
        double suma = 0;
        int n = 0;

        while(true){
            System.out.print("Sexo(M(mujer)-H(hombre)): ");
            sexo = sc.next();
            if(sexo.equals("M") || sexo.equals("H")) break;
        }

        for (int i = 0; i < numMedidas; i++) {
            raf.seek(TAM_REG * i);
            if(raf.readChar() == sexo.charAt(0)){
                raf.readInt();
                suma += raf.readDouble();
                n++;
            }
        }

        if(n == 0){
            System.out.printf("No hay mediciones de %s\n", sexo.equals("M")?"mujeres":"hombres");
        }else{
            System.out.printf("La media de peso para %s es %.2f\n", sexo.equals("M")?"las mujeres":"los hombres", (suma/n));
        }
    }
}
