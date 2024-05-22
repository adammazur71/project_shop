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
@Table(name = "Stock")
// TODO: JAVA DOC
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long stockId;
    @Column(name = "item_id")
    Long itemId;
    @Column(name = "qty")
    Integer qty;
}
