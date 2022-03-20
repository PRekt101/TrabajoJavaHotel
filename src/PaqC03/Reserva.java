package PaqC03;

import java.util.Scanner;

public class Reserva {
    private String nombre;
    private String apellidos;
    private int DNI;
    private int numTelef;
    private int numTarjet;
    private String fechaEntrada;
    private String fechaSalida;
    private String regAlimenticio;
    private final Reserva[][] hotel = new Reserva[8][6];

    public Reserva() {
    }

    public Reserva(String nombre, String apellidos, int DNI, int numTelef, int numTarjet, String fechaEntrada, String fechaSalida, String regAlimenticio) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
        this.numTelef = numTelef;
        this.numTarjet = numTarjet;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.regAlimenticio = regAlimenticio;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getNumTelef() {
        return numTelef;
    }

    public void setNumTelef(int numTelef) {
        this.numTelef = numTelef;
    }

    public int getNumTarjet() {
        return numTarjet;
    }

    public void setNumTarjet(int numTarjet) {
        this.numTarjet = numTarjet;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getRegAlimenticio() {
        return regAlimenticio;
    }

    public void setRegAlimenticio(String regAlimenticio) {
        this.regAlimenticio = regAlimenticio;
    }

    public static Reserva pedirDatos(){
        Scanner sc = new Scanner(System.in);
        Reserva r = new Reserva();
        System.out.print("Nombre: ");
        r.setNombre(sc.nextLine());
        System.out.print("Apellido: ");
        r.setApellidos(sc.nextLine());
        System.out.print("DNI: ");
        r.setDNI(sc.nextInt());
        System.out.print("Telefono: ");
        r.setNumTelef(sc.nextInt());
        System.out.print("Numero de Tarjeta: ");
        r.setNumTarjet(sc.nextInt());
        System.out.print("Fecha de Entrada: ");
        r.setFechaEntrada(sc.nextLine());
        sc.nextLine();
        System.out.print("Fecha de Salida: ");
        r.setFechaSalida(sc.nextLine());
        System.out.print("Regimen Alimenticio: ");
        r.setRegAlimenticio(sc.nextLine());
        return r;
    }

    public void mapeadoDisponible(){
        for(int i=0;i<8;i++){
            for(int j=0;j<6;j++){
                if(hotel[i][j]==null){
                    System.out.print(" L ");
                }
                else{
                    System.out.print(" R ");
                }
            }
            System.out.println("");
        }
    }

    public int[] reservarHab(Reserva rHab, String opcion ) { //num de habitaciones
        int[] habitacion = encontrarHabLibre(opcion);
        hotel[habitacion[0]][habitacion[1]] = rHab;
        return habitacion;
    }

        private int[] encontrarHabLibre(String opc) {
        int fila = 0;
        int columna = 0;
        switch (opc) {
            case "Estandar":
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (hotel[i][j] == null) {
                            fila = i;
                            columna = j;
                            return new int[]{fila, columna};
                        }
                    }
                }
                break;
            case "Balcon":
                for (int i = 5; i < 7; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (hotel[i][j] == null) {
                            fila = i;
                            columna = j;
                            return new int[]{fila, columna};
                        }
                    }
                }
                break;
            case "Suite":
                fila=7;
                for (int j = 0; j < 6; j++) {
                    if (hotel[fila][j] == null) {
                        columna = j;
                        return new int[]{fila, columna};
                    }
                }
                break;
            }
        return new int[]{fila, columna};
    }

    public void anularReserva(Reserva r){
        for(int i = 0;i < 8; i++){
            for(int j = 0;j < 6;j++){
                if(hotel[i][j] == this){
                    hotel[i][j] = null;
                    return;
                }
            }
        }
        System.out.println("Datos de habitacion erroneos 404");
    }
}
