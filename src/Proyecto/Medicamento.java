package Proyecto;


public class Medicamento {
    private String nombre;
    private int dosis; //para dosis en mg o unidades
    private int cantidadDisponible;
    private String fechaVencimiento;

    //constructor
    public Medicamento(String nombre, int dosis, int cantidad, String fecha) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.cantidadDisponible = cantidad;
        this.fechaVencimiento = fecha;
    }

}
