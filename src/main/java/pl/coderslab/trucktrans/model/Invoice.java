package pl.coderslab.trucktrans.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_number", unique = true)
    private String invoiceNumber;

    @Column(name = "invoice_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate invoiceDate;

    @Column(name = "service_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate serviceDate;

    @Column(name = "place")
    private String place;

    @Range(min = 0)
    @Column(name = "days")
    private Integer days;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "serviceDescription")
    private String serviceDescription;

    @Column(name = "unit")
    private String unit;

    @Range(min = 0)
    @Column(name = "quantity", precision = 6, scale = 2)
    private Double quantity;//BigDecimal

    @Range(min = 0)
    @Column(name = "unit_price", precision = 10, scale = 2)
    private Double unitPrice;

    @Column(name = "vat_rate")
    private Integer vatRateInPercent;

//    //jak kalkulować datę?
//    @Formula(value = "invoice_date + days")
//    private LocalDate paymentDate;
//
//    @Formula(value = "quantity * unitPrice")
//    private Double netAmount;
//
//    @Formula(value = "netAmount * vat_rate/100")
//    private Double vatAmount;
//
//    @Formula(value = "netAmount + vatAmount")
//    private Double grossAmount;

    @Size(max = 600)
    @Column(name = "annotations")
    private String annotations;

    @ManyToOne
    private Contractor contractor;

    @ManyToOne
    private Company company;

    @Transient
    public LocalDate getPaymentDate() {
        return invoiceDate.plusDays(this.days);
    }

    public Double getNetAmount() {
        return quantity * unitPrice;
    }

    public Double getVatAmount() {
        return getNetAmount() * vatRateInPercent;
    }

    public Double grossAmount() {
        return getNetAmount() + getVatAmount();
    }


}
