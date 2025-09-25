package Proyecto;

//heredada de medicamento
public class Insulina extends Medicamento {
    private double nivelGlucosa;
    private int dosisRecomendada;

    //constructor
    public Insulina(String nombre, int dosis, int cantidad, String fecha, double nivelGlucosa, int dosisRecomendada) {
        super(nombre, dosis, cantidad, fecha); // esto llama al constructor de Medicamento
        this.nivelGlucosa = nivelGlucosa;
        this.dosisRecomendada = dosisRecomendada;
    }

    //metodo adicional para recomendar dosis
    public void recomendarDosis() {
        if(nivelGlucosa > 180) {
            System.out.println("Glucosa alta (" + nivelGlucosa + "). Se recomienda aplicar " + dosisRecomendada + " unidades de insulina");
        } else if (nivelGlucosa < 70) {
            System.out.println("Glucosa baja (" + nivelGlucosa + "). Precaucion, aplicar insulina");
        } else {
            System.out.println("Glucosa estable (" + nivelGlucosa + "). Mantener dosis actual");
        }
    }

    //getters y setters
    public double getNivelGlucosa() {
        return nivelGlucosa;
    }
     public void setNivelGlucosa(double nivelGlucosa) {
        this.nivelGlucosa = nivelGlucosa;
     }

     public int getDosisRecomendada() {
        return dosisRecomendada;
     }

     public void setDosisRecomendada(int dosisRecomendada) {
        this.dosisRecomendada = dosisRecomendada;
     }
}
