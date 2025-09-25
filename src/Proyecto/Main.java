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
}

