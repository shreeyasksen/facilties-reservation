package io.demo.facilitiesreservation.entities;

import java.util.Set;

import io.demo.facilitiesreservation.entities.types.ValidTypesOfFaciltiesEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    Set<Reservation> reservation;

    @NotBlank(message = "Facility name is COMPULSORY")
    @Size(min = 3, max = 40, message = "Name must be at least 3 characters.")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Type must not be null")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "facility_type", columnDefinition = "smallint")
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
