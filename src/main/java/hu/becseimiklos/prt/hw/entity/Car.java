package hu.becseimiklos.prt.hw.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "CAR_SEQ")
    private Long id;

    @Column(unique = true)
    private String licensePlateNumber;
    private String brand;
    private String color;
    private Boolean hasParkingPass;
}
