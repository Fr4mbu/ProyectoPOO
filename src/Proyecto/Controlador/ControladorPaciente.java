package Proyecto.Controlador;

import Proyecto.Modelo.*;
import Proyecto.Vista.LoginPaciente;
import Proyecto.Vista.VentanaPaciente;

import java.awt.*;

public class ControladorPaciente {
    private Paciente paciente;
    private LoginPaciente loginVista;
    private VentanaPaciente ventanaVista;

    //constructor con paciente y login
    public ControladorPaciente(Paciente paciente, LoginPaciente loginVista) {
        this.paciente = paciente;
        this.loginVista = loginVista;
    }

    //asociar ventana principal luego del login
    public void setVentanaPaciente(VentanaPaciente ventanaVista) {
        this.ventanaVista = ventanaVista;
    }

    //registrar un medicamento
    public void registrarMedicamento(Medicamento medicamento) {
        paciente.agregarMedicamento(medicamento);
        guardarPaciente();
        if (ventanaVista != null) {
            ventanaVista.mostrarMensaje("Medicamento registrado: " + medicamento.getNombre());
        }
    }

    //tomar un medicamento por nombre
    public void tomarMedicamento(String nombre) {
        boolean encontrado = false;
        for (Medicamento m: paciente.getListaMedicamentos()) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                paciente.tomarMedicamento(nombre);
                encontrado = true;
                break;
            }
        }
        guardarPaciente();
        if (ventanaVista != null) {
            if (encontrado) {
                ventanaVista.mostrarMensaje("Se tomo correctamente: " + nombre);
            } else {
                ventanaVista.mostrarMensaje("No se encontro el medicamento: " + nombre);
            }
        }
    }

    //obtener lista de medicamentos
    public List<Medicamento> obtenerMedicamentos() {
        return paciente.getListaMedicamentos();
    }

    //obtener historial de consumo
    public List<String> obtenerHistorial() {
        return paciente.getHistorial().getRegistros();
    }

    //login
    public boolean login(String usuario, String contraseña) {
        if (paciente.getUsuario().equals(usuario) && paciente.getContraseña().equals(contraseña)) {
            if (loginVista != null) {
                loginVista.mostrarMensaje("Login exitoso");
            }
            return true;
        } else {
            if (loginVista != null) {
                loginVista.mostrarMensaje("Usuario o contraseña incorrectos");
            }
            return false;
        }
    }

    //guardar paciente en JSON
    private void guardarPaciente () {
        BaseDatos.guardarPacientes(List.of(paciente));
    }
}
