import java.io.*;
import java.util.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        File f = new File("datos2.dat");
        Map<String, List<Integer>> registros;
        Scanner sc = new Scanner(System.in);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            System.out.println("Cargando datos ...");
            registros = (Map<String, List<Integer>>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Comenzando nueva semana ...");
            registros = new HashMap<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        mostrarTemperaturas(registros);

        String dia;
        List<Integer> temperaturas= new ArrayList<>();

        System.out.println("Dia de la semana: ");
        dia = sc.next();

        System.out.println("Temperatura a las 8 horas: ");
        temperaturas.add(sc.nextInt());
        System.out.println("Temperatura a las 14 horas: ");
        temperaturas.add(sc.nextInt());
        System.out.println("Temperatura a las 22 horas: ");
        temperaturas.add(sc.nextInt());

        registros.put(dia,temperaturas);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("datos2.dat"))){
            System.out.println("Guardando datos ...");
            oos.writeObject(registros);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void mostrarTemperaturas(Map<String,List<Integer>> temperaturas){
        if(temperaturas.isEmpty()){
            System.out.println("No hay temperaturas registradas");
        }else{
            System.out.println("Temperaturas registradas: ");
            for(String d : temperaturas.keySet()){
                System.out.printf("%s: [", d);
                for(int t : temperaturas.get(d)){
                    System.out.printf(" %d ", t);
                }
                System.out.printf("]\n");
            }
        }

    }
}

