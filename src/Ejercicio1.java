import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(new FileReader("entrada.txt"))) {

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
