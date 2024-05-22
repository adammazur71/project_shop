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
@Table(name = "Item_type")
// TODO: JAVA DOC
public class ItemType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long itemTypeId;
    @Column(name = "Item_Type_Name")
    String itemTypeName;


}
