package pl.coderslab.trucktrans.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.NIP;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NIP
    @Column(name = "nip", unique = true)
    private String nip;

    @Column(name = "street")
    private String street;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "post_code")
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}")
    private String postCode;

    @Column(name = "place")
    private String place;

    @Size(min = 26, max = 26)
    @Column(name = "iban")
    private String iban;

    @Column(name = "bank")
    private String bank;

}
