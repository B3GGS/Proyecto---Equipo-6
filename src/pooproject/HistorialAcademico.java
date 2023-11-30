/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pooproject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class HistorialAcademico {
    
    //Generar Archivo Personal.- HistorialAcademico
    public void historialAcademico(List<List<String>> calificacionesAlumno,int claveAlumno, double promedioGeneral){
        int indice = claveAlumno-23100000-1;
        
        if(indice>=0 && indice<calificacionesAlumno.size()){            
            List<String> datos = calificacionesAlumno.get(indice);
            String archivo = "Historial_"+(indice+1+23100000)+".csv";
            int calProm = 0;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))){
                writer.write("Alumno,"+claveAlumno);
                writer.newLine();
                writer.write("Semestre,Clave Materia,Calificacion");
                writer.newLine();
                int i = 1;
                int contador = 0;
                
                for(String dato:datos){
                    dato = i+","+dato.replaceAll(" ", ",");
                    writer.write(dato);
                    writer.newLine();
                    contador++;
                    if(contador%5==0){
                        i++;
                        List<Double> promedioSemestral = promedioSem(calificacionesAlumno, claveAlumno);
                        writer.write("Promedio:,"+promedioSemestral.get(calProm));
                        calProm++;
                        writer.newLine();
                    }
                }
                writer.newLine();
                writer.write("Promedio General:,"+promedioGeneral);
                
                System.out.println("\nHistorial Academico del alumno: "+claveAlumno+" generado existosamente en: "+archivo+"\n");
            }catch(IOException e){
                System.err.println("Error al escribir en el archivo CSV: "+archivo);
            }
        }else{
            System.out.println("Numero de cuenta invalido.");
        }
    }
    
    //Obtener Promedio General
    public double promedioGeneral(List<Double> promediosGenerales, int clave){
        int indice = clave-23100000-1;
        double promedioGeneral = promediosGenerales.get(indice);
        return promedioGeneral;
    }
    
    private List<Double> promedioSem(List<List<String>> calificaciones, int claveAlumno){
        List<Double> promedioSem = new ArrayList<>();
        int indice = claveAlumno-23100000-1;
        List<String> alumno = new ArrayList<>();
        alumno = calificaciones.get(indice);
        double suma = 0;
        int count = 0;
        
        for (String dato : alumno) {
                int calificacion;
                try {
                    calificacion = Integer.parseInt(dato.substring(dato.lastIndexOf(' ') + 1));
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    continue; // Si no hay una calificación numérica válida, pasa a la siguiente materia
                }

                suma += calificacion;
                count++;

                if (count % 5 == 0) { // Cada 5 materias es un semestre
                    double promedio = suma / 5.0;
                    promedioSem.add(promedio);
                    suma = 0.0;
                }
        }
        return promedioSem;
    }
    
}