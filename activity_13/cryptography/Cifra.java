import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class Cifra
{
    public static void main(String[] args) throws Exception, NoSuchPaddingException
    {
        for (Provider provider : Security.getProviders())
        {
            System.out.println(provider);    
        }
        Provider provider = Security.getProviders()[0];
        
        System.out.println(provider.getServices());

        System.out.println(Security.getAlgorithms("Cipher"));
    
        KeyGenerator keygen1 = KeyGenerator.getInstance("AES");
        javax.crypto.SecretKey mySecretKey = keygen1.generateKey();

        Cipher cipher1;
        cipher1 = Cipher.getInstance("AES");
        cipher1.init(Cipher.ENCRYPT_MODE, mySecretKey);
        /* para vários blocos usar-se-ia o método update() (multiple-part encryption)
           até ao penúltimo bloco, seguido de doFinal()
        */

        byte[] plaintext = "Teste123".getBytes();
        byte[] ciphertext1 = cipher1.doFinal( plaintext );  // cifrar num "bloco" só

        // teste rápido para ver o q fica, que não será visível:
        System.out.println( new String(ciphertext1) );

        //agora para desencriptar
        cipher1.init(Cipher.DECRYPT_MODE, mySecretKey);
        byte[] secretMsgBytes= cipher1.doFinal(ciphertext1);

        System.out.println(new String(secretMsgBytes));
    }
}