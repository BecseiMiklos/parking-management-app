package hu.becseimiklos.prt.hw.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Parking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "PARKING_SEQ")
    private Long id;

    private LocalDateTime enterTime;
    private LocalDateTime exitTime;
    private Integer paidCost;

    @ManyToOne()
    private Car car;
}
