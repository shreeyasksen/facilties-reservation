package io.demo.facilitiesreservation.entities;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "id")
    private UUID id;

    @Min(1)
    @NotBlank(message = "Please provide name")
    @Column(name = "name")
    private String name;

    @Email(message = "Please provide valid email")
    private String email;

    @Digits(fraction = 0, integer = 8, message = "Please provide 8 digits contact number")
    @Column(name = "contact_no")
    private String contactNo;

    // Foreign
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Reservation reservation;

    public Customer() {
        this.id = UUID.randomUUID();
    }
}
