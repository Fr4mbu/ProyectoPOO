package Proyecto;

//heredada de medicamento
public class Insulina extends Medicamento {
    private double nivelGlucosa;
    private int dosisRecomendada;

    //constructor
    private Insulina(String nombre, int dosis, int cantidad, String fecha, double nivelGlucosa, int dosisRecomendada) {
        super(nombre, dosis, cantidad, fecha); // esto llama al constructor de Medicamento
        this.nivelGlucosa = nivelGlucosa;
        this.dosisRecomendada = dosisRecomendada;
    }


}
