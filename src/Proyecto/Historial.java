package Proyecto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<String> registros; //lista de eventos de consumo

    //constructor
    public Historial() {
        this.registros = new ArrayList<>();
    }

    //metodo para agregar registro con fecha y hora automatica
    public void agregarRegistro(String registro) {
        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        registros.add("[" + fechaHora + "]" + registro);
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
