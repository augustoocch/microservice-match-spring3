package ar.com.augustoocc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "course",
        schema="universityW3")
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso", nullable = false)
    private long idCurso;

    @Column(name = "nombre")
    private String nombre;

    @Column(name= "precio")
    private float precio;

}
