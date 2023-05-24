import java.io.*;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(new FileReader("afluencia.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter("estadistica.txt"))) {
            while(sc.hasNext()){
                String nombreMes = sc.next();
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
                    pw.println(String.format("%s: No hay datos", nombreMes));
                }else{
                    double r = (double)sumaTem/numTemp;
                    pw.println(String.format("%s: %.2f", nombreMes, r));
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
