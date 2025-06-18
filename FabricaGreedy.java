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

    /*
     * GREEDY
     *
     * los candidatos son Las máquinas disponibles, consideradas en orden descendente segun
     * su capacidad de producción (piezas que pueden fabricar).
     *
     * La estrategia de selección de los candidatos elegida (criterio greedy) fue la de en cada paso
     * seleccionar la máquina que produzca mas piezas, dado por el orden de la lista,
     * que no haga que el total de piezas producidas supere al objetivo para tratar de reducir la
     * cantidad total de máquinas utilizadas.
     * Mientras no se alcance el objetivo, se toma la maquina segun el criterio y se la agrega a la
     * solucion. Esto se repite hasta completar la cantidad de piezas o hasta que no se encuentre
     * solucion.
     *
     * No siempre se encuentra la mejor solucion (puede haber combinaciones con menos máquinas).
     * Tambien puede fallar en encontrar una solucion aunque exista.
     *
     * Es mucho mas eficiente frente a la solucion con backtracking pero tiene la desventaja de que
     * en algunos casospuede no encontrar la mejor solucion o directamente no encontrarla aunque exista.
     * La diferencia de eficiencia se puede ver en las metricas para analizar el costo de la solucion
     * de una tecnica y la otra. Cantidad de estados generados en backtracking y cantidad de candidatos
     * considerados en greedy.
     */
    public List<Maquina> resolverGreedy() {
        //ordeno las maquinas de mayor a menor, asi se empieza de mejor manera, no se daria el caso de que la
        // primer maquina produzca por ejemplo 3 piezas y no sirva la solucion
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

    public int getPiezasProducidas() {
        return this.piezasProducidas;
    }

    public int getCantidadCandidatosConsiderados() {
        return this.cantidadCandidatosConsiderados;
    }

    public int getCantidadMaquinasEncendidas() {
        return cantidadMaquinasEncendidas;
    }
}

