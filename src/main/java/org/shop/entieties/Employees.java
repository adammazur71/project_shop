package org.shop.entieties;

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
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long employeeId;
    @Column(name = "Name")
    String employeeName;

}