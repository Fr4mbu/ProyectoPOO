package proyecto.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDatosPaciente {

    private final Paciente paciente;
    private final String rutPaciente;
    private final String ARCHIVO_JSON;
    private final Gson gson;

    private static class PacienteData {
        List<String> medicamentosCSV;
        List<String> recordatoriosCSV;
        List<String> historialLineas;
    }

    public GestorDatosPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        this.paciente = paciente;
        this.rutPaciente = paciente.getRut().replaceAll("[^a-zA-Z0-9.-]", "_");

        this.ARCHIVO_JSON = rutPaciente + ".json";

        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void guardarDatos() {
        PacienteData data = new PacienteData();

        data.medicamentosCSV = new ArrayList<>();
        for (Medicamento med : paciente.getListaMedicamentos()) {
            if (med instanceof Insulina) {
                data.medicamentosCSV.add("Insulina;" + med.toCSV());
            } else {
                data.medicamentosCSV.add("Medicamento;" + med.toCSV());
            }
        }

        data.recordatoriosCSV = new ArrayList<>();
        for (Recordatorio rec : paciente.getListaRecordatorios()) {
            data.recordatoriosCSV.add(rec.toCSV());
        }

        data.historialLineas = paciente.getHistorial().getRegistros();

        try (Writer writer = new BufferedWriter(new FileWriter(ARCHIVO_JSON))) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar JSON para " + rutPaciente + ": " + e.getMessage());
        }
    }

    public void cargarDatos() {
        File f = new File(ARCHIVO_JSON);
        if (!f.exists()) {
            return;
        }

        try (Reader reader = new BufferedReader(new FileReader(f))) {

            PacienteData data = gson.fromJson(reader, PacienteData.class);
            if (data == null) {
                return;
            }

            paciente.limpiarDatosCargados();

            if (data.medicamentosCSV != null) {
                for (String linea : data.medicamentosCSV) {
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
            }

            if (data.recordatoriosCSV != null) {
                for (String linea : data.recordatoriosCSV) {
                    try {
                        Recordatorio rec = Recordatorio.fromCSV(linea, paciente.getListaMedicamentos());
                        paciente.cargarRecordatorio(rec);
                    } catch (Exception e) {
                        System.err.println("Error al cargar linea de recordatorio: " + linea + " (" + e.getMessage() + ")");
                    }
                }
            }

            if (data.historialLineas != null) {
                for (String linea : data.historialLineas) {
                    paciente.getHistorial().agregarRegistroDirecto(linea);
                }
            }

        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error al cargar o parsear " + ARCHIVO_JSON + ": " + e.getMessage());
        }
    }
}