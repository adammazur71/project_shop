package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Employees")
// TODO: JAVA DOC
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long employeeId;
    @Column(name = "Name")
    String employeeName;

}
