package activity_2.src;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

    private String address= null;
    private int sPort= 0;
    
    public EchoClient(String add, int p) {
		address= add;
		sPort=p;
    }
    
    public static void main(String[] args){
		if (args.length < 3) {
			System.err.println("Argumentos insuficientes:  java EchoClient ADDRESS PORT MSG");
			System.exit(1);
		}
		
		try {
			String addr= args[0];
			int p= Integer.parseInt(args[1]);	
			String msg= args[2];
			EchoClient cl= new EchoClient(addr,p);
			
			cl.go(msg);   // faz o pretendido
		}
		catch (Exception e) {
			System.out.println("Problema no formato dos argumentos");
			e.printStackTrace();
		}
    }
    
    
    
    public void go(String msg){
	
		System.out.println("Going to send: " + msg);
	
		Socket s;
		try {
			s = new Socket(address, sPort);

			OutputStream socketOut= s.getOutputStream();
			socketOut.write(msg.getBytes());

			InputStream socketIn= s.getInputStream();
			byte[] b= new byte[256];
			int readed = socketIn.read(b,0,256);
			String response= new String(b, 0, readed);
			System.out.println("Resposta: " + response);

		} catch (UnknownHostException e1) {
			System.err.println("Problema " + e1.getMessage());
			e1.printStackTrace();
		} catch (IOException e2) {
			System.err.println("Problema " + e2.getMessage());
			e2.printStackTrace();
		}
    }
    
}
