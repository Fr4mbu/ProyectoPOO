package Proyecto;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class VentanaPaciente {
    public static final List<Paciente> PACIENTES = new ArrayList<>();
    // --- UI ---
    private final JFrame frame = new JFrame("Registro Paciente");
    private final JLabel lblUsuario = new JLabel("Paciente:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblEdad = new JLabel("Edad:");
    private final JTextField txtEdad = new JTextField("0");
    private final JLabel lblPassword = new JLabel("Contraseña:");
    private final JPasswordField txtPassword = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Registarse");

    public VentanaPaciente() {
                frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        lblUsuario.setBounds(50,30,150,25);
        txtUsuario.setBounds(50,55,150,25);
        lblEdad.setBounds(50,80,150,25);
        txtEdad.setBounds(50,105,150,25);
        lblPassword.setBounds(50,130,150,25);
        txtPassword.setBounds(50,155,150,25);
        btnIngresar.setBounds(250,180,100,30);
        btnRegistrar.setBounds(100,180,100,30);
        PACIENTES.add(new Paciente(txtUsuario.getText(),Integer.parseInt(txtEdad.getText()), Arrays.toString(txtPassword.getPassword())));
    }

    public void mostrarVentana() {
        frame.add(lblEdad);
        frame.add(txtEdad);
        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblPassword);
        frame.add(txtPassword);
        frame.add(btnIngresar);
        frame.setVisible(true);
        btnIngresar.addActionListener(e-> getDatos());
        btnRegistrar.addActionListener(e->getDatos());
        new VentanaPaciente();

    }
    public void getDatos() {
        String paciente = txtUsuario.getText();
        int edad = Integer.parseInt(txtEdad.getText());
        String clave = txtPassword.getText();
    }




}