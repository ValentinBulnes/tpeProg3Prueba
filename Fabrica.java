import java.util.ArrayList;
import java.util.List;

public class Fabrica {

    private List<Maquina> maquinasDisponibles;
    private int piezasObjetivo;
    private List<Maquina> mejorSecuenciaMaquinas;
    private int minActivaciones;
    private int estadosGenerados;

    public Fabrica(List<Maquina> maquinas, int piezasObjetivo) {
        this.maquinasDisponibles = new ArrayList<>(maquinas);
        this.piezasObjetivo = piezasObjetivo;
    }

    public List<Maquina> encontrarSecuenciaOptima() {
        this.mejorSecuenciaMaquinas = new ArrayList<>();
        this.minActivaciones = Integer.MAX_VALUE;
        this.estadosGenerados = 0;

        List<Maquina> secuenciaActual = new ArrayList<>();
        backtracking(0, secuenciaActual);

        return new ArrayList<>(this.mejorSecuenciaMaquinas);
    }

    private void backtracking(int piezasProducidasActual, List<Maquina> secuenciaActual) {
        this.estadosGenerados++;

        if (piezasProducidasActual == this.piezasObjetivo) {  //caso base
            if (secuenciaActual.size() < this.piezasObjetivo) {
                this.minActivaciones = secuenciaActual.size();
                this.mejorSecuenciaMaquinas = new ArrayList<>(secuenciaActual);
            }
            return;  //termina esta rama
        }

        if (secuenciaActual.size() >= this.minActivaciones) {  //poda
            return;  //vuelve a la llamada anterior
        }

        if (piezasProducidasActual > this.piezasObjetivo) {  //poda
            return;
        }

        for (Maquina maquinaAProbar : this.maquinasDisponibles) {
            secuenciaActual.add(maquinaAProbar);
            backtracking(piezasProducidasActual + maquinaAProbar.getPiezasProducidas(), secuenciaActual);  //llamada recursiva con el nuevo estado
            secuenciaActual.remove(secuenciaActual.size() - 1);  //deshacer
        }
    }

    public int getPiezasObjetivo() {
        return this.piezasObjetivo;
    }

    public int getMinActivaciones() {
        return this.minActivaciones;
    }

    public int getEstadosGenerados() {
        return this.estadosGenerados;
    }
}
