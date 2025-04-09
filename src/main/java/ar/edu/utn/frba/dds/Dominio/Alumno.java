package ar.edu.utn.frba.dds.Dominio;

import java.util.List;

public class Alumno {
    private String legajo;
    private String nombre;
    private String apellido;
    private List<Materia> materiasAprobadas;

    public Alumno(String legajo, String nombre, String apellido, List<Materia> materiasAprobadas) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.materiasAprobadas = materiasAprobadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Materia> getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public void setMateriasAprobadas(List<Materia> materiasAprobadas) {
        this.materiasAprobadas = materiasAprobadas;
    }
}
