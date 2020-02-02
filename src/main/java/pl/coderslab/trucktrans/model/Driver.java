package pl.coderslab.trucktrans.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name")
    private String lastName;

    @Past
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @PESEL
    @Column(name = "pesel", unique = true)
    private String pesel;

    @Column(name = "id_number", unique = true)
    private String idNumber;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Trailer trailer;

    @ManyToMany(mappedBy = "drivers")
    List<Order> orders = new ArrayList<>();

    public String getFullName() {
        return  firstName + " " + lastName;
    }

    public String getDriverDetails() {
        return firstName + " " + lastName + ", " +
                truck.getMake() + " " + truck.getRegistrationNumber() + " - "
                + trailer.getMake() + " " + trailer.getRegistrationNumber();
    }

}
