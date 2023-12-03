package io.demo.facilitiesreservation.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "id")
    private UUID id;

    // Foreign Key 1 - facility ID

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "facility_id", referencedColumnName = "id")
    private Facility facility;

    // Foreign Key 2 - reservation ID

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "check_in", nullable = false)
    private String checkIn;

    @Column(name = "check_out", nullable = false)
    private String checkOut;

    @Min(1)
    @Max(20)
    @Column(name = "noOfGuest", nullable = false)
    private Integer guests;

    @Column
    private boolean status;

    public Reservation() {
        this.id = UUID.randomUUID();
    }

}
