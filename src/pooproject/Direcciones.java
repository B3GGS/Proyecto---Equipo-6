/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pooproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ACER
 */
public class Direcciones {
    
    public void direcciones(List<String> nombres){
        String archivoCSV = "Direcciones.csv";

        try ( // Crea un lector de archivo y un buffer para leer el archivo CSV
                FileReader fileReader = new FileReader(archivoCSV)) {
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                for(int i=0; i < nombres.size(); i++){
                    String linea = bufferedReader.readLine();
                    String direccion = linea.replaceAll("\\s", "");
                    nombres.set(i, nombres.get(i)+" "+direccion); 
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cargarPais(List<String> nombres){
        for(int i=0; i < nombres.size(); i++){
            String pais = "Mexico";
            String[] datos = nombres.get(i).split(" ");
            nombres.set(i, nombres.get(i)+" "+pais); 
        }
    }
}

