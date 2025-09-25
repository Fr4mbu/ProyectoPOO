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

    //agregar recordatorio
    public void agregarRecordatorio(Recordatorio r) {
        listaRecordatorios.add(r);
        System.out.println("Se agrego un recordatorio para: " + r.getMedicamentoAsociado().getNombre());
    }

    //tomar un medicamento, actualizacion de stock + historial
    public void tomarMedicamento(String nombreMedicamento) {
        for (Medicamento m : listaMedicamentos) {
            if (m.getNombre().equalsIgnoreCase(nombreMedicamento)) {
                m.tomarDosis();
                historial.agregarRegistro("Paciente " + nombre + " tomo " + m.getNombre());
                return;
            }
        }
        System.out.println("Medicamento " + nombreMedicamento + " no esta en la lista de medicamentos");
    }

    //mostrar medicamentos del paciente
    public void mostrarMedicamentos() {
        System.out.println("Medicamentos de " + nombre + ":");
        for (Medicamento m : listaMedicamentos) {
            System.out.println("- " + m.getNombre() + "(stock: " + m.getCantidadDisponible() + ")");
        }
    }

    //mostrar recordatorios para los pacientes
    public void mostrarRecordatorios() {
        System.out.println("Recordatorios de " + nombre + ":");
        for (Recordatorio r : listaRecordatorios) {
            r.mostrarRecordatorio();
        }
    }

    //mostrar historial al paciente
    public void mostrarHistorial() {
        System.out.println("Historial de " + nombre + ":");
        historial.mostrarHistorial();
    }

    public List<Medicamento> getListaMedicamentos() {
        return listaMedicamentos;
    }
}
