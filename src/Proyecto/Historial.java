package Proyecto;

import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<String> registros; //lista de eventos de consumo

    //constructor
    public Historial() {
        this.registros = new ArrayList<>();
    }
    
    public void mostrarHistorial() {
        if(registros.isEmpty()) {
            System.out.println("El historial esta vacio");
        } else {
            System.out.println("----- HISTORIAL -----");
            for(String r : registros) {
                System.out.println(r);
            }
        }
    }

    //getter
    public List<String> getRegistros() {
        return registros;
    }
}
