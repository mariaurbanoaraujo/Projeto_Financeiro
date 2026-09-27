package business;

public class Aplicacao implements IAplicacao {
    private float montanteFinal;

    @Override
    public void calcularRendimento(float valorAplicado, int prazo, float taxa) {
        float i = taxa / 100.0f;
        this.montanteFinal = (float) (valorAplicado * Math.pow(1 + i, prazo));
    }

    public float getMontanteFinal() {
        return montanteFinal;
    }
}