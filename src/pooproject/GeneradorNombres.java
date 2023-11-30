package pooproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorNombres {
    private List<String> nombres;
    private List<String> apellidos;

    public GeneradorNombres() {
        this.nombres = new ArrayList<>();
        this.apellidos = new ArrayList<>();
    }

    public void cargarNombres(String rutaArchivo) {
        cargarLista(rutaArchivo, nombres);
    }
    public void cargarApellidos(String rutaArchivo) {
        cargarLista(rutaArchivo, apellidos);
    }
    
    private void cargarLista(String rutaArchivo, List<String> lista) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(linea); 
            }
            //System.out.println("Datos cargados exitosamente desde: " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + rutaArchivo + ": " + e.getMessage());
        }
    }

    public List<String> obtenerNombres() {
        return nombres;
    }
    public List<String> obtenerApellidos() {
        return apellidos;
    }
    
    public List<String> generarNombresCompletos() {
        List<String> nombresCompletos = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 1000; i++) {
            String nombre = nombres.get(rand.nextInt(nombres.size()));
            String apellido1 = apellidos.get(rand.nextInt(apellidos.size()));
            String apellido2 = apellidos.get(rand.nextInt(apellidos.size()));

            String nombreCompleto = nombre + " " + apellido1 + " " + apellido2;

            if (!nombresCompletos.contains(nombreCompleto)) {
                nombresCompletos.add(nombreCompleto);
            } else {
                i--; // Repetir la iteración si se genera un nombre completo duplicado
            }
        }
        //System.out.println("Nombres completos generados existosamente.");
        return nombresCompletos;
    }

    
}