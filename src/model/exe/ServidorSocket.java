
package model.exe;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Conversao;

/**
 *
 * @author Pedro Citadin Coelho
 */
public class ServidorSocket {
    public static void main(String[] args) {
        try {
            Conversao conv;
            ServerSocket servidor = new ServerSocket(3334);
            System.out.println("Servidor iniciado na porta 3334");
            
            Socket cliente = servidor.accept();
            System.out.println("Cliente do ip "+ cliente.getInetAddress().getHostAddress());
            Scanner entrada  = new Scanner(cliente.getInputStream());
            while(entrada.hasNextLine()){
                conv = new Conversao(entrada.nextLine());
                Float valor = conv.converte(conv);
                PrintStream saida = new PrintStream(cliente.getOutputStream());
                saida.println(valor);
                
            }
            entrada.close();
            servidor.close();
            
        } catch (IOException ex) {
            System.out.println("Erro: "+ex.getMessage());
        }
    }
}
