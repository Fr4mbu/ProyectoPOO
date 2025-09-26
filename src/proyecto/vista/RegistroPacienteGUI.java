package proyecto.vista;

import proyecto.controlador.ControladorPaciente;

import javax.swing.*;
import java.awt.*;

public class RegistroPacienteGUI extends JFrame {

    private JTextField txtRut;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JPasswordField txtClave;
    private JButton btnRegistrar;
    private final ControladorPaciente controlador;

    public RegistroPacienteGUI(ControladorPaciente controlador) {
        this.controlador = controlador;

        setTitle("Registro de Paciente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblRut = new JLabel("RUT:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblRut, gbc);

        txtRut = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtRut, gbc);

        JLabel lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblNombre, gbc);

        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtNombre, gbc);

        JLabel lblEdad = new JLabel("Edad:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblEdad, gbc);

        txtEdad = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtEdad, gbc);

        JLabel lblClave = new JLabel("Clave:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblClave, gbc);

        txtClave = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtClave, gbc);

        btnRegistrar = new JButton("Registrar");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(btnRegistrar, gbc);

        add(panel);

        btnRegistrar.addActionListener(e -> registrarPaciente());
    }

    private void registrarPaciente() {
        String rut = txtRut.getText().trim();
        String nombre = txtNombre.getText().trim();
        String clave = new String(txtClave.getPassword()).trim();
        String edadStr = txtEdad.getText().trim();

        if (rut.isEmpty() || nombre.isEmpty() || clave.isEmpty() || edadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Edad debe ser un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String msg = controlador.registrarPaciente(rut, nombre, edad, clave);

        if (msg.startsWith("EXITO")) {
            JOptionPane.showMessageDialog(this, msg, "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            dispose();
            LoginPacienteGUI login = new LoginPacienteGUI(controlador);
            login.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtRut.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtClave.setText("");
    }
}