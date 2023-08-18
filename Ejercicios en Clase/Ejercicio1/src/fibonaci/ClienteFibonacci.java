/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fibonaci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class ClienteFibonacci {
    public static void main(String[] args) {
        {
            int port = 5002;
            Scanner sc = new Scanner(System.in);
            
            try {
                Socket client = new Socket("localhost", port);
                PrintStream toServer = new PrintStream(client.getOutputStream());
                BufferedReader fromServer = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));
                System.out.println("Introduzca un numero: ");
                String num = sc.next();
                toServer.println(num);
                String result = fromServer.readLine();
                System.out.println("El fibonacci de " + num + " es: " + result);

            } catch (IOException ex) {
                Logger.getLogger(ClienteFibonacci.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
