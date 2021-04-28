package sd.cc;

import java.util.LinkedList;

public class Consumidor extends Thread {

    LinkedList<String> produtos;

    public Consumidor(LinkedList<String> produtos) {
        super();
        this.produtos = produtos;
    }



    public void run() {
        String name = Thread.currentThread().getName();
        try {
            System.out.println("# C " + name + " ponto 0");


            for (int i = 0; i < 3; i++) { // loop de consumo

                if (produtos.isEmpty()) {
                    System.out.println("\t C " + name + " DESISTE");
                    break;
                }
                
                // *************************************
                // NÃO ALTERAR ESTE BLOCO **************
                String p = produtos.getFirst();
                Thread.sleep(1000); // espera propositada para simular ambiente de risco
                produtos.removeFirst();
                // *************************************

                System.out.println("CONSUMIDOR " + name + " consome " + p);


            }


            System.out.println("\t# C " + name + " ponto 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
