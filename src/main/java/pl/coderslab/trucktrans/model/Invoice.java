package pl.coderslab.trucktrans.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.trucktrans.converters.LocalDateConverter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    @Column(name = "invoice_number", unique = true)
    private String invoiceNumber;

    @NotNull
    @Column(name = "invoice_date")
    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate invoiceDate;

    @NotNull
    @Column(name = "service_date")
    @Convert(converter = LocalDateConverter.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate serviceDate;

    @Column(name = "place")
    private String place;

    @NotNull
    @Range(min = 0)
    @Column(name = "days")
    private Integer days;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Size(max = 600)
    @Column(name = "annotations")
    private String annotations;

    public void setPaid(Boolean paid) {
        isPaid = false;
    }

    @Column(name = "is_paid")
    private Boolean isPaid;


    @ManyToOne
    private Contractor contractor;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "invoice")
    private List<Item> items = new ArrayList<>();

    @Transient
    public LocalDate getPaymentDate() {
        return invoiceDate.plusDays(this.days);
    }

    @Transient
    public Double getTotalNetAmount() {
        double totalNetAmount = 0.0;

        for (Item item : items
        ) {
            double netAmount = item.getQuantity() * item.getUnitPrice();
            totalNetAmount += Math.round(netAmount);
        }
        return totalNetAmount;
    }

    @Transient
    public Double getTotalVatAmount() {
        double totalVatAmount = 0.0;

        for (Item item : items
        ) {
            double vatAmount = item.getNetAmount() * item.getVatRateInPercent() * 0.01;
            totalVatAmount += Math.round(vatAmount);
        }
        return totalVatAmount;
    }

    @Transient
    public Double getTotalGrossAmount() {
        double totalGrossAmount = 0.0;

        for (Item item : items
        ) {
            double grossAmount = item.getNetAmount() + item.getVatAmount();
            totalGrossAmount += Math.round(grossAmount);
        }
        return totalGrossAmount;
    }

    @Transient
    public Integer getVatRateInPercent() {
        if(items.isEmpty()) {
            return 0;
        }

        return items.get(0).getVatRateInPercent();
    }

}
