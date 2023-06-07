package ar.com.augustoocc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="orders",
        schema = "universityW3")
@NoArgsConstructor
@AllArgsConstructor
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orders", nullable = false)
    private long id;

    @ManyToOne(targetEntity = Course.class)
    @JoinColumn(name = "curso_id", referencedColumnName = "id_curso")
    private Course courseId;

    private String state;
}
