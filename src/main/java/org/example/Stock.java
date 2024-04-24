package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long stockId;
    @ManyToOne
    @JoinColumn(name = "item_id")
    Items item;
    @Column(name = "qty")
    int qty;
}
