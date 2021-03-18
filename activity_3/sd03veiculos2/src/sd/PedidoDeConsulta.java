package activity_3.sd03veiculos2.src.sd;

public class PedidoDeConsulta extends Pedido implements java.io.Serializable {

    private String matricula;

    public PedidoDeConsulta(String matricula) {
        this.matricula= matricula;
    }

    public String getMatricula() {
        return matricula;
    }
}