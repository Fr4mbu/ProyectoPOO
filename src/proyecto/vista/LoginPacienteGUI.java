package proyecto.vista;

import proyecto.controlador.ControladorPaciente;
import proyecto.controlador.ControladorMedicamentos;
import proyecto.modelo.Paciente;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class LoginPacienteGUI extends JFrame {

    private final ControladorPaciente controlador;
    private JTextField txtRut;
    private JPasswordField txtClave;
    private JButton btnLogin;

    public LoginPacienteGUI(ControladorPaciente controlador) {
        this.controlador = controlador;

        setTitle("Login Paciente");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblRut = new JLabel("RUT:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblRut, gbc);

        txtRut = new JTextField(15);
        gbc.gridx = 1;
        add(txtRut, gbc);

        JLabel lblClave = new JLabel("Clave:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblClave, gbc);

        txtClave = new JPasswordField(15);
        gbc.gridx = 1;
        add(txtClave, gbc);

        btnLogin = new JButton("Iniciar Sesion");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnLogin, gbc);

        btnLogin.addActionListener(e -> procesarLogin());
    }

    private void procesarLogin() {
        String rut = txtRut.getText().trim();
        String clave = new String(txtClave.getPassword()).trim();

        if (rut.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "RUT y clave son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String msgLogin = controlador.procesarLogin(rut, clave);
        Optional<Paciente> optPaciente = controlador.loginPaciente(rut, clave);

        if (optPaciente.isPresent()) {
            JOptionPane.showMessageDialog(this, msgLogin, "Exito", JOptionPane.INFORMATION_MESSAGE);
            ControladorMedicamentos ctrlMed = new ControladorMedicamentos(optPaciente.get());
            GestionMedicamentosGUI gestion = new GestionMedicamentosGUI(ctrlMed, optPaciente.get());
            gestion.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, msgLogin, "Error de Login", JOptionPane.ERROR_MESSAGE);
            txtClave.setText("");
        }
    }
}