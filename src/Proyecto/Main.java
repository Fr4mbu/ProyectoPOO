package Proyecto;

//clase de prueba para ver si funciona la clase insulina
public class Main {
    public static void main(String[] args) {

        //crear insulina
        Insulina insulina = new Insulina("Insulina", 10, 10, "2025 - 09 - 24", 200, 8);

        //mostrar informacion y recomendacion
        System.out.println("Nombre: " + insulina.getNombre());
        System.out.println("Stock inicial: " + insulina.getCantidadDisponible());
        insulina.recomendarDosis();

        //tomar una dosis de insulina
        insulina.tomarDosis();
        System.out.println("Stock restante: " + insulina.getCantidadDisponible());

        //cambiar nivel de glucosa y volver a recomendar
        insulina.setNivelGlucosa(95);
        insulina.recomendarDosis();
    }
}

