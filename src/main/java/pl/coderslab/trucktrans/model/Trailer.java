package pl.coderslab.trucktrans.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.trucktrans.converters.LocalDateConverter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trailers")
public class Trailer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "body")
    private String body;

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

    public String getTrailerDetails() {
        return make + "  " + registrationNumber;
    }
}
