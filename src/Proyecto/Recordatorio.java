package Proyecto;

import java.time.LocalTime;

public class Recordatorio {
    private LocalTime hora; //hora de la toma
    private int frecuenciaHoras; //cada cuanto repetir
    private Medicamento medicamentoAsociado;

    //constructor
    public Recordatorio(LocalTime hora, int frecuenciaHoras, Medicamento medicamento) {
        this.hora = hora;
        this.frecuenciaHoras = frecuenciaHoras;
        this.medicamentoAsociado = medicamento;
    }

    //mostrar recordatorio
    public void mostrarRecordatorio(){
        System.out.println("Recordatorio: Tomar " + medicamentoAsociado.getNombre() + " a las " + hora);
        System.out.println("Frecuencia: cada " + frecuenciaHoras +" horas");
    }

    //getters
    public LocalTime getHora() {
        return hora;
    }

    public int  getFrecuenciaHoras() {
        return frecuenciaHoras;
    }

    public Medicamento getMedicamentoAsociado() {
        return medicamentoAsociado;
    }
}
