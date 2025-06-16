import java.util.ArrayList;
import java.util.List;

public class FabricaBacktracking {

    private List<Maquina> maquinasDisponibles;
    private int piezasObjetivo;
    private List<Maquina> mejorSecuenciaMaquinas;
    private int minActivaciones;
    private int estadosGenerados;

    public FabricaBacktracking(List<Maquina> maquinas, int piezasObjetivo) {
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

    /*
     * <<Breve explicación de la estrategia de resolución. Por ejemplo:
     * - Cómo se genera el árbol de exploración.
     * - Cuáles son los estados finales y estados solución.
     * - Posibles podas.
     * - etc.>>
     */
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

    public int getEstadosGenerados() {
        return this.estadosGenerados;
    }

    public int getMinActivaciones() {
        if (this.minActivaciones == Integer.MAX_VALUE) {
            return 0;
        }
        return this.minActivaciones;
    }

    public int getPiezasProducidas() {
        if (this.mejorSecuenciaMaquinas == null) {
            return 0;
        }
        int total = 0;
        for (Maquina m : this.mejorSecuenciaMaquinas) {
            total += m.getPiezasProducidas();
        }
        return total;
    }
}
