import java.util.ArrayList;
import java.util.List;

public class Fabrica {

    private List<Maquina> maquinasDisponibles;
    private int piezasObjetivo;
    private List<Maquina> mejorSecuenciaMaquinas;
    private int minActivaciones;

    public Fabrica(List<Maquina> maquinas, int piezasObjetivo) {
        this.maquinasDisponibles = new ArrayList<>(maquinas);
        this.piezasObjetivo = piezasObjetivo;
        this.mejorSecuenciaMaquinas = new ArrayList<>();
        this.minActivaciones = Integer.MAX_VALUE;
    }

    public List<Maquina> encontrarSecuenciaOptima() {
        this.mejorSecuenciaMaquinas = new ArrayList<>();
        this.minActivaciones = Integer.MAX_VALUE;

        List<Maquina> secuenciaActual = new ArrayList<>();

        backtracking(0, secuenciaActual);

        if (this.minActivaciones == Integer.MAX_VALUE) {
            return new ArrayList<>();
        }

        return this.mejorSecuenciaMaquinas;
    }

    private void backtracking(int piezasProducidasActual, List<Maquina> secuenciaActual) {

        if (secuenciaActual.size() >= this.minActivaciones) {  //poda
            return;  //vuelve a la llamada anterior
        }

        if (piezasProducidasActual == this.piezasObjetivo) {  //caso base
            this.minActivaciones = secuenciaActual.size();
            this.mejorSecuenciaMaquinas = new ArrayList<>(secuenciaActual);
            return;  //termina esta rama
        }

        if (piezasProducidasActual > this.piezasObjetivo) {  //poda
            return;
        }

        for (Maquina maquinaAProbar : this.maquinasDisponibles) {
            secuenciaActual.add(maquinaAProbar);
            int nuevasPiezasProducidas = piezasProducidasActual + maquinaAProbar.getPiezasProducidas();
            backtracking(nuevasPiezasProducidas, secuenciaActual);  //llamada recursiva con el nuevo estado
            secuenciaActual.remove(secuenciaActual.size() - 1);  //deshacer
        }
    }
}
