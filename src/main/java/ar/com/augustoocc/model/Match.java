package ar.com.augustoocc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "match",
        schema="intraLink")
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Column(name = "id_usr", nullable = false)
    private long idUsr;

    @Column(name = "like")
    private Set<Long> like;

    @Column(name= "dislike")
    private Set<Long> dislike;

}
