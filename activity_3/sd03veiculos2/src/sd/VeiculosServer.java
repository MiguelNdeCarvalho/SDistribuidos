package activity_3.sd03veiculos2.src.sd;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.List;

public class VeiculosServer {
	private int serverPort;

	private List<Registo> repositorio;

	public VeiculosServer(int p) {
		serverPort= p;		
		repositorio= new LinkedList<Registo>();  // INICIALIZE com LinkedList
    }

	public static void main(String[] args) {
		System.err.println("SERVER...");
		if (args.length < 1) {
			System.err.println("Missing parameter: port number");
			System.exit(1);
		}
		int p = 0;
		try {
			p = Integer.parseInt(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(2);
		}

		VeiculosServer serv = new VeiculosServer(p);
		serv.servico(); // activa o servico
	}

	public void servico() {

	try {

	    // inicializa o socket para receber ligacoes
		ServerSocket s = new ServerSocket(serverPort);

		while (true) {
			// espera uma ligacao
			Socket clientSock = s.accept();
			// ... accept()
			
			try {
				Object objPedido= null;
				// le os dados do pedido, como um OBJECTO "objPedido"		

				ObjectInputStream ois = new ObjectInputStream(clientSock.getInputStream());  
				objPedido = ois.readObject();
				

				ObjectOutputStream oos = new ObjectOutputStream(clientSock.getOutputStream());

				// apreciar o objecto com o pedido recebido:
				if (objPedido==null)
					System.err.println("PEDIDO NULL: esqueceu-se de alguma coisa");
				
				if (objPedido instanceof PedidoDeConsulta) {
					PedidoDeConsulta pc= (PedidoDeConsulta)objPedido;
				
					Registo reg = null;
					// procurar o registo associado a matricula pretendida
						for(Registo r: repositorio){
							if(r.getMatricula().equals(pc.getMatricula())){
								reg = r;
							}
						}
					// pesquisar no servidor (List, mais tarde num ficheiro)

					
					// enviar objecto Cliente via socket		    
					// se encontra devolve o registo, se não, devolve um novo objecto ou string a representar que nao encontrou
					if(reg!= null) {
						oos.writeObject(reg);
					} else {
						oos.writeObject("Não encontrado");
					}
				}
				else if (objPedido instanceof PedidoDeRegisto) {
					PedidoDeRegisto pr= (PedidoDeRegisto)objPedido;
					Registo registo = pr.getRegisto();

					// ver se ja existia registo desta matricula
					boolean exists = false;
					for(Registo r1: repositorio) {
						if(r1.getMatricula().equals(registo.getMatricula())) {
							exists = true;
							break;
						}
					}
				
					//repositorio.add(registo);
					if(!exists) {
						// adicionar ao rep local (se nao e' uma repeticao)
						//responder ao cliente
						repositorio.add(registo);
						oos.writeObject("Ok, irá ser adicionada.");
						
					} else { //Já tinhamos o registo
						// responder ao cliente
						oos.writeObject("Ok mas já existe essa matricula");
						//deveria-mos substituir na mesma no repositorio
					}


				}
				else
				System.out.println("PROBLEMA");
				
					}
					catch (Exception exNoAtendimento) {
						exNoAtendimento.printStackTrace();
					}
					finally { 
						// fechar o socket de dados dedicado a este cliente:
						try {
							clientSock.close();
						}
						catch (Exception e002) {
						}
					}
					
			
			
			
	    }  // ... ciclo de atendimento
	
	}
	catch (Exception problemaBindAccept) {
	    problemaBindAccept.printStackTrace();
	}

    }

}
