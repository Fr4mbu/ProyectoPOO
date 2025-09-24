package Proyecto;

import java.time.LocalDate;

public class Recordatorio {
    private LocalDate hora; //hora de la toma
    private int frecuenciaHoras; //cada cuanto repetir
    private Medicamento medicamentoAsociado;

    //constructor
    public Recordatorio(LocalDate hora, int frecuenciaHoras, Medicamento medicamento) {
        this.hora = hora;
        this.frecuenciaHoras = frecuenciaHoras;
        this.medicamentoAsociado = medicamento;
    }


}
