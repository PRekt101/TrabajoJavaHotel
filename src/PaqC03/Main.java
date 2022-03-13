package PaqC03;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Reserva r = new Reserva();
        Reserva relim = new Reserva("dan","min",7543,776655,888,"2/5/20","6/2/21","Vegano");
        int opcion=0;
        boolean salida = true;
        do{
        System.out.println("Bienvenido al Hotel C03");
        System.out.println("1 mostrar plano habitaciones disponibles");
        System.out.println("2 Reservar habitacion");
        System.out.println("3 Anular reserva");
        opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                r.mapeadoDisponible();
                break;
            case 2:
                System.out.println("Rellena los datos a continuacion");
                r = Reserva.pedirDatos();
                sc.nextLine();
                System.out.println("Que tipo de habitacion quiere?");
                String tipo = sc.nextLine();
                int[] pos = r.reservarHab(r, tipo);
                System.out.println("Se ha reservado planta " + (pos[0] + 1) + " , habitacion " + (pos[1] + 1));
                break;
            case 3:
                r.anularReserva(relim);
                break;
            default:
                System.out.println("Opcion no valida");
                salida = false;
                break;
        }
        }while (salida);
    }
}
