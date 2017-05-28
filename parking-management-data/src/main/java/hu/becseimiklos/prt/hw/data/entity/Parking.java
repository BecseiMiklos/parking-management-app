package hu.becseimiklos.prt.hw.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Parking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "PARKING_SEQ")
    private Long id;

    private Date enterTime;
    private Date exitTime;

    @ManyToOne()
    private Car car;
}
