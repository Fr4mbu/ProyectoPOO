package Proyecto.Controlador;

import Proyecto.Vista.LoginPaciente;
import Proyecto.Modelo.Paciente;
import Proyecto.Vista.VentanaPaciente;

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


}
