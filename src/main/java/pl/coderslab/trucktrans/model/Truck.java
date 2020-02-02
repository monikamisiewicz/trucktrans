package pl.coderslab.trucktrans.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trucks")
public class Truck {

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

//    @ManyToOne
//    private Driver driver;

    public String getTruckDetails() {
        return make + " " + model + " " + registrationNumber;
    }




}
