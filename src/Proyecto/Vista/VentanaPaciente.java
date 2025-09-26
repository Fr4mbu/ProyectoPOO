package Proyecto.Vista;

import Proyecto.Controlador.ControladorPaciente;
import Proyecto.Modelo.Medicamento;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaPaciente {
    private ControladorPaciente controlador;
    private JFrame frame;

    //componentes GUI
    private JTextField txtMedicamento;
    private JTextField txtDosis;
    private JButton btnAgregar;
    private JButton btnTomar;
    private JTextArea txtHistorial;

    public VentanaPaciente(ControladorPaciente controlador) {
        this.controlador = controlador;
        initComponentes();
    }

    private void initComponentes() {
        frame = new JFrame("Panel Paciente");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));

        //panel superior para ingresar medicamento
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblMed = new JLabel("Medicamento: ");
        gbc.gridx = 0; gbc.gridy = 0;
        panelEntrada.add(lblMed, gbc);

        txtMedicamento = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0;
        panelEntrada.add(txtMedicamento, gbc);

        JLabel lblDosis = new JLabel("Dosis: ");
        gbc.gridx = 0; gbc.gridy = 1;
        panelEntrada.add(lblDosis, gbc);

        txtDosis = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        panelEntrada.add(txtDosis, gbc);

        btnAgregar = new JButton("Agregar");
        gbc.gridx = 0; gbc.gridy = 2;
        panelEntrada.add(btnAgregar, gbc);

        btnTomar = new JButton("Tomar");
        gbc.gridx = 1; gbc.gridy = 2;
        panelEntrada.add(btnTomar, gbc);

        frame.add(panelEntrada, BorderLayout.NORTH);

        //panel central para historial
        txtHistorial = new JTextArea();
        txtHistorial.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtHistorial);
        frame.add(scroll, BorderLayout.CENTER);

        //acciones botones
        btnAgregar.addActionListener(e -> agregarMedicamento());
        btnTomar.addActionListener(e -> tomarMedicamento());

        frame.setVisible(true);
        actualizarHistorial();
    }

    private void agregarMedicamento() {
        String nombre = txtMedicamento.getText().trim();
        if(nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese el nombre del medicamento");
            return;
        }

        int dosis;
        try {
            dosis = Integer.parseInt(txtDosis.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Ingrese un numero valido para la dosis");
            return;
        }

        Medicamento m = new Medicamento(nombre, dosis, dosis, "N/A");
        controlador.registrarMedicamento(m);
        txtMedicamento.setText("");
        txtDosis.setText("");
        actualizarHistorial();
    }

    private void tomarMedicamento() {
        String nombre = txtMedicamento.getText().trim();
        if(nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese el nombre del medicamento para tomar");
            return;
        }

        controlador.tomarMedicamento(nombre);
        txtMedicamento.setText("");
        txtDosis.setText("");
        actualizarHistorial();
    }

    private void actualizarHistorial() {
        List<String> historial = controlador.obtenerHistorial();
        txtHistorial.setText(String.join("\n", historial));
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje);
    }
}


