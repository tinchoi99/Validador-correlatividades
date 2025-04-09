package ar.edu.utn.frba.dds;
import static org.junit.jupiter.api.Assertions.*;

import ar.edu.utn.frba.dds.Dominio.Alumno;
import ar.edu.utn.frba.dds.Dominio.Inscripcion;
import ar.edu.utn.frba.dds.Dominio.Materia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class ValidacionInscripcionesTests {
        private Materia matematica;
        private Materia fisica;
        private Materia quimica;
        private Alumno alumno;
        private List<Materia> materiasAprobadas;

        @BeforeEach
        void setUp() {
                // Crear materias
                matematica = new Materia("Matematica", "M001", new ArrayList<>(), 30);
                fisica = new Materia("Fisica", "F001", new ArrayList<>(), 25);
                quimica = new Materia("Quimica", "Q001", new ArrayList<>(), 20);

                matematica.getTurnosHorarios().add("Lunes 10-12");
                matematica.getTurnosHorarios().add("Martes 10-12");
                fisica.getTurnosHorarios().add("Lunes 10-12");
                fisica.getTurnosHorarios().add("Viernes 10-12");
                quimica.getTurnosHorarios().add("Lunes 14-16");
                quimica.getTurnosHorarios().add("Viernes 14-16");

                // Establecer correlativas para Fisica y Quimica
                fisica.getCorrelativas().add(matematica);
                quimica.getCorrelativas().add(matematica);

                // Crear un alumno con materias aprobadas
                materiasAprobadas = new ArrayList<>();
                materiasAprobadas.add(matematica);
                alumno = new Alumno("A123", "Milton", "Gimenez", materiasAprobadas);
        }

        @Test
        void testCumpleCorrelativas() {
                // Querido Milton tiene aprobada Matemática, por lo que puede inscribirse en Física y Química
                assertTrue(fisica.cumpleCorrelativas(alumno));
                assertTrue(quimica.cumpleCorrelativas(alumno));

                // Fabra no aprobo Matematica, entonces no puede inscribirse en Fisica y Quimica
                Alumno alumno2 = new Alumno("A124", "Frank", "Fabra", new ArrayList<>());
                assertFalse(fisica.cumpleCorrelativas(alumno2));
                assertFalse(quimica.cumpleCorrelativas(alumno2));
        }

        @Test
        void testConflictoDeTurnos() {
                List<Materia> materiasInscripcion = new ArrayList<>();
                materiasInscripcion.add(fisica);
                materiasInscripcion.add(quimica);

                List<String> turnosSeleccionados = new ArrayList<>();
                turnosSeleccionados.add("Lunes 10-12");
                turnosSeleccionados.add("Lunes 10-12");

                Inscripcion inscripcion = new Inscripcion(alumno, materiasInscripcion, turnosSeleccionados);

                // El mismo turno está seleccionado para dos materias
                assertFalse(inscripcion.aprobada());
        }

        @Test
        void testCupoMaximo() {
                // Materia con un cupo máximo de 1
                Materia materiaConCupo = new Materia("Informatica", "I001", new ArrayList<>(), 1);

                // Inscribimos un alumno
                materiaConCupo.inscribirAlumno();

                List<Materia> materiasInscripcion = new ArrayList<>();
                materiasInscripcion.add(materiaConCupo);

                // Intentamos inscribir otro alumno
                Inscripcion inscripcion = new Inscripcion(alumno, materiasInscripcion, new ArrayList<>());

                // Se alcanzó el cupo máximo, La inscripcion falla
                assertFalse(inscripcion.aprobada());
        }

        @Test
        void testInscripcionValida() {
                List<Materia> materiasInscripcion = new ArrayList<>();
                materiasInscripcion.add(fisica);
                materiasInscripcion.add(quimica);

                List<String> turnosSeleccionados = new ArrayList<>();
                turnosSeleccionados.add("Lunes 10-12");
                turnosSeleccionados.add("Viernes 14-16");

                Inscripcion inscripcion = new Inscripcion(alumno, materiasInscripcion, turnosSeleccionados);

                // La inscripción debe ser válida si no hay conflictos
                assertTrue(inscripcion.aprobada());
        }
}
