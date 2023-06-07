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
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Match", nullable = false)
    private long idMatch;

    @Column(name = "id_usr", nullable = false)
    private long idUsr;

    @Column(name = "like")
    private String nombre;

    @Column(name= "unlike")
    private float precio;

}
