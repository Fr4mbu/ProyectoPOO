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

    private static void crearPaciente() {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        int edad = leerEntero("Ingrese su edad: ");

        paciente = new Paciente(nombre, edad);
        System.out.println("Paciente creado");
    }

    //metodos del menu
    private static void mostrarMenu() {
        System.out.println("\n----- Menu  -----");
        System.out.println("1. Agregar medicamento ");
        System.out.println("2. Agregar recordatorio ");
        System.out.println("3. Tomar medicamento ");
        System.out.println("4. Mostrar medicamentos ");
        System.out.println("5. Mostrar recordatorios ");
        System.out.println("6. Mostrar historial ");
        System.out.println("7. Saliendo del sistema ");
    }

    private static void agregarMedicamento() {
        System.out.print("Ingrese el nombre del medicamento: ");
        String nombre = scanner.nextLine();

        int dosis = leerEntero("Ingrese dosis (mg/unidades): ");
        int cantidad = leerEntero("Ingrese cantidad disponible: ");

        System.out.println("Ingrese la fecha de vencimiento del medicamento (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();

        Medicamento m = new Medicamento(nombre, dosis, cantidad, fecha);
        paciente.agregarMedicamento(m);
    }

    private static void agregarRecordatorio() {
        if (paciente.getListaMedicamentos().isEmpy()) {
            System.out.print("El paciente no tiene medicamentos. Agregue medicamentos ");
            return;
        }

        System.out.print("Seleccione un medicamento para asociar: ");
        mostrarMedicamentosEnumerados();

        int seleccion = leerEntero("Opcion: ");
        Medicamento med = paciente.getListaMedicamentos().get(seleccion - 1);

        System.out.print("Ingrese hora del recordatorio (HH:MM): ");
        String horaInput = scanner.nextLine();
        LocalTime hora = LocalTime.parse(horaInput);

        int frecuencia = leerEntero("Ingrese frecuencia en horas: ");

        Recordatorio r = new Recordatorio(hora, frecuencia, med);
        paciente.agregarRecordatorio(r);
    }

    private static void tomarMedicamento() {
        if (paciente.getListaMedicamentos().isEmpty()) {
            System.out.println("No hay medicamentos registrados ");
            return;
        }

        System.out.println("Seleccione un medicamento para tomar: ");
        mostrarMedicamentosEnumerados();

        int seleccion = leerEntero("Opcion: ");
        Medicamento med = paciente.getListaMedicamentos().get(seleccion - 1);

        paciente.tomarMedicamento(med);
    }

    private static void mostrarMedicamentosEnumerados() {
        for (int i = 0; i < paciente.getListaMedicamentos().size(); i++) {
            Medicamento m = paciente.getListaMedicamentos().get(i);
            System.out.println((i + 1) + ". " + m.getNombre() + " (Stock: " + m.getCantidadDisponible() + ")");
        }
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada invalida");
            scanner.next(); // descartar entrada incorrecta
            System.out.print(mensaje);
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return valor;
    }
}

