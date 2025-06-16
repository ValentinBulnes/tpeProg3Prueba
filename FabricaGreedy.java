import java.util.ArrayList;
import java.util.List;

public class FabricaGreedy {

    private List<Maquina> maquinasDisponibles;
    private int piezasObjetivo;
    private List<Maquina> solucionGreedy;
    private int cantidadCandidatosConsiderados;
    private int cantidadMaquinasEncendidas;
    private int piezasProducidas;

    public FabricaGreedy(List<Maquina> maquinasDisponibles, int piezasObjetivo) {
        this.maquinasDisponibles = maquinasDisponibles;
        this.piezasObjetivo = piezasObjetivo;
        this.solucionGreedy = new ArrayList<>();
        this.cantidadCandidatosConsiderados = 0;
        this.cantidadMaquinasEncendidas = 0;
        this.piezasProducidas = 0;
    }
    public List<Maquina> resolverGreedy() {
        //ordeno las maquinas de mayor a menor, asi se empieza de mejor manera, no se daria el caso de que la primer maquina produzca por ejemplo 3 piezas
        //y no sirva la solucion
        maquinasDisponibles.sort((a, b) -> b.getPiezasProducidas() - a.getPiezasProducidas());
        int totalPiezas = 0;
        while (totalPiezas < piezasObjetivo) {
            boolean maquinaElegida = false;
            for (Maquina m : maquinasDisponibles) {
                cantidadCandidatosConsiderados++;

                if (totalPiezas + m.getPiezasProducidas() <= piezasObjetivo) {
                    cantidadMaquinasEncendidas++;
                    solucionGreedy.add(m);
                    totalPiezas += m.getPiezasProducidas();
                    maquinaElegida = true;
                    //se eligio la maquina y la agrego a la solucionGreddy con el add
                }
            }
            if (!maquinaElegida) {
                // No se puede usar esta solucion, dejo vacio el array
                solucionGreedy = new ArrayList<>();
            }
        }
        this.piezasProducidas = totalPiezas;
        return new ArrayList<>(this.solucionGreedy);
    }

    public int getPiezasObjetivo() {
        return this.piezasObjetivo;
    }

    public int getCantidadCandidatosConsiderados() {
        return this.cantidadCandidatosConsiderados;
    }

    public int getCantidadMaquinasEncendidas() {
        return cantidadMaquinasEncendidas;
    }
}

