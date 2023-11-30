/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pooproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Equipo6
 */
public class POOProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
    }
    
    //Menu
    public static void menu(){
        int opcion = 0;
        DatosHistorial datos = new DatosHistorial();
        List<String> nombresAlumnos = generarNombres();
        generarDatosPersonales(nombresAlumnos);
        do{
            System.out.println("***** MENU DE OPCIONES *****\n"
                             + "1.- Datos Personales\n"
                             + "2.- Registros Academicos\n"
                             + "3.- Salir.");
            Scanner console = new Scanner(System.in);
            System.out.println("Opcion: ");
            opcion = Integer.parseInt(console.nextLine());
            switch(opcion){
                case 1: 
                    System.out.println("\n1.- Obtener Archivo de Datos Personales del Alumno"
                                     + "\n2.- Modificar Datos del Alumno");
                    System.out.println("Opcion: ");
                    opcion = Integer.parseInt(console.nextLine());
                    switch(opcion){
                        case 1: 
                            guardarDatos(nombresAlumnos);
                            break;
                        case 2: 
                            System.out.println("\n1.- Modificar Direccion"
                                             + "\n2.- Modificar Correo"
                                             + "\n3.- Modificar Telefono");
                            System.out.println("Opcion: ");
                            opcion = Integer.parseInt(console.nextLine());
                            switch(opcion){
                                case 1: 
                                    ModificarDatos manejador = new ModificarDatos();
                                    System.out.println("\nIngresa el numero de cuenta el alumno: ");
                                    int clave = console.nextInt();
                                    manejador.modificarDireccion(nombresAlumnos, clave);
                                    guardarDatos(nombresAlumnos);
                                    break;
                                case 2: 
                                    ModificarDatos controller = new ModificarDatos();
                                    System.out.println("\nIngresa el numero de cuenta el alumno: ");
                                    clave = console.nextInt();
                                    controller.modificarNumTelefono(nombresAlumnos, clave);
                                    guardarDatos(nombresAlumnos);
                                    break;
                                case 3: 
                                    ModificarDatos controlador = new ModificarDatos();
                                    System.out.println("\nIngresa el numero de cuenta el alumno: ");
                                    clave = console.nextInt();
                                    controlador.modificarNumTelefono(nombresAlumnos, clave);
                                    guardarDatos(nombresAlumnos);
                                    break;
                            }
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\n1.- Obtener Archivo de Registros Academicos"
                                     + "\n2.- Obtener Historial Académico de un Alumno");
                    System.out.println("Opcion: ");
                    opcion = Integer.parseInt(console.nextLine());
                    switch(opcion){
                        case 1:
                            registrosAcademicos(nombresAlumnos,datos);
                            break;
                        case 2:
                            System.out.println("Ingresa el numero de cuenta del alumno: ");
                            int clave = console.nextInt();
                            generarHistorial(clave,datos);
                    }
                    break;
                
                case 3: break;
                default: System.out.println("\nOpcion no valida."); break;
            }
        }while(opcion != 3);
            System.out.println("\nGracias por utilizar el programa.");
    }
    
    // Método que guarda en el archivo CSV los datos personales.
    public static void guardarDatos( List<String> datos ){
        
        //Escribe en un TXT los elementos de la lista separados por comas.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DatosPersonales.txt"))) {
            for (int i = 0; i < datos.size(); i++) {
                String elemento = datos.get(i);
                elemento = elemento.replaceAll(" ", ","); // Reemplazar espacios por comas
                writer.write(elemento);

                if (i != datos.size() - 1) {
                    writer.newLine(); // Agregar salto de línea si no es el último elemento
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Convierte el TXT en CSV
        try (BufferedReader reader = new BufferedReader(new FileReader("DatosPersonales.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("DatosPersonales.csv"))) {
            writer.write("Nombre,Apellido Paterno,Apellido Materno,Edad,Numero Cuenta,Semestre,Telefono,Correo,Calle,Numero,CP,Colonia,Pais");
            writer.newLine();
            
            String linea;
            while ((linea = reader.readLine()) != null) {
                writer.write(linea);
                writer.newLine();
            }
            System.out.println("\nDatos Personales guardados exitosamente en: DatosPersonales.csv\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Módulo Generador de Nombres
    public static List<String> generarNombres(){
        String rutaArchivoNombres = "nombres.txt";
        String rutaArchivoApellidos = "apellidos.txt"; 

        GeneradorNombres manejador = new GeneradorNombres();
        manejador.cargarNombres(rutaArchivoNombres);
        manejador.cargarApellidos(rutaArchivoApellidos);

        List<String> listaNombres = manejador.obtenerNombres();
        List<String> listaApellidos = manejador.obtenerApellidos();
        
        List<String> nombresCompletos = manejador.generarNombresCompletos(); 
        
        return nombresCompletos;
    }
    
    //Módulo Generador de Datos Personales
    public static void generarDatosPersonales(List<String> nombres){
        GeneradorDatosPersonales manejador = new GeneradorDatosPersonales();
        manejador.cargarNumeroCuenta(nombres);
        manejador.cargarSemestre(nombres);
        manejador.cargarEdad(nombres);
        manejador.cargarNumTelefono(nombres);
        manejador.cargarCorreo(nombres);
        
        Direcciones controlador = new Direcciones();
        controlador.direcciones(nombres);
        controlador.cargarPais(nombres);
    }
    
    //Modulo Generador de Direcciones
    public static void generarDirecciones(List<String> nombres){
        String rutaArchivoDireccion = "Direcciones.csv";
        
        Direcciones manejador = new Direcciones();

    }
    
    //Modulo de Registros Academicos
    public static void registrosAcademicos(List<String> nombres, DatosHistorial datos){
        RegistrosAcademicos manejador = new RegistrosAcademicos();
        
        //Materias
        manejador.obtenerMaterias();
        List<List<String>> materiasPorSemestre = manejador.materiasPorSemestre();
        List<String> numerosCuenta = manejador.obtenerNumerosCuenta(nombres);
        List<String> semestre = manejador.obtenerSemestre(nombres);
        List<List<String>> materiasInscritas = manejador.obtenerMateriasInscritas(semestre,materiasPorSemestre);
        List<String> materiasAlumnos = manejador.materiasInscritasAlumno(numerosCuenta, materiasInscritas);
        List<Integer> cantidadMaterias = manejador.cantidadMaterias(semestre);

        //Creditos
        List<Integer> creditosSemestre = manejador.creditosPorSemestre(materiasPorSemestre);
        List<Integer> creditosIngreso = manejador.creditosIngreso(semestre,creditosSemestre);
        
        //Promedio
        List<List<String>> materiasCursadasAlumno = manejador.materiasTotalesAlumno(semestre, materiasPorSemestre);
        manejador.asignacionCalificaciones(materiasCursadasAlumno);
        List<List<Double>> promedioSemestral = manejador.calcularPromediosSemestrales(materiasCursadasAlumno);
        List<Double> promediosGenerales = manejador.promedioGeneral(promedioSemestral);
        
        //Numero de Inscripción
        GenerarNumInscripcion controladora = new GenerarNumInscripcion();
        List<Double> escolaridad = controladora.obtenerEscolaridad(cantidadMaterias);
        List<Double> velocidad = controladora.obtenerVelocidad(creditosIngreso);
        List<Double> numerosInscripcion = controladora.obtenerNumInscripcion(promediosGenerales, escolaridad, velocidad);
        controladora.generarArchivo(numerosInscripcion,promediosGenerales,semestre);
        
        //Historial Academico
        datos.setMateriasCursadasAlumno(materiasCursadasAlumno);
        datos.setPromediosGenerales(promediosGenerales);
    }
    
    public static void generarHistorial(int claveAlumno,DatosHistorial datos){
        HistorialAcademico historial = new HistorialAcademico();
        double promedioGeneral = historial.promedioGeneral(datos.getPromediosGenerales(), claveAlumno);
        historial.historialAcademico(datos.getMateriasCursadasAlumno(), claveAlumno,promedioGeneral);
    }
    
}
    
    
    
    
   
