package Proyecto.Vista;

import Proyecto.Controlador.ControladorPaciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPaciente extends JFrame {
    private ControladorPaciente controlador;

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnLogin;

    public LoginPaciente(ControladorPaciente controlador) {
        this.controlador = controlador;

        setTitle("Login Paciente");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponentes();
        setVisible(true);
    }

    private void initComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Usuario: "));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        panel.add(new JLabel("Contraseña: "));
        txtClave = new JPasswordField();
        panel.add(txtClave);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }

        });
        panel.add(new JLabel()); //espacio vacio
        panel.add(btnLogin);

        add(panel);
    }

    private void realizarLogin() {
        String usuario = txtUsuario.getText();
        String clave = new String(txtClave.getPassword());

        if(controlador.login(usuario, clave)) {
            mostrarMensaje("Login exitoso");
            VentanaPaciente ventana = new VentanaPaciente(controlador);
            controlador.setVentanaPaciente(ventana);
            this.dispose();
        } else {
            mostrarMensaje("Usuario o contraseña incorrectos");
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}

