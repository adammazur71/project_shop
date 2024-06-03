package org.shop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sys_log")
public class SysLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "log_date")
    private Date date;
    @Column(name = "logger")
    private String logger;
    @Column(name = "log_level")
    private String logLevel;
    @Column(name = "log_message")
    private String logMessage;
}
