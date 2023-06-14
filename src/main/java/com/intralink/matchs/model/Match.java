package com.intralink.matchs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    private long idUsr;

    private String like;

    private Set<Long> dislike;

}
