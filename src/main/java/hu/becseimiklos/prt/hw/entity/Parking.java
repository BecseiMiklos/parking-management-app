package hu.becseimiklos.prt.hw.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "PARKING")
public class Parking implements Serializable {

    private static final long serialVersionUID = 8548776286630504423L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ENTER_TIME")
    private LocalDateTime enterTime;

    @Column(name = "EXIT_TIME")
    private LocalDateTime exitTime;

    @Column(name = "PAID_COST")
    private Integer paidCost;

    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;

}
