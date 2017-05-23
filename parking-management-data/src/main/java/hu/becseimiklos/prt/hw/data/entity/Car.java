package hu.becseimiklos.prt.hw.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Car implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String licensePlateNumber;
}
