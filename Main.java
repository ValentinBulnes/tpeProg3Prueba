import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        //cargar configuracion desde el archivo de texto
        String rutaArchivo = "ejemplo.csv";
        List<Maquina> maquinas = new ArrayList<>();
        int piezasObjetivo = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            // Leer la primera línea para las piezas objetivo
            String lineaObjetivo = br.readLine();
            if (lineaObjetivo != null) {
                piezasObjetivo = Integer.parseInt(lineaObjetivo.trim());
            }

            // Leer las líneas restantes para configurar las máquinas
            String lineaMaquina;
            while ((lineaMaquina = br.readLine()) != null) {
                String[] partes = lineaMaquina.split(",");
                if (partes.length == 2) {
                    String id = partes[0].trim();
                    int piezas = Integer.parseInt(partes[1].trim());
                    maquinas.add(new Maquina(id, piezas));
                }
            }
            System.out.println("Piezas a producir: " + piezasObjetivo);
            System.out.println("Máquinas disponibles: " + maquinas);
            System.out.println();

        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.err.println("Error numero en formato incorrecto" + e.getMessage());
            return;
        }

        FabricaBacktracking fabricaBacktracking = new FabricaBacktracking(maquinas,  piezasObjetivo);

        FabricaGreedy fabricaGreedy = new FabricaGreedy(maquinas, piezasObjetivo);

        System.out.println("Backtracking");
        System.out.println("Secuencia de maquinas: " + fabricaBacktracking.encontrarSecuenciaOptima());
        System.out.println("Cantidad de piezas producidas: " + fabricaBacktracking.getPiezasProducidas());
        System.out.println("Cantidad de puestas en funcionamiento requeridas: " + fabricaBacktracking.getMinActivaciones());
        System.out.println("Cantidad de estados generados: " + fabricaBacktracking.getEstadosGenerados());
        System.out.println();
        System.out.println("Greddy");
        System.out.println("Solucion obtenida:" + fabricaGreedy.resolverGreedy());
        System.out.println("Cantidad de piezas producidas: " + fabricaGreedy.getPiezasObjetivo());
        System.out.println("Cantidad de puestas en funcionamiento requeridas: " + fabricaGreedy.getCantidadMaquinasEncendidas());
        System.out.println("Cantidad de candidatos considerados: " + fabricaGreedy.getCantidadCandidatosConsiderados());
    }
}
