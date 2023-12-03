package io.demo.facilitiesreservation.entities;

import io.demo.facilitiesreservation.entities.types.ValidTypesOfFaciltiesEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@Table(name = "facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    private Reservation reservation;

    @NotBlank(message = "Facility name is COMPULSORY")
    @Size(min = 3, max = 40, message = "Name must be at least 3 characters.")
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Column(name = "facility_type")
    private ValidTypesOfFaciltiesEnum type;

    @NotBlank(message = "Description is mandatory")
    @Column(name = "description")
    private String description;

    @Column(name = "available_from")
    private String availableFrom;

    @Column(name = "available_to")
    private String availableTo;

    @Column(nullable = false)
    private boolean status;

}
