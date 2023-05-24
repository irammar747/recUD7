import java.io.*;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(new FileReader("entrada.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter("salida.txt"))) {
            while(sc.hasNext()){
                String nombreDia = sc.next();
                int sumaTem = 0;
                int temp;
                int numTemp = 0;
                while(true){
                    temp = sc.nextInt();
                    if(temp == -1) break;
                    sumaTem += temp;
                    numTemp++;
                }
                if(numTemp == 0){
                    pw.println(String.format("%s: No hay datos", nombreDia));
                }else{
                    double r = (double)sumaTem/numTemp;
                    pw.println(String.format("%s: %.2f", nombreDia, r));
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
