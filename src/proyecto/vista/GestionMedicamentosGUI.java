package proyecto.vista;

import proyecto.controlador.ControladorMedicamentos;
import proyecto.modelo.Medicamento;
import proyecto.modelo.Insulina;
import proyecto.modelo.Paciente;
import proyecto.modelo.Recordatorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GestionMedicamentosGUI extends JFrame {

    private final Paciente paciente;
    private final ControladorMedicamentos controladorMed;
    private JComboBox<String> comboMedicamentos;
    private JTextArea medicamentosArea;
    private JTextArea historialArea;
    private JTextArea recordatoriosArea;
    private RecordatorioTimer recordatorioTimer;

    public GestionMedicamentosGUI(ControladorMedicamentos controladorMed, Paciente paciente) {
        this.controladorMed = controladorMed;
        this.paciente = paciente;
        setTitle("Gestion de Medicamentos - " + paciente.getNombre());
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        actualizarMedicamentosArea();
        actualizarHistorialArea();
        actualizarRecordatoriosArea();

        recordatorioTimer = new RecordatorioTimer(paciente, this);
        recordatorioTimer.start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (recordatorioTimer != null) {
                    recordatorioTimer.stop();
                }
                super.windowClosing(e);
            }
        });

        cargarComboMedicamentos();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JSplitPane splitNorte = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitNorte.setResizeWeight(0.5);

        medicamentosArea = new JTextArea(5, 40);
        medicamentosArea.setEditable(false);
        JScrollPane scrollMeds = new JScrollPane(medicamentosArea);
        splitNorte.setTopComponent(scrollMeds);

        recordatoriosArea = new JTextArea(5, 40);
        recordatoriosArea.setEditable(false);
        JScrollPane scrollRecs = new JScrollPane(recordatoriosArea);
        splitNorte.setBottomComponent(scrollRecs);
        panel.add(splitNorte, BorderLayout.NORTH);

        historialArea = new JTextArea(10, 40);
        historialArea.setEditable(false);
        JScrollPane scrollHistorial = new JScrollPane(historialArea);
        panel.add(scrollHistorial, BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new FlowLayout());
        panelSur.add(new JLabel("Seleccionar medicamento:"));
        comboMedicamentos = new JComboBox<>();
        panelSur.add(comboMedicamentos);

        JButton btnTomar = new JButton("Tomar medicamento");
        panelSur.add(btnTomar);

        JButton btnAgregar = new JButton("Agregar medicamento");
        panelSur.add(btnAgregar);

        JButton btnRecordatorio = new JButton("Agregar recordatorio");
        panelSur.add(btnRecordatorio);

        JButton btnVerificarRecs = new JButton("Verificar recordatorios");
        panelSur.add(btnVerificarRecs);

        JButton btnRemover = new JButton("Remover medicamento");
        panelSur.add(btnRemover);

        panel.add(panelSur, BorderLayout.SOUTH);
        add(panel);

        btnTomar.addActionListener(e -> tomarMedicamento());
        btnAgregar.addActionListener(e -> agregarMedicamentoDialog());
        btnRecordatorio.addActionListener(e -> agregarRecordatorioDialog());
        btnVerificarRecs.addActionListener(e -> verificarRecordatorios());
        btnRemover.addActionListener(e -> removerMedicamento());
    }

    private void cargarComboMedicamentos() {
        comboMedicamentos.removeAllItems();
        String textoMeds = controladorMed.obtenerTextoMedicamentos();
        for (String linea : textoMeds.split("\n")) {
            if (linea.contains("- ") && linea.contains("(")) {
                String nombre = linea.substring(2, linea.indexOf(" (")).trim();
                if (!nombre.isEmpty()) {
                    comboMedicamentos.addItem(nombre);
                }
            }
        }
    }

    private void tomarMedicamento() {
        String nombreSeleccionado = (String) comboMedicamentos.getSelectedItem();
        if (nombreSeleccionado != null && !nombreSeleccionado.isEmpty()) {
            String msg = controladorMed.tomarMedicamento(nombreSeleccionado);
            JOptionPane.showMessageDialog(this, msg, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            actualizarTodo();
            cargarComboMedicamentos();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un medicamento", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void agregarMedicamentoDialog() {
        JPanel panelInput = new JPanel(new GridLayout(6, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtNombre = new JTextField();
        JTextField txtDosis = new JTextField();
        JTextField txtCantidad = new JTextField();
        JTextField txtFecha = new JTextField();
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Medicamento", "Insulina"});
        JLabel lblGlucosa = new JLabel("Glucosa minima (solo para Insulina):");
        JTextField txtGlucosa = new JTextField("70.0");
        txtGlucosa.setEnabled(false);

        comboTipo.addActionListener(e -> txtGlucosa.setEnabled("Insulina".equals(comboTipo.getSelectedItem())));

        panelInput.add(new JLabel("Nombre:"));
        panelInput.add(txtNombre);
        panelInput.add(new JLabel("Dosis (mg/unidades):"));
        panelInput.add(txtDosis);
        panelInput.add(new JLabel("Cantidad disponible:"));
        panelInput.add(txtCantidad);
        panelInput.add(new JLabel("Fecha vencimiento (dd/MM/yyyy):"));
        panelInput.add(txtFecha);
        panelInput.add(new JLabel("Tipo:"));
        panelInput.add(comboTipo);
        panelInput.add(lblGlucosa);
        panelInput.add(txtGlucosa);

        int result = JOptionPane.showConfirmDialog(this, panelInput, "Agregar Medicamento", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) return;

        try {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) throw new IllegalArgumentException("Nombre requerido");

            int dosis = Integer.parseInt(txtDosis.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            String fecha = txtFecha.getText().trim();
            if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
                throw new IllegalArgumentException("Formato de fecha invalido (dd/MM/yyyy)");
            }

            Medicamento m;
            if ("Insulina".equals(comboTipo.getSelectedItem())) {
                double glucMin = Double.parseDouble(txtGlucosa.getText().trim());
                m = new Insulina(nombre, dosis, cantidad, fecha, glucMin);
            } else {
                m = new Medicamento(nombre, dosis, cantidad, fecha);
            }

            String msg = controladorMed.agregarMedicamento(m);
            JOptionPane.showMessageDialog(this, msg, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            actualizarTodo();
            cargarComboMedicamentos();
        } catch (Exception e) {
            System.err.println("Error en agregarMedicamentoDialog: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Datos invalidos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarRecordatorioDialog() {
        String nombreMed = (String) JOptionPane.showInputDialog(this, "Nombre del medicamento para recordatorio:");
        if (nombreMed == null || nombreMed.trim().isEmpty()) return;

        String horaStr = JOptionPane.showInputDialog(this, "Hora (HH:mm):");
        if (horaStr == null || !horaStr.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Formato de hora invalido (HH:mm)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalTime hora = LocalTime.parse(horaStr, DateTimeFormatter.ofPattern("HH:mm"));

        String freqStr = JOptionPane.showInputDialog(this, "Frecuencia en horas (ej 4):");
        int frecuencia;
        try {
            frecuencia = Integer.parseInt(freqStr);
            if (frecuencia <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Frecuencia invalida (numero >0)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Medicamento med = null;
        for (Medicamento m : paciente.getListaMedicamentos()) {
            if (m.getNombre().equalsIgnoreCase(nombreMed.trim())) {
                med = m;
                break;
            }
        }
        if (med == null) {
            JOptionPane.showMessageDialog(this, "Medicamento '" + nombreMed + "' no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Recordatorio r = new Recordatorio(hora, frecuencia, med);
        String msg = controladorMed.agregarRecordatorio(r);
        JOptionPane.showMessageDialog(this, msg, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        actualizarRecordatoriosArea();
        cargarComboMedicamentos();
    }

    private void verificarRecordatorios() {
        List<String> activos = controladorMed.verificarRecordatoriosActivos();
        if (activos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay recordatorios activos en este momento", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder("Recordatorios activos:\n");
            for (String a : activos) {
                sb.append("- ").append(a).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Alertas Activas", JOptionPane.WARNING_MESSAGE);
        }
        actualizarRecordatoriosArea();
    }

    private void removerMedicamento() {
        String nombre = (String) JOptionPane.showInputDialog(this, "Nombre del medicamento a remover:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            String msg = controladorMed.removerMedicamento(nombre.trim());
            JOptionPane.showMessageDialog(this, msg, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            actualizarTodo();
            cargarComboMedicamentos();
        }
    }

    public void actualizarMedicamentosArea() {
        medicamentosArea.setText(controladorMed.obtenerTextoMedicamentos());
    }

    public void actualizarHistorialArea() {
        historialArea.setText(controladorMed.obtenerTextoHistorial());
    }

    public void actualizarRecordatoriosArea() {
        recordatoriosArea.setText(controladorMed.obtenerTextoRecordatorios());
    }

    public void actualizarTodo() {
        actualizarMedicamentosArea();
        actualizarHistorialArea();
        actualizarRecordatoriosArea();
    }

    public ControladorMedicamentos getControladorMed() {
        return controladorMed;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}