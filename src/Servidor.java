import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        int numeroSecreto = (int)(Math.random() * 101); // 0 to 100
        int intentos = 1;
        try {
            ServerSocket servidor = new ServerSocket(6666);
            System.out.println("Esperando conexiones...");


            Socket cliente = servidor.accept();
            System.out.println("Conectado desde " + cliente.getInetAddress());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            while(true) {
                salida.println("Intenta adivinar el número (0-100):");

                int numUsr = Integer.parseInt(entrada.readLine());

                if (numUsr < numeroSecreto) {
                    salida.println("MAYOR");
                } else if (numUsr > numeroSecreto) {
                    salida.println("MENOR");
                } else {
                    salida.println("Acierto en " + intentos);
                    break;
                }
                intentos++;
            }

            // Cerramos la conexión y el servidor.
            entrada.close();
            salida.close();
            cliente.close();
            servidor.close();


        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}