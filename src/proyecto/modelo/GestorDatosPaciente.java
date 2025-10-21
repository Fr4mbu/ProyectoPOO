package proyecto.modelo;

import java.io.IOException;

//clase responsable de la persistencia de datos de un paciente especifico (los carga y los guarda)
public class GestorDatosPaciente {
    private final Paciente paciente;
    private final String rutPaciente;

    private final String ARCHIVO_MEDICAMENTOS;
    private final String ARCHIVO_RECORDATORIO;
    private final String ARCHIVO_HISTORIAL;

    public GestorDatosPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        this.paciente = paciente;
        this.rutPaciente = paciente.getRut().replace("[^a-zA-Z0-9.-]", "_");

        //definir los nombres de los archivos especificos del paciente
        this.ARCHIVO_MEDICAMENTOS = rutPaciente + "_medicamentos.csv";
        this.ARCHIVO_RECORDATORIO = rutPaciente + "_recordorios.csv";
        this.ARCHIVO_HISTORIAL = rutPaciente + "_historial.csv";
    }

    //metodo para guardar los datos del paciente
    //este metodo sera llamado por el ControladorMedicamentos
    public void guardarDatos() {
        try {
            guardarMedicamentos();
            guardarRecordatorios();
            gusrdarHistorial();
        } catch (IOException e) {
            System.out.println("Error al guardar datos");
        }
    }





}
