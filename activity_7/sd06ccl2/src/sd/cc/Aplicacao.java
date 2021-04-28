package sd.cc;

import java.util.LinkedList;



public class Aplicacao {
    static LinkedList<String> produtos;

    public static void main(String[] args) throws Exception {
	System.out.println("main: inicio ---------------------------");
	
	produtos= new LinkedList<>();   // partilhada entre as threads

	// 3 objectos do tipo Thread (subclasse Consumidor)
	Consumidor c1= new Consumidor(produtos);
	Consumidor c2= new Consumidor(produtos);
	Consumidor c3= new Consumidor(produtos);
	
	// 1 produtor
	Produtor p1= new Produtor(produtos);

	// lancar as 3+1, cada uma executará por si
	c1.start();
	c2.start();
	c3.start();

	p1.start();
	
	System.out.println("main: fim ---------------------------");
    }
    
}
