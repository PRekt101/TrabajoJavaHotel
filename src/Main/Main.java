package Main;

import PaqC03.Cliente;
import PaqC03.Hotel;

public class Main {

    public static void main(String[] args) {

        Cliente cl1 = new Cliente("dan","min",7543,776655,888,"2/5/20","6/2/21","Vegano");
        Cliente cl2 = new Cliente("juan","des",4321,8885,777,"3/4/21","5/6/22","Carnivoro");


        Hotel h = new Hotel();
        System.out.println("Disponiblidad del hotel \n"+h);


        int operaciones = 0;
        operaciones = h.reservarHab(cl1,"Estandar",2);
        System.out.println("Reservas realizadas "+operaciones);
        operaciones = h.reservarHab(cl1,"Balcon",1);
        System.out.println("Reservas realizadas "+operaciones);
        operaciones = h.reservarHab(cl2,"Suite",3);
        System.out.println("Reservas realizadas "+operaciones);
        System.out.println("Disponiblidad del hotel \n"+h);


        h.anularReserva(cl1,1);
        System.out.println("Disponiblidad del hotel \n"+h);

    }
}
