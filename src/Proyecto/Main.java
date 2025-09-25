package Proyecto;

//clase de prueba para ver si funciona la clase medicamento
public class Main {
    public static void main(String[] args) {

        //crear medicamento
        Medicamento paracetamol = new Medicamento("Paracetamol", 500, 5, "2025-09-24");

        //mostrar informacion inicial
        System.out.println("Medicamento: " + paracetamol.getNombre());
        System.out.println("Stock inicial: " + paracetamol.getCantidadDisponible());

        //tomar dosis varias veces
        paracetamol.tomarDosis(); //primera dosis
        paracetamol.tomarDosis(); //sugunda
        paracetamol.tomarDosis(); //tercera

        //mostrar stock
        System.out.println("Stock final: " + paracetamol.getCantidadDisponible());
    }
}

