package sd.exercicio;

import java.util.Date;

public class PrimosThread extends Thread {
    int inicio;
    int fim;
    
    public PrimosThread() {
        super();
        this.inicio= 0;
        this.fim= 10;
    }
    public PrimosThread(int inicio, int fim) {
        super();
        this.inicio= inicio;
        this.fim= fim;
    }    
    
    public void run() {
        Primos p= new Primos(inicio,fim);
        p.go();
    }

    public static void main(String[] args) throws InterruptedException {
        Date t1 = new Date();
        
        PrimosThread p1 = new PrimosThread(0, 50000);
        PrimosThread p2 = new PrimosThread(50000, 100000);
        PrimosThread p3 = new PrimosThread(100000, 150000);
        PrimosThread p4 = new PrimosThread(150000, 200000);

        p1.start();
        p2.start();
        p3.start();
        p4.start();

        p4.join();

        Date t2 = new Date();
        long ms = t2.getTime()-t1.getTime();
        System.out.println("tempo ecorrido: " + ms + " ms");
    }
}
