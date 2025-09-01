package com.refactoring.piggybank.membermanagement.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile implements Serializable {
    @Column(name = "goal")
    private String goal;

    @Column(name = "`limit`")
    private String limit;
}