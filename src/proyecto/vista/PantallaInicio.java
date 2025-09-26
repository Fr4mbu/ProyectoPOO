package proyecto.vista;

import proyecto.controlador.ControladorPaciente;

import javax.swing.*;
import java.awt.*;

public class PantallaInicio extends JFrame {

    private final ControladorPaciente controlador;

    public PantallaInicio() {
        this.controlador = new ControladorPaciente();

        setTitle("Sistema de Gestion de Medicamentos");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Bienvenido al Sistema de Gestion de Medicamentos");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        JButton btnRegistrar = new JButton("Registrar Paciente");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(btnRegistrar, gbc);

        JButton btnLogin = new JButton("Iniciar Sesion");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(btnLogin, gbc);

        add(panel);

        btnRegistrar.addActionListener(e -> {
            RegistroPacienteGUI registro = new RegistroPacienteGUI(controlador);
            registro.setVisible(true);
            dispose();
        });

        btnLogin.addActionListener(e -> {
            LoginPacienteGUI login = new LoginPacienteGUI(controlador);
            login.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            PantallaInicio inicio = new PantallaInicio();
            inicio.setVisible(true);
        });
    }
}
