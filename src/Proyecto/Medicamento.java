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

    //metodo para tomar dosis
    public void tomarDosis() {
        if (cantidadDisponible > 0) {
            cantidadDisponible--;
            System.out.println("Cantidad de dosis: " + cantidadDisponible);
        } else {
            System.out.println("No queda stock de " + nombre);
        }
    }

    //metodos getter
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public String getNombre() {
        return nombre;
    }



}
