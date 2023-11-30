/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pooproject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ACER
 */
public class GenerarNumInscripcion {
    
    public List<Double> obtenerEscolaridad(List<Integer> materiasInscritas){
        List<Double> escolaridadAlumnos = new ArrayList<>();

        for(Integer materiasOrdinario:materiasInscritas){
            double escolaridad = (materiasOrdinario/materiasOrdinario)*100;
            escolaridadAlumnos.add(escolaridad);
        }
        return escolaridadAlumnos;
    }

    public List<Double> obtenerVelocidad(List<Integer> creditosIngreso){
        List<Double> velocidadAlumnos = new ArrayList<>();
        for(Integer creditos:creditosIngreso){
            double velocidad = (creditos/creditos)*100;
            velocidadAlumnos.add(velocidad);
        }
        return velocidadAlumnos;
    }
    
    public List<Double> obtenerNumInscripcion(List<Double> promediosGenerales,List<Double> escolaridad,List<Double> velocidad){
        List<Double> numerosInscripcion = new ArrayList<>();
        for (int i = 0; i < promediosGenerales.size(); i++) {
                double numInscripcion = promediosGenerales.get(i) * escolaridad.get(i) * velocidad.get(i);
                numerosInscripcion.add(numInscripcion);
        }
        return numerosInscripcion;
    }
    
    public void generarArchivo(List<Double> numInscripcion,List<Double> promedios,List<String> semestre){
        List<Integer> numOrdenados = new ArrayList<>();
        int j = 1;
        
        for (int i = 0; i < numInscripcion.size(); i++) {
            numOrdenados.add(i); // Llenar la lista de índices con los índices correspondientes a los valores
        }

        // Ordenar los índices según los valores de la lista original
        Collections.sort(numOrdenados, Comparator.comparingDouble(numInscripcion::get).reversed());

        String nombreArchivo = "RegistrosAcademicos.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Numero Cuenta,Carrera,Semestre,Promedio,Numero Inscripcion");
            writer.newLine();
            
            for (int indice : numOrdenados) {
                writer.write(Integer.toString(23100000+indice+1)+",Ingenieria en Computacion,"+semestre.get(indice)+","+promedios.get(indice)+","+j);
                j++;
                writer.newLine(); // Agregar una nueva línea para cada índice
            }
            System.out.println("\nRegistros academicos generados exitosamente en: "+nombreArchivo+"\n");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
