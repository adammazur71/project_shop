package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "Invoice_json")
    String invoiceJSON;
    @Column(name = "Ksef_status")
    int ksefStatus;
    @Column(name = "Sent_status")
    int sentStatus;
    @JoinColumn(name = "Customer_id")
    @ManyToOne
    Customers customer;
    @OneToMany(mappedBy = "Invoice")
    Set<InvoiceItems> invoiceItem;

}
