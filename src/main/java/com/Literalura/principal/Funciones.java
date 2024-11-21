package com.Literalura.principal;

import java.util.Scanner;

public class Funciones {

    static void presionarTecla(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("\t\n Presione cualquier tecla para continuar...");
        try
        {
            teclado.nextLine();
        }
        catch(Exception e)
        {}
    }
}
