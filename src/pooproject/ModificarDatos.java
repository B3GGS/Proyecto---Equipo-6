/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pooproject;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class ModificarDatos {
    Scanner console = new Scanner(System.in);
    String[] datos;
    
    public void modificarDireccion(List<String> nombres, int clave){
        int indice = clave-23100000-1;
        String dato = nombres.get(indice);

        System.out.println("\nIngresa tu Calle: ");
        String calle = console.nextLine();
        System.out.println("Ingresa tu Numero: ");
        String numero = console.nextLine();
        System.out.println("Ingresa tu CP: ");
        String cp = console.nextLine();
        System.out.println("Ingresa tu colonia: ");
        String colonia = console.nextLine();
        
        String[] datos = dato.split(" ");
        StringBuilder nuevaDireccion = new StringBuilder();

        for (int i = 0; i < datos.length; i++) {
            if (i >= 8) {
                if (i == 8) {
                    nuevaDireccion.append(","+calle).append(",").append(numero).append(",").append(cp).append(",").append(colonia);
                } else {
                    nuevaDireccion.append(" ").append(datos[i]);
                }
            } else {
                nuevaDireccion.append(" ").append(datos[i]);
            }
        }

        nombres.set(indice, nuevaDireccion.toString().trim());

}
    
    public void modificarNumTelefono(List<String> nombres, int clave){
        int indice = clave-23100000-1;
        String dato = nombres.get(indice);

        System.out.println("\nIngresa tu Telefono: ");
        String telefono = console.nextLine();

        
        String[] datos = dato.split(" ");
        StringBuilder nuevoTelefono = new StringBuilder();

        for (int i = 0; i < datos.length; i++) {
            if (i == 6) { // Verifica la sexta posición (índice 5)
                nuevoTelefono.append(",").append(telefono);
            } else {
                nuevoTelefono.append(" ").append(datos[i]);
            }
        }

        nombres.set(indice, nuevoTelefono.toString().trim());
    }
    
    public void modificarCorrero(List<String> nombres, int clave){
        int indice = clave-23100000-1;
        String dato = nombres.get(indice);

        System.out.println("\nIngresa tu Correo: ");
        String correo = console.nextLine();

        
        String[] datos = dato.split(" ");
        StringBuilder nuevoCorreo = new StringBuilder();

        for (int i = 0; i < datos.length; i++) {
            if (i == 7) { // Verifica la sexta posición (índice 5)
                nuevoCorreo.append(",").append(correo);
            } else {
                nuevoCorreo.append(" ").append(datos[i]);
            }
        }

        nombres.set(indice, nuevoCorreo.toString().trim());
    }
}
