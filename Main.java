import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Ejemplo: (M1, 7), (M2, 3), (M3, 4), (M4, 1)");
        int piezas = 12;

        List<Maquina> maquinas = new ArrayList<>();
        maquinas.add(new Maquina("M1", 7));
        maquinas.add(new Maquina("M2", 3));
        maquinas.add(new Maquina("M3", 4));
        maquinas.add(new Maquina("M4", 1));

        Fabrica fabrica = new Fabrica(maquinas, piezas);

        FabricaGreedy fabrica2 = new FabricaGreedy(maquinas, piezas);

        System.out.println("Backtracking");
        System.out.println("Solucion obtenida:");
        System.out.println("Secuencia de maquinas: " + fabrica.encontrarSecuenciaOptima());
        System.out.println("Cantidad de piezas producidas: " + fabrica.getPiezasObjetivo());
        System.out.println("Cantidad de puestas en funcionamiento requeridas: " + fabrica.getMinActivaciones());
        System.out.println("Cantidad de estados generados: " + fabrica.getEstadosGenerados());

        System.out.println("Greddy");
        System.out.println("Solucion obtenida:" + fabrica2.resolverGreedy());
        System.out.println("Cantidad de piezas producidas: " + fabrica2.getPiezasObjetivo());
        System.out.println("Cantidad de puestas en funcionamiento requeridas: " + fabrica2.getCantidadMaquinasEncendidas());
        System.out.println("Cantidad de candidatos considerados: " + fabrica2.getCantidadCandidatosConsiderados());
    }
}
