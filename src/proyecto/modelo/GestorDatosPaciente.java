package proyecto.modelo;

import java.io.*;

public class GestorDatosPaciente {

    private final Paciente paciente;
    private final String rutPaciente;

    private final String ARCHIVO_MEDICAMENTOS;
    private final String ARCHIVO_RECORDATORIOS;
    private final String ARCHIVO_HISTORIAL;

    public GestorDatosPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        this.paciente = paciente;
        this.rutPaciente = paciente.getRut().replaceAll("[^a-zA-Z0-9.-]", "_");

        this.ARCHIVO_MEDICAMENTOS = rutPaciente + "_medicamentos.csv";
        this.ARCHIVO_RECORDATORIOS = rutPaciente + "_recordatorios.csv";
        this.ARCHIVO_HISTORIAL = rutPaciente + "_historial.csv";
    }

    public void guardarDatos() {
        try {
            guardarMedicamentos();
            guardarRecordatorios();
            guardarHistorial();
        } catch (IOException e) {
            System.err.println("Error al guardar datos para " + rutPaciente + ": " + e.getMessage());
        }
    }

    public void cargarDatos() {
        cargarMedicamentos();
        cargarRecordatorios();
        cargarHistorial();
    }

    private void guardarMedicamentos() throws IOException {
        //se usa try-with-resources para asegurar que el writer se cierre
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_MEDICAMENTOS))) {
            bw.write("tipo;csv_data\n"); // Header
            for (Medicamento med : paciente.getListaMedicamentos()) {
                if (med instanceof Insulina) {
                    bw.write("Insulina;" + med.toCSV() + "\n");
                } else {
                    bw.write("Medicamento;" + med.toCSV() + "\n");
                }
            }
        }
    }

    private void guardarRecordatorios() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_RECORDATORIOS))) {
            for (Recordatorio rec : paciente.getListaRecordatorios()) {
                bw.write(rec.toCSV() + "\n");
            }
        }
    }

    private void guardarHistorial() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_HISTORIAL))) {
            bw.write(paciente.getHistorial().toCSV());
        }
    }

    private void cargarMedicamentos() {
        File f = new File(ARCHIVO_MEDICAMENTOS);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            br.readLine();

            while ((linea = br.readLine()) != null) {
                try {
                    String[] parte = linea.split(";", 2);
                    if (parte.length < 2) continue;

                    String tipo = parte[0];
                    String csvData = parte[1];

                    Medicamento med;
                    if ("Insulina".equals(tipo)) {
                        med = Insulina.fromCSV(csvData);
                    } else {
                        med = Medicamento.fromCSV(csvData);
                    }
                    paciente.cargarMedicamento(med);
                } catch (Exception e) {
                    System.err.println("Error al cargar linea de medicamento: " + linea + " (" + e.getMessage() + ")");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar " + ARCHIVO_MEDICAMENTOS + ": " + e.getMessage());
        }
    }

    private void cargarRecordatorios() {
        File f = new File(ARCHIVO_RECORDATORIOS);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    Recordatorio rec = Recordatorio.fromCSV(linea, paciente.getListaMedicamentos());
                    paciente.cargarRecordatorio(rec);
                } catch (Exception e) {
                    System.err.println("Error al cargar linea de recordatorio: " + linea + " (" + e.getMessage() + ")");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar " + ARCHIVO_RECORDATORIOS + ": " + e.getMessage());
        }
    }

    private void cargarHistorial() {
        File f = new File(ARCHIVO_HISTORIAL);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                paciente.getHistorial().agregarRegistroDirecto(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar " + ARCHIVO_HISTORIAL + ": " + e.getMessage());
        }
    }
}