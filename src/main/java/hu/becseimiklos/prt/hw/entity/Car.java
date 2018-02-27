package hu.becseimiklos.prt.hw.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
public class Car implements Serializable {

    private static final long serialVersionUID = -5147165640225738026L;

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
