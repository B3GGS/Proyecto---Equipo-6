/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pooproject;

import java.util.List;

/**
 *
 * @author ACER
 */
public class DatosHistorial {
    private List<List<String>> materiasCursadasAlumno;
    private List<Double> promediosGenerales;

    public DatosHistorial() {
    }

    public DatosHistorial(List<List<String>> materiasCursadasAlumno, List<Double> promediosGenerales) {
        this.materiasCursadasAlumno = materiasCursadasAlumno;
        this.promediosGenerales = promediosGenerales;
    }

    public void setMateriasCursadasAlumno(List<List<String>> materiasCursadasAlumno) {
        this.materiasCursadasAlumno = materiasCursadasAlumno;
    }

    public void setPromediosGenerales(List<Double> promediosGenerales) {
        this.promediosGenerales = promediosGenerales;
    }

    public List<List<String>> getMateriasCursadasAlumno() {
        return materiasCursadasAlumno;
    }

    public List<Double> getPromediosGenerales() {
        return promediosGenerales;
    }

}

