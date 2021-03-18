package activity_2.src;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private int serverPort;	
    
    public EchoServer(int p) {
		serverPort= p;		
    }
    
    
    public static void main(String[] args){
		System.err.println("SERVER...");
		if (args.length<1) {
			System.err.println("Missing parameter: port number");	
			System.exit(1);
		}
		int p=0;
		try {
			p= Integer.parseInt( args[0] );
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(2);
		}
		
		
		EchoServer serv= new EchoServer(p);
		serv.servico();   // activa o servico
		}

		
	// activa o servidor no porto indicado em "serverPort"
	public void servico() {
		try {
			ServerSocket s = new ServerSocket(serverPort);

			while(true){
				Socket data = s.accept();

				try {
					InputStream socketIn = data.getInputStream();
					byte[] b = new byte[256];
					int readed = socketIn.read(b,0,256);
					String response= new String(b, 0, readed);
					System.out.println("Received: " + response);

					OutputStream socketOut = data.getOutputStream();
					socketOut.write(response.getBytes());
					
					// BufferedReader breader= new BufferedReader(new InputStreamReader(data.getInputStream()));
					// String msg= breader.readLine();
					// DataOutputStream sout= new DataOutputStream(data.getOutputStream());
					// sout.writeChars(msg);


				} catch (Exception exNoAtendimento) {
					exNoAtendimento.printStackTrace();
				}
				
			}
		}
		catch (Exception exNoAtendimento) {
			exNoAtendimento.printStackTrace();
		}
	}
}
