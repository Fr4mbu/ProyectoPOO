package Proyecto.Vista;

import Proyecto.Controlador.ControladorPaciente;
import Proyecto.Modelo.Paciente;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class VentanaPaciente {
    private ControladorPaciente controlador;
    private JFrame frame;
    private JTextField txtUsuario;
    private JTextField txtEdad;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private JButton btnRegistrar;

    //lista de pacientes
    public static final List<Paciente> PACIENTES = new ArrayList<>();

    public VentanaPaciente(ControladorPaciente controlador) {
        this.controlador = controlador;
        initComponentes();
    }

    private void initComponentes() {
        frame = new JFrame("Registro de Paciente");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblUsuario = new JLabel("Paciente: ");
        lblUsuario.setBounds(50,30,150,25);
        txtUsuario = new JTextField();
        txtUsuario.setBounds(50,55,150,25);

        JLabel lblEdad = new JLabel("Edad: ");
        lblEdad.setBounds(50,80,150,25);
        txtEdad = new JTextField("0");
        txtEdad.setBounds(50,105,150,25);

        JLabel lblPassword = new JLabel("Contraseña: ");
        lblPassword.setBounds(50,130,150,25);
        txtContrasena= new JPasswordField();
        txtContrasena.setBounds(50,155,150,25);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(250,180,100,30);
        btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setBounds(100,180,120,30);

        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblEdad);
        frame.add(txtEdad);
        frame.add(lblPassword);
        frame.add(txtContrasena);
        frame.add(btnIngresar);
        frame.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrarPaciente());
        btnIngresar.addActionListener(e -> ingresarPaciente());

        frame.setVisible(true);
    }

    private void registrarPaciente() {
        String nombre = txtUsuario.getText();
        int edad = Integer.parseInt(txtEdad.getText());
        String contraseña = new String(txtContrasena.getPassword());

        Paciente p = new Paciente(nombre, edad, contraseña);
        PACIENTES.add(p);
        JOptionPane.showMessageDialog(frame, "Paciente registrado");
    }

    private void ingresarPaciente() {
        String nombre = txtUsuario.getText();
        String contraseña = new String(txtContrasena.getPassword());

        for (Paciente p : PACIENTES) {
            if (p.getNombre().equalsIgnoreCase(nombre) && p.getClave().equals(contraseña)) {
                JOptionPane.showMessageDialog(frame, "Registro exitoso");
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrecto");
    }
}
