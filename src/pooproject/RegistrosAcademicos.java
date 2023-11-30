/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pooproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ACER
 */
public class RegistrosAcademicos {
    private List<String> registroMaterias;

    public RegistrosAcademicos() {
        registroMaterias = new ArrayList<>();
    }

    public void obtenerMaterias() {
        try (BufferedReader br = new BufferedReader(new FileReader("ListaMaterias.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                registroMaterias.add(linea); // Agrega cada línea a la lista
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<List<String>> materiasPorSemestre() {
    List<List<String>> sublistas = new ArrayList<>();

    int tamañoLineas = registroMaterias.size();
    int totalSublistas = 10; // Calcula el total de sublistas necesarias

    for (int i = 0; i < totalSublistas; i++) {
        int inicio = i * 5;
        int fin = Math.min((i + 1) * 5, tamañoLineas); // Ajusta el límite superior

        List<String> sublista = new ArrayList<>(registroMaterias.subList(inicio, fin));
        sublistas.add(sublista);
    }
    return sublistas;
    }

    public List<String> obtenerNumerosCuenta(List<String> datos) {
        List<String> numerosCuenta = new ArrayList<>();

        for (String dato : datos) {
            String[] partes = dato.split(" ");
            if (partes.length >= 6) {
                String numeroCuenta = partes[4];
                numerosCuenta.add(numeroCuenta);
            }
        }
        return numerosCuenta;
    }

    public List<String> obtenerSemestre(List<String> datos){
        List<String> semestre = new ArrayList<>();

        for (String dato : datos) {
            String[] partes = dato.split(" ");
            if (partes.length >= 6) {
                String numeroCuenta = partes[5];
                semestre.add(numeroCuenta);
            }
        }
        return semestre;
    }

    public List<List<String>> obtenerMateriasInscritas(List<String> semestre, List<List<String>> materiasPorSemestre){
        List<List<String>> materiasAlumnos = new ArrayList<>();
        List<Integer> listaSemestres = new ArrayList<>();

        for (String dato : semestre) {
            try {
                int entero = Integer.parseInt(dato);
                listaSemestres.add(entero);
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir la cadena a entero: " + e.getMessage());
            }
        }
        for (Integer valor:listaSemestres) {
            List<String> prueba = materiasPorSemestre.get(valor-1);
            materiasAlumnos.add(prueba);
        }
        return materiasAlumnos;
    }

    public List<String> materiasInscritasAlumno(List<String> numeroCuenta, List<List<String>> materiasInscritas){
        List<String> alumnosMaterias = new ArrayList<>();

        if (numeroCuenta.size() != materiasInscritas.size()) {
            throw new IllegalArgumentException("Las listas no tienen la misma longitud");
        }

        // Concatenar elemento a elemento las listas
        for (int i = 0; i < numeroCuenta.size(); i++) {
            String cadena = numeroCuenta.get(i);
            List<String> lista = materiasInscritas.get(i);

            StringBuilder sb = new StringBuilder(cadena);
            for (String elemento : lista) {
                sb.append(" ").append(elemento);
            }

            alumnosMaterias.add(sb.toString());
        }

        return alumnosMaterias;
    }

    public List<Integer> cantidadMaterias(List<String> semestre){
        List<Integer> cantidadMaterias = new ArrayList<>();

        for (String materias : semestre) {
            int totalMaterias = 0;
            try {
                int semestreAlumno = Integer.parseInt(materias);
                    totalMaterias = semestreAlumno*5;
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir la cantidad de créditos: " + e.getMessage());
            }
            cantidadMaterias.add(totalMaterias);
        }
        return cantidadMaterias;
    }

    //CREDITOS
    public List<Integer> creditosPorSemestre(List<List<String>> materiasPorSemestre) {
        List<Integer> creditosPorSemestre = new ArrayList<>();

        for (List<String> materiasSemestre : materiasPorSemestre) {
            int totalCreditosSemestre = 0;
            for (String materia : materiasSemestre) {
                String[] partes = materia.split(", ");
                for (String parte : partes) {
                    String[] infoMateria = parte.split(" ");
                    if (infoMateria.length > 1) {
                        try {
                            int creditos = Integer.parseInt(infoMateria[1]);
                            totalCreditosSemestre += creditos;
                        } catch (NumberFormatException e) {
                            System.err.println("Error al convertir la cantidad de créditos: " + e.getMessage());
                        }
                    }
                }
            }

            creditosPorSemestre.add(totalCreditosSemestre);
        }

        return creditosPorSemestre;
    }

    public List<Integer> creditosIngreso(List<String> semestre, List<Integer> creditosPorSemestre){
        List<Integer> creditosIngreso = new ArrayList<>();
        int totalCreditosIngreso = 0;

        for (String semestreAlumno : semestre) {
            int semestreActual;
            // Convertir la cadena del semestre a un entero
            try {
                semestreActual = Integer.parseInt(semestreAlumno);
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir el semestre a entero: " + e.getMessage());
                continue; // Pasar al siguiente alumno si hay un error en la conversión
            }
            int creditos = 0;
            for (int j = 0; j < semestreActual; j++) {
                creditos += creditosPorSemestre.get(j);
            }
            totalCreditosIngreso = creditos;
            creditosIngreso.add(totalCreditosIngreso);
        }
        return creditosIngreso;
    }

    //PROMEDIO
    public List<List<String>> materiasTotalesAlumno(List<String> semestre, List<List<String>> materiasPorSemestre){
        List<List<String>> materiasAcumuladasPorAlumno = new ArrayList<>();
        
       for(String dato:semestre){
            try{
                int semestreActual = Integer.parseInt(dato);
                List<String> materiasHastaSemestre = new ArrayList<>();
                for (int j = 0; j < semestreActual; j++) {
                    List<String> materiasSemestre = materiasPorSemestre.get(j);
                    for(String clave:materiasSemestre){
                        String[] parts = clave.split(" ");
                        materiasHastaSemestre.add(parts[0]);
                    }
                }
                materiasAcumuladasPorAlumno.add(materiasHastaSemestre);
            }catch (NumberFormatException e) {
                System.err.println("Error al convertir el semestre a entero: " + e.getMessage());
            }
       }
        return materiasAcumuladasPorAlumno;
    }
    
    public void asignacionCalificaciones (List<List<String>> materiasCursadas){
        Random rand = new Random();

        for (List<String> materiasAlumno : materiasCursadas) {
            for (int i = 0; i < materiasAlumno.size(); i++) {
                int calificacion = rand.nextInt(5) + 6; // Generar un número aleatorio entre 6 y 10
                materiasAlumno.set(i, materiasAlumno.get(i) +" "+ calificacion);
            }
        }
    }
    
    public List<List<Double>> calcularPromediosSemestrales(List<List<String>> materiasAcumuladasPorAlumno) {
        List<List<Double>> promediosPorSemestre = new ArrayList<>();

        for (List<String> materiasAlumno : materiasAcumuladasPorAlumno) {
            List<Double> promediosAlumno = new ArrayList<>();
            double sum = 0.0;
            int count = 0;

            for (String materia : materiasAlumno) {
                int calificacion;
                try {
                    calificacion = Integer.parseInt(materia.substring(materia.lastIndexOf(' ') + 1));
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    continue; // Si no hay una calificación numérica válida, pasa a la siguiente materia
                }

                sum += calificacion;
                count++;

                if (count % 5 == 0) { // Cada 5 materias es un semestre
                    double promedio = sum / 5.0;
                    promediosAlumno.add(promedio);
                    sum = 0.0;
                }
            }
            promediosPorSemestre.add(promediosAlumno);
        }

        return promediosPorSemestre;
    }

    public List<Double> promedioGeneral(List<List<Double>> promedioSemestres){
        List<Double> promediosGenerales = new ArrayList<>();

        for (List<Double> promediosAlumno : promedioSemestres) {
            double suma = 0.0;
            for (Double promedio:promediosAlumno) {
                suma += promedio;
            }
            double promedioGeneral = suma / promediosAlumno.size();
            promediosGenerales.add(promedioGeneral);
        }
        return promediosGenerales;
    }

}
