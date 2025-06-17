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
     * BACKTRACKING
     *
     * Para resolver el problema se prueban todas las combinaciones de máquinas hasta encontrar la mejor.
     * La funcion va armando una secuencia maquina por máquina. En cada paso, prueba con una máquina y se
     * llama a sí misma para ver a dónde la lleva ese camino. Si el camino no funciona vuelve para atras
     * y prueba con la siguiente máquina en el mismo punto.
     * El arbol de exploracion se genera a traves de las llamadas recursivas de la funcion, cada llamada
     * representa un nodo. El nodo raiz es la llamda inicial con cero piezas. El bucle for que recorre
     * las maquinas va creando las ramas, por cada maquina disponible se realiza una nueva llamada recursiva
     * que profundiza un nivel para formar un nodo hijo. Cuando la rama termina su camino, por una poda o
     * porque es solucion, la ejecucion regresa al nodo anterior, deshace la ultima eleccion y continua el bloque
     * por la siguiente rama.
     *
     * Se propusieron dos posibles podas para encotnrar la solucion mas rapido y eficientemente:
     * - Si la secuencia que estamos armando tiene mas activaciones que la mejor que encontramos
     * antes, no se sigue por ese camino, se corta.
     * - Si nos pasamos de la cantidad de piezas que teníamos que fabricar, la solucion ya no es valida
     * por lo tanto ese camino tampoco sirve, así que lo descartamos.
     * Al final como se exploraron todas las posibilidades validas, nos aseguramos de
     * quedarnos con la secuencia más corta que cumple con el número de piezas pedido.
     *
     * Los estados finales donde se detiene la exploracion son cuando se alcanza la cantidad exacta de piezas,
     * cuando se supera la cantidad de piezas objetivo y cuando la secuencia de maquinas ya es mas larga que la
     * mejor solucion encontrada.
     * Los estados solucion es un estado que cumple con la condicion del problema, cuando la cantidad de piezas
     * producidas es exactamente igual a la cantidad objetivo
     *
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
