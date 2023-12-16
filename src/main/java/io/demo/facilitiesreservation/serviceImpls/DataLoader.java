package io.demo.facilitiesreservation.serviceImpls;

import java.util.UUID;

import org.springframework.stereotype.Component;

import io.demo.facilitiesreservation.entities.Customer;
import io.demo.facilitiesreservation.entities.Facility;
import io.demo.facilitiesreservation.entities.Reservation;
import io.demo.facilitiesreservation.entities.types.ValidTypesOfFaciltiesEnum;
import io.demo.facilitiesreservation.repositories.CustomerRepository;
import io.demo.facilitiesreservation.repositories.FacilityRepository;
import io.demo.facilitiesreservation.repositories.ReservationRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataLoader {

        private final CustomerRepository customerRepository;
        private final FacilityRepository facilityRepository;
        private final ReservationRepository reservationRepository;

        // @Autowired
        public DataLoader(CustomerRepository customerRepository, FacilityRepository facilityRepository,
                        ReservationRepository reservationRepository) {
                this.customerRepository = customerRepository;
                this.facilityRepository = facilityRepository;
                this.reservationRepository = reservationRepository;
        }

        @PostConstruct
        public void loadSampleData() {
                // Clear existing data
                customerRepository.deleteAll();
                reservationRepository.deleteAll();
                facilityRepository.deleteAll();

                // Creating sample customers
                Customer c1 = new Customer();

                c1.setName("John Doe");
                c1.setEmail("johndoe@example.com");
                c1.setContactNo("12345678");

                Customer c2 = new Customer();

                c2.setName("Alice Smith");
                c2.setEmail("alice@example.com");
                c2.setContactNo("98765432");

                customerRepository.save(c1);
                customerRepository.save(c2);

                log.info("Loaded Customers: " + customerRepository.count());

                // Creating sample facilities
                Facility f1 = new Facility();
                f1.setName("Gym");
                f1.setType(ValidTypesOfFaciltiesEnum.SPORTS);
                f1.setDescription("HARDCORE Gym");
                f1.setAvailableFrom("2019-01-01");
                f1.setAvailableTo("2019-12-31");
                f1.setStatus(true);

                Facility f2 = new Facility();
                f2.setName("Meeting Room A");
                f2.setType(ValidTypesOfFaciltiesEnum.BUSINESS);
                f2.setDescription("NICE Room");
                f2.setAvailableFrom("2020-01-01");
                f2.setAvailableTo("2020-12-31");
                f2.setStatus(true);

                facilityRepository.save(f1);
                facilityRepository.save(f2);

                log.info("Loaded Facilities: " + facilityRepository.count());

                // Creating sample reservations
                Reservation r1 = new Reservation();
                r1.setId(UUID.randomUUID());
                r1.setFacility(f1);
                r1.setCustomer(c1);
                r1.setCheckIn("2023-01-01");
                r1.setCheckOut("2023-12-31");
                r1.setGuests(4);
                r1.setStatus(true);

                Reservation r2 = new Reservation();
                r2.setId(UUID.randomUUID());
                r2.setFacility(f2);
                r2.setCustomer(c2);
                r2.setCheckIn("2023-01-01");
                r2.setCheckOut("2023-12-31");
                r2.setGuests(3);
                r2.setStatus(true);

                reservationRepository.save(r1);
                reservationRepository.save(r2);

                log.info("Loaded Reservations: " + reservationRepository.count());
        }
}
