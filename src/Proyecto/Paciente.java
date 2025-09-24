package Proyecto;

import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private String nombre;
    private int edad;
    private List<Medicamento> listaMedicamentos;
    private List<Recordatorio> listaRecordatorios;
    private Historial historial;

    //constructor
    public Paciente(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.listaMedicamentos = new ArrayList<>();
        this.listaRecordatorios = new ArrayList<>();
        this.historial = new Historial();
    }

    //agregar medicamento
    public void agregarMedicamento(Medicamento m) {
        listaMedicamentos.add(m);
        System.out.println("Se agrego el medicamento: " + m.getNombre());
    }
}
