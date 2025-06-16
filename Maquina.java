public class Maquina {
    private String id;
    private int piezasProducidas;

    public Maquina(String id, int piezasProduce) {
        this.id = id;
        this.piezasProducidas = piezasProduce;
    }

    public String getId() {
        return id;
    }
    public int getPiezasProducidas() {
        return piezasProducidas;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPiezasProducidas(int piezasProducidas) {
        this.piezasProducidas = piezasProducidas;
    }

    @Override
    public String toString() {
        return id + "(" + piezasProducidas + ")";
    }
}
