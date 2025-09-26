package Proyecto.Vista;

import Proyecto.Controlador.ControladorPaciente;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPaciente extends JFrame {
    private ControladorPaciente controlador;
    private JFrame frame;

    private JTextField txtUsuario;
    private JTextField txtEdad;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private JButton btnRegistrar;

    public LoginPaciente(ControladorPaciente controlador) {
        this.controlador = controlador;
        initComponentes();
    }

    private void initComponentes() {
        frame = new JFrame("Login Paciente");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblUsuario = new JLabel("Nombre: ");
        lblUsuario.setBounds(50, 30, 100, 25);
        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 30, 150, 25);

        JLabel lblEdad = new JLabel("Edad: ");
        lblEdad.setBounds(50, 70, 100, 25);
        txtEdad = new JTextField("0");
        txtEdad.setBounds(150, 70, 150, 25);

        JLabel lblContrasena = new JLabel("Contraseña: ");
        lblContrasena.setBounds(50, 110, 100, 25);
        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(150, 110, 150, 25);

        btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setBounds(50, 160, 130, 30);
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(180, 160, 130, 30);

        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblEdad);
        frame.add(txtEdad);
        frame.add(lblContrasena);
        frame.add(txtContrasena);
        frame.add(btnRegistrar);
        frame.add(btnIngresar);

        //acciones de los botones
        btnRegistrar.addActionListener(e -> registrarPaciente());
        btnIngresar.addActionListener(e -> loginPaciente());

        frame.setVisible(true);
    }

    private void registrarPaciente() {
        String nombre = txtUsuario.getText();
        int edad = Integer.parseInt(txtEdad.getText());
        String clave = new String(txtContrasena.getPassword());

        controlador.registrarPaciente(nombre, edad, clave);
        JOptionPane.showMessageDialog(frame, "Paciente registrado correctamente");
    }

    private void loginPaciente() {
        String nombre = txtUsuario.getText();
        String clave = new String(txtContrasena.getPassword());

        boolean exito = controlador.loginPaciente(nombre, clave);
        if (exito) {
            JOptionPane.showMessageDialog(frame, "Login exitoso");
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos");
        }
    }
}

