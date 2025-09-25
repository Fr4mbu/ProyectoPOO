package Proyecto.Controlador;

import Proyecto.Modelo.Medicamento;
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

    //registrar un medicamento
    public void registrarMedicamento(Medicamento medicamento) {
        paciente.agregarMedicamento(medicamento);
        if(ventanaVista!=null){
            ventanaVista.mostrarMensaje("Medicamento registrado: " + medicamento.getNombre());
        }
    }

    //lista de medicamentos
    public List<Medicamento> obtenerMedicamentos(){
        return paciente.getListaMedicamentos();
    }

    //tomar m¿un medicamento por nombre
    public void tomarMedicamento(String nombre){
        boolean resultado = paciente.tomarMedicamento(nombre);
        if(ventanaVista!=null){
            if(resultado){
                ventanaVista.mostrarMensaje("Se tomo correctamente: " + nombre);
            } else {
                ventanaVista.mostrarMensaje("No se encontro el medicamento: " + nombre);
            }
        }
    }








}
