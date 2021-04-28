package sd.cc;

import java.util.LinkedList;

public class Produtor extends Thread {

    LinkedList<String> produtos;

    public Produtor(LinkedList<String> produtos) {
        super();
        this.produtos = produtos;
    }

    public void run() {
        String name = Thread.currentThread().getName();
        try {
            System.out.println("# P " + name + " ponto 0");

            for (int i = 0; i < 4; i++) {
                
                if (produtos.size() < 10) {   // ciclo de producao
                    String x = "p_" + name + "_" + i;
                    produtos.add(x);
                    System.out.println("\t\t PRODUTOR " + name + " gera " + x.toString());
                } 
                    
            }
            System.out.println("\t# P " + name + " ponto 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
