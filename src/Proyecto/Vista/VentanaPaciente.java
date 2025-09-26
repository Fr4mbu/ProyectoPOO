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
        frame.setLayout(null);

        JLabel lblMed = new JLabel("Medicamento: ");
        lblMed.setBounds(20, 20, 100, 25);
        txtMedicamento = new JTextField();
        txtMedicamento.setBounds(120, 20, 150, 25);

        JLabel lblDosis = new JLabel("Dosis: ");
        lblDosis.setBounds(20, 50, 100, 25);
        txtDosis = new JTextField();
        txtDosis.setBounds(120, 50, 150, 25);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(300, 20, 100, 25);
        btnTomar = new JButton("Tomar");
        btnTomar.setBounds(300, 50, 100, 25);

        txtHistorial = new JTextArea();
        txtHistorial.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtHistorial);
        scroll.setBounds(20, 100, 440, 250);

        frame.add(lblMed);
        frame.add(txtMedicamento);
        frame.add(lblDosis);
        frame.add(txtDosis);
        frame.add(btnAgregar);
        frame.add(btnTomar);
        frame.add(scroll);

        //acciones botones
        btnAgregar.addActionListener(e -> agregarMedicamento());
        btnTomar.addActionListener(e -> tomarMedicamento());

        frame.setVisible(true);
        actualizarHistorial();
    }

    private void agregarMedicamento() {
        String nombre = txtMedicamento.getText();
        int dosis = Integer.parseInt(txtDosis.getText());
        Medicamento m = new Medicamento(nombre, dosis, dosis, "N/A");
        controlador.registrarMedicamento(m);
        actualizarHistorial();
    }

    private void tomarMedicamento() {
        String nombre = txtMedicamento.getText();
        controlador.tomarMedicamento(nombre);
        actualizarHistorial();
    }

    private void actualizarHistorial() {
        List<String> historial = controlador.obtenerHistorial();
        txtHistorial.setText(String.join("\n", historial));
    }
}

