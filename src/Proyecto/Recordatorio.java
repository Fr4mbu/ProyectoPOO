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

    //verificar si es hora de tomar medicamento
    public void verificarAlerta() {
        LocalTime ahora = LocalTime.now();
        if (ahora.getHour() == hora.getHour() && ahora.getMinute() == hora.getMinute()) {
            System.out.println("Alerta: Es hora de tomar " + medicamentoAsociado.getNombre());
        }
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
