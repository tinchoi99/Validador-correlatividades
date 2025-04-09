package ar.edu.utn.frba.dds.Dominio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Materia {
    private String nombre;
    private String codigo;
    private Set<String> turnosHorarios; // Set de turnos disponibles para la materia, para evitar duplicados
    private List<Materia> correlativas;
    private int cupoMaximo;
    private int inscriptos;

    public Materia(String nombre, String codigo, List<Materia> correlativas, int cupoMaximo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.turnosHorarios = new HashSet<>();
        this.correlativas = correlativas;
        this.cupoMaximo = cupoMaximo;
        this.inscriptos = 0;
    }

    // Método para verificar si el alumno cumple con las correlativas de la materia
    public boolean cumpleCorrelativas(Alumno alumno) {
        List<Materia> materiasAprobadasAlumno = alumno.getMateriasAprobadas();
        for (Materia correlativa : correlativas) {
            if (!materiasAprobadasAlumno.contains(correlativa)) {
                return false;
            }
        }
        return true;
    }

    public void inscribirAlumno(){
        this.inscriptos++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<String> getTurnosHorarios() {
        return turnosHorarios;
    }

    public void setTurnosHorarios(Set<String> turnosHorarios) {
        this.turnosHorarios = turnosHorarios;
    }

    public List<Materia> getCorrelativas() {
        return correlativas;
    }

    public void setCorrelativas(List<Materia> correlativas) {
        this.correlativas = correlativas;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getInscriptos() {
        return inscriptos;
    }
}
