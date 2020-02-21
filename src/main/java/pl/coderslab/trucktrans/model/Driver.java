package pl.coderslab.trucktrans.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.trucktrans.converters.LocalDateConverter;


import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
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
    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @PESEL
    @Column(name = "pesel", unique = true)
    private String pesel;

    @Column(name = "phone")
    private String phone;

    @Future
    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "driving_licence_validity")
    private LocalDate drivingLicenceValidity;

    @PastOrPresent
    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "last_osh")
    private LocalDate lastOSH;

    @Future
    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "next_osh")
    private LocalDate nextOSH;

    @OneToOne
    @JoinColumn(name = "tractor_id",
            unique=true)
    private Tractor tractor;


    @OneToOne
    @JoinColumn(name = "trailer_id",
            unique=true)
    private Trailer trailer;

    @ManyToMany(mappedBy = "drivers")
    List<Order> orders = new ArrayList<>();

    public String getFullName() {
        return  firstName + " " + lastName;
    }

    public String getDriverDetails() {
        return firstName + " " + lastName + ", " +
                tractor.getMake() + " " + tractor.getRegistrationNumber() + " - "
                + trailer.getMake() + " " + trailer.getRegistrationNumber();
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName + '\n';
    }
}
