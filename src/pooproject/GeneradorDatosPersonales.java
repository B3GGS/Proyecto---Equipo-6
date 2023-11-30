/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pooproject;

import java.util.List;
import java.util.Random;

/**
 *
 * @author ACER
 */
public class GeneradorDatosPersonales {
    private int edad,
                semestre,
                numeroCuenta = 23100000;

    public GeneradorDatosPersonales() {
    }

    public int getSemestre() {
        return semestre;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public GeneradorDatosPersonales(int edad, int semestre, int numeroCuenta) {
        this.edad = edad;
        this.semestre = semestre;
        this.numeroCuenta = numeroCuenta;
    }
    
    public void cargarEdad(List<String> nombres){
        Random rand = new Random();
        for (int i = 0; i < nombres.size(); i++) {
            String[] datos = nombres.get(i).split(" ");
            int semestre = Integer.parseInt(datos[4]);
            
            int edad = (int) (Math.random() * 10) + 18;
            boolean cumple = false;
            while(cumple==false){
                switch(semestre){
                    case 3: 
                        if(edad>=19){cumple=true;break;}
                        else{edad+=1;break;}
                    case 5: 
                        if(edad>=20){cumple=true;break;}
                        else{edad+=1;break;}
                    case 7: 
                        if(edad>=21){cumple=true;break;}
                        else{edad+=1;break;}
                    case 9: 
                        if(edad>=22){cumple=true;break;}
                        else{edad+=1;break;}
                    default: 
                        cumple=true; break;
                }
            }
            nombres.set(i, datos[0]+" "+datos[1]+" "+datos[2]+" "+edad+" "+datos[3]+" "+datos[4]);
        }
    }
        
    public void cargarSemestre(List<String> nombres){
        Random rand = new Random();
        for(int i=0; i < nombres.size(); i++){
            for(String nombre:nombres){
                semestre = (int) (Math.random() * 10) + 1;
                nombres.set(i, nombres.get(i)+" "+semestre);
                i++;
            }
        }
    }
    
    public void cargarNumeroCuenta(List<String> nombres){
        for(int i=0; i < nombres.size(); i++){
            for(String nombre:nombres){
                numeroCuenta++;
                nombres.set(i, nombres.get(i)+" "+numeroCuenta);
                i++;
            }
        }
    }
    
    public void cargarNumTelefono(List<String> nombres){
        for(int i=0; i < nombres.size(); i++){
            String numTelefono = "55";
            String[] datos = nombres.get(i).split(" ");
            numTelefono = numTelefono+datos[4];
            nombres.set(i, nombres.get(i)+" "+numTelefono); 
        }
    }
    
    public void cargarCorreo(List<String> nombres){
        for(int i=0; i < nombres.size(); i++){
            String correo = "@correo.com";
            String[] datos = nombres.get(i).split(" ");
            correo = datos[4]+correo;
            nombres.set(i, nombres.get(i)+" "+correo); 
        }
    }
    
}
