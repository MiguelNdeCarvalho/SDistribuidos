package sd;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author jsaias
 */
public class TesteAcessoBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        // coloque os argumentos
        if (args.length < 4)
        {
            System.out.println("Arguments: host db user password");
            System.exit(1);
        }

        PostgresConnector pc = new PostgresConnector(args[0], args[1], args[2], args[3]);
        // PostgresConnector pc = new PostgresConnector( ?? );
        // NOTA: não DEVE ter configuracoes no código fonte!!!
        // passar como argumento ou ler de .properties
        
        
        
        // estabelecer a ligacao ao SGBD
        pc.connect();
        Statement stmt = pc.getStatement();

	// *******************
        // update/insert
        try {

            smt.executeUpdate()

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
        }

	// ******************
        // query	
        try {
            
            // AQUI: código para realizar a CONSULTA
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }

        // desligar do SGBD:
        pc.disconnect();
    }


}
