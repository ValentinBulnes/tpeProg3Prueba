import java.util.ArrayList;
import java.util.List;

public class FabricaGreedy {

    private List<Maquina> maquinasDisponibles;
    private int piezasObjetivo;
    private List<Maquina> solucionGreddy;
    private int cantidadCandidatosConsiderados;
    int cantidadMaquinasEncendidas=0;

    public FabricaGreedy(List<Maquina> maquinasDisponibles, int piezasObjetivo) {
        this.maquinasDisponibles = maquinasDisponibles;
        this.piezasObjetivo = piezasObjetivo;
        this.solucionGreddy = new ArrayList<>();
    }
    public List<Maquina> resolverGreedy() {
        //ordeno las maquiinas de mayor a menor, asi se empieza de mejor manera, no se daria el caso de que la primer maquina produzca por ejemplo 3 piezas
        //y no sirva la solucion
        maquinasDisponibles.sort((a, b) -> b.getPiezasProducidas() - a.getPiezasProducidas());
        int totalpiezas = 0;
        while (totalpiezas < piezasObjetivo) {
            boolean maquinaElegida = false;
            for (Maquina m : maquinasDisponibles) {
                cantidadCandidatosConsiderados++;

                if (totalpiezas + m.getPiezasProducidas() <= piezasObjetivo) {
                    cantidadMaquinasEncendidas++;
                    solucionGreddy.add(m);
                    totalpiezas += m.getPiezasProducidas();
                    maquinaElegida = true;
                    //se eligio la maquina y la agrego a la solucionGreddy con el add
                }
            }
            if (!maquinaElegida) {
                // No se puede usar esta solucion, dejo vacio el array
                solucionGreddy = new ArrayList<>();

            }
        }
        return solucionGreddy;
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

