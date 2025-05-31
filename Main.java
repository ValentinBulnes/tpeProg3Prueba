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

        List<Maquina> secuencia = fabrica.encontrarSecuenciaOptima();

        if (secuencia.isEmpty()) {
            System.out.println("No se encontró una secuencia de máquinas para producir " + piezas + " piezas.");
        } else {
            System.out.println("Piezas objetivo a producir: " + piezas);
            System.out.println("Secuencia óptima de máquinas encontrada: " + secuencia);

        }
    }
}
