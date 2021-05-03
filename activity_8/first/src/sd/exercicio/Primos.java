package sd.exercicio;

import java.util.*;

public class Primos {
    private int start;
    private int end;
    
    
    public Primos(int start, int end) {
        this.start= start;
        this.end= end;
    }
    
    int count_primes(int start, int end) {
        int total = 0;
        int i = start; // check if prime    
        while (i <= end) {
            int c;
            for (c = 2; c <= i - 1; c++) {
                if (i % c == 0) {
                    break;
                }
            }
            if (c == i) {
                //printf("%d\n", i);
                total++;
            }
            i++;   // next prime candidate
        }
        return total;
    }

    public void go() {
	
        int found_primes = count_primes(start, end);
        System.out.println("  found "+found_primes);
    }


    public static void main(String[] args) {
    	
    	Date t1 = new Date();
    	
    	//4 objetos primos
    	Primos p1 = new Primos(0,50000);  //argumentos = start e end
    	Primos p2 = new Primos(50000,100000);
    	Primos p3 = new Primos(100000,150000);
    	Primos p4 = new Primos(200000,250000);
    	
    	//invocar o mÃ©todo go para fazer a contagem dos numeros primos
    	//estamos a criar trabalho para "entreter" o processadosr
    	
    	p1.go();  //found 5133
    	p2.go();  //found 4459
    	p3.go();  //found 4256
    	p4.go();  // found 4136
    	//demora 5 ou 6 segundos
    	
    	Date t2 = new Date();
    	long ms = t2.getTime() - t1.getTime();
    	System.out.print("tempo decorrido:" + ms + " ms.");
    }

}