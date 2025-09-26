package proyecto.controlador;

import proyecto.modelo.Paciente;
import proyecto.modelo.PacientesCSV;
import java.util.Optional;

public class ControladorPaciente {
    private final PacientesCSV pacientesCSV;

    public ControladorPaciente() {
        this.pacientesCSV = new PacientesCSV();
    }

    public String registrarPaciente(String rut, String nombre, int edad, String clave) {
        if (rut == null || rut.trim().isEmpty()) {
            return "ERROR: El RUT no puede ser nulo o vacio";
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            return "ERROR: El nombre no puede ser nulo o vacio";
        }
        if (clave == null || clave.trim().isEmpty()) {
            return "ERROR: La clave no puede ser nula o vacia";
        }
        if (edad < 0 || edad > 150) {
            return "ERROR: La edad debe ser entre 0 y 150";
        }

        return pacientesCSV.registrarPaciente(rut.trim(), nombre.trim(), edad, clave);
    }

    public Optional<Paciente> loginPaciente(String rut, String clave) {
        if (rut == null || rut.trim().isEmpty() || clave == null || clave.trim().isEmpty()) {
            return Optional.empty();
        }

        Optional<Paciente> optPaciente = pacientesCSV.obtenerPaciente(rut.trim());
        if (optPaciente.isPresent()) {
            Paciente p = optPaciente.get();
            if (p.getClave().equals(clave.trim())) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public String procesarLogin(String rut, String clave) {
        Optional<Paciente> opt = loginPaciente(rut, clave);
        if (opt.isPresent()) {
            return "EXITO: Bienvenido, " + opt.get().getNombre();
        } else {
            return "ERROR: RUT o clave incorrectos";
        }
    }

    public String listarPacientes() {
        return "Pacientes registrados: " + pacientesCSV.listarPacientes().size();
    }
}
