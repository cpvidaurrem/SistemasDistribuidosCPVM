/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosoquets;

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
public class ClienteMayMen {

    public static void main(String[] args) {
        {
            int port = 5002;
            Scanner sc = new Scanner(System.in);

            boolean salir = false;

            try {
                Socket client = new Socket("localhost", port);
                PrintStream toServer = new PrintStream(client.getOutputStream());
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

                while (!salir) {

                    System.out.println("Digite numero 1: ");
                    String num1 = sc.next();
                    System.out.println("Digite numero 2: ");
                    String num2 = sc.next();
                    System.out.println("Digite numero 3: ");
                    String num3 = sc.next();
                    System.out.println("que operacion quiere(mayor-menor): ");
                    String op = sc.next();

                    String cadena = op + "-" + num1 + "-" + num2 + "-" + num3;
                    toServer.println(cadena);

                    if ("salir".equals(num1) || "salir".equals(num2) || "salir".equals(num3) || "salir".equals(op)) {
                        salir = true;
                        System.out.println("Se termino ");
                        client.close();
                    } else {
                        String result = fromServer.readLine();
                        System.out.println("El " + op + " es: " + result);
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(ClienteMayMen.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
