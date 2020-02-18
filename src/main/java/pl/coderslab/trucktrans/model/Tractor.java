package pl.coderslab.trucktrans.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.trucktrans.converters.LocalDateConverter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tractors")
public class Tractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Size(min = 2, max = 14)
    @NotNull(message = "Registration number is required")
    @Column(name = "registration_number")
    private String registrationNumber;

    @NotNull
    @Size(min = 17, max = 17)
    @Column(name = "vin", unique = true)
    private String vin;

    @Future
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Convert(converter = LocalDateConverter.class)
    @Column(name = "next_technical_inspection")
    private LocalDate nextTechnicalInspection;

    @Future
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Convert(converter = LocalDateConverter.class)
    @Column(name = "insurance_expires")
    private LocalDate insuranceExpires;

//    @OneToOne
//    @JoinColumn(name = "trailer_id",
//            unique=true)
//    private Driver driver;

    public String getTractorDetails() {
        return make + " " + model + " " + registrationNumber;
    }




}
