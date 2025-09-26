package proyecto.controlador;

import proyecto.modelo.Paciente;
import proyecto.modelo.Medicamento;
import proyecto.modelo.Recordatorio;
import proyecto.modelo.Insulina;

import java.time.LocalTime;
import java.util.List;

public class ControladorMedicamentos {
    private final Paciente paciente;

    public ControladorMedicamentos(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        this.paciente = paciente;
    }

    public String agregarMedicamento(Medicamento m) {
        return paciente.agregarMedicamento(m);
    }

    public String tomarMedicamento(String nombre) {
        return paciente.tomarMedicamento(nombre);
    }

    public String removerMedicamento(String nombre) {
        return paciente.removerMedicamento(nombre);
    }

    public String agregarRecordatorio(Recordatorio r) {
        try {
            if (r == null) {
                return "ERROR: El recordatorio no puede ser nulo";
            }
            if (!paciente.getListaMedicamentos().stream().anyMatch(existing ->
                    existing.getNombre().equalsIgnoreCase(r.getMedicamentoAsociado().getNombre()))) {
                return "ERROR: El medicamento asociado al recordatorio no esta registrado";
            }
            return paciente.agregarRecordatorio(r);
        } catch (IllegalArgumentException e) {
            return "ERROR de validacion: " + e.getMessage();
        } catch (Exception e) {
            System.err.println("Error inesperado en agregarRecordatorio: " + e.getMessage());
            return "ERROR inesperado: " + e.getMessage();
        }
    }

    public List<String> verificarRecordatoriosActivos() {
        return paciente.verificarRecordatoriosActivos();
    }

    public String obtenerTextoMedicamentos() {
        return paciente.obtenerTextoMedicamentos();
    }

    public String obtenerTextoRecordatorios() {
        return paciente.obtenerTextoRecordatorios();
    }

    public String obtenerTextoHistorial() {
        return paciente.obtenerTextoHistorial();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public List<String> obtenerNombresMedicamentos() {
        return paciente.getListaMedicamentos().stream()
                .map(Medicamento::getNombre)
                .toList();
    }
}