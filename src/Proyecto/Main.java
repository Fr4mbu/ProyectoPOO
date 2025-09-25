package Proyecto;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Paciente paciente;

    public static void main(String[] args) {
        System.out.println("----- Sistema de Gestion de Medicamentos -----");

        crearPaciente(); //se crea al paciente
        ejecutarMenu();
    }

    //control principal del menu
    private static void ejecutarMenu() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1: agregarMedicamento();
                case 2: agregarRecordatorio();
                case 3: tomarMedicamento();
                case 4: paciente.mostrarMedicamentos();
                case 5: paciente.mostrarRecordatorios();
                case 6: paciente.mostrarHistorial();
                case 7: System.out.println("Saliendo del sistema...");
                default: System.out.println("Opcion invalida");
            }
        } while (opcion !=7);
    }
}

