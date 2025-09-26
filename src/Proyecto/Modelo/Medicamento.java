package Proyecto.Modelo;

public class Medicamento {
    private String nombre;
    private int dosis; //para dosis en mg o unidades
    private int cantidadDisponible;
    private String fechaVencimiento;

    //constructor
    public Medicamento(String nombre, int dosis, int cantidad, String fechaVencimiento) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.cantidadDisponible = cantidad;
        this.fechaVencimiento = fechaVencimiento;
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

    //getter y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return nombre + " | Dosis: " + dosis + " | Stock: " + cantidadDisponible + " | Vence: " + fechaVencimiento;
    }
}