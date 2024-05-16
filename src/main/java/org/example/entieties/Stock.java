package org.example.entieties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long stockId;
    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;
    @Column(name = "qty")
    Integer qty;
}
