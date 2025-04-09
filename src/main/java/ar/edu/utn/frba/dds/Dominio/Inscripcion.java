package ar.edu.utn.frba.dds.Dominio;

import java.util.List;

public class Inscripcion {
    Alumno alumno;
    List<Materia> materias; // Materias que el alumno desea inscribirse
    List<String> turnosSeleccionados; // Turnos que eligio el alumno
    boolean valida; // Estado de la inscripción

    public Inscripcion(Alumno alumno, List<Materia> materias, List<String> turnosSeleccionados) {
        this.alumno = alumno;
        this.materias = materias;
        this.turnosSeleccionados = turnosSeleccionados;
        this.valida = false;
    }

    public boolean aprobada() {
        // Verifico que el alumno cumpla con las correlativas
        for (Materia materia : materias) {
            if (!materia.cumpleCorrelativas(alumno)) {
                System.out.println("No cumple con las correlativas para la materia: " + materia.getNombre());
                return false;
            }
        }

        // Verifico que no haya conflictos de turnos
        for (Materia materia : materias) {
            for (String turno : turnosSeleccionados) {
                if (materia.getTurnosHorarios().contains(turno)) {
                    if (turnosSeleccionados.indexOf(turno) != turnosSeleccionados.lastIndexOf(turno)) {
                        System.out.println("Conflicto de turno: El turno " + turno + " está seleccionado para más de una materia.");
                        return false;
                    }
                }
            }
        }

        // Verifico que no se supere el cupo máximo de inscripción
        for (Materia materia : materias) {
            if (materia.getInscriptos() >= materia.getCupoMaximo()) {
                System.out.println("La materia " + materia.getNombre() + " ya ha alcanzado el cupo máximo.");
                return false;
            }
        }

        //si pasa todas las verificaciones, se incrementa por materia en 1 la cantidad de incriptos a cada una.
        for (Materia materia : materias){
            materia.inscribirAlumno();
        }

        // Si pasa todas las verificaciones, la inscripción es válida
        valida = true;
        return true;
    }
}
