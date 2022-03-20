package PaqC03;

import java.io.Serializable;

public class Hotel implements Serializable {

    private final Cliente [][] hotel;

    public Hotel() {
        hotel = new Cliente[8][6];
    }

    public int reservarHab(Cliente cl, String opc, int cantHab) {
        int fila = 0;
        int columna = 0;
        int reservas=0;
        switch (opc) {
            case "Estandar":
                for (int n = 1; n <= cantHab; n++) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 6; j++) {
                            if (hotel[i][j] == null) {
                                if (cantHab>0){
                                    hotel[i][j] = cl;
                                    cantHab--;
                                    reservas++;
                                }
                            }
                        }
                    }
                }
                return reservas;
            case "Balcon":
                for (int n = 0; n < cantHab; n++) {
                    for (int i = 5; i < 7; i++) {
                        for (int j = 0; j < 6; j++) {
                            if (hotel[i][j] == null) {
                                if (cantHab>0){
                                    hotel[i][j] = cl;
                                    cantHab--;
                                    reservas++;
                                }
                            }
                        }
                    }
                }
                return reservas;
            case "Suite":
                fila=7;
                for (int n = 0; n < cantHab; n++) {
                    for (int j = 0; j < 6; j++) {
                        if (hotel[fila][j] == null) {
                            if (cantHab>0){
                                hotel[fila][j] = cl;
                                cantHab--;
                                reservas++;
                            }
                        }
                    }
                }
                return reservas;
            }
        return reservas;
    }

    public boolean anularReserva(Cliente cl, int cantHab) {
        for (int n = 0; n < cantHab; n++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 6; j++) {
                    if (hotel[i][j] != null) {
                        if (hotel[i][j].equals(cl)) {
                            hotel[i][j] = null;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String toString(){
        String disponible="";
        for(int i=0;i<8;i++){
            for(int j=0;j<6;j++){
                if(hotel[i][j]==null){
                    disponible = disponible + " [L] ";
                }
                else{
                    disponible = disponible + " [R] ";
                }
            }
            disponible = disponible + "\n";
        }
        return disponible;
    }
}

