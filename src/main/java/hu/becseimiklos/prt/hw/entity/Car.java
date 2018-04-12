package hu.becseimiklos.prt.hw.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@Table(name = "CAR")
public class Car implements Serializable {

    private static final long serialVersionUID = -5147165640225738026L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "LICENSE_PLATE_NUMBER", unique = true)
    private String licensePlateNumber;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "HAS_PARKING_PASS")
    private Boolean hasParkingPass;

}
