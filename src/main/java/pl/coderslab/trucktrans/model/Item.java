package pl.coderslab.trucktrans.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serviceDescription")
    private String serviceDescription;

    @Column(name = "unit")
    private String unit;

    @NotNull
    @Range(min = 0)
    @Column(name = "quantity", precision = 6, scale = 2)
    private Double quantity;

    @NotNull
    @Range(min = 0)
    @Column(name = "unit_price", precision = 10, scale = 2)
    private Double unitPrice;

    @NotNull
    @Range(min = 0, max = 100)
    @Column(name = "vat_rate")
    private Integer vatRateInPercent;

    @ManyToOne
    private Invoice invoice;

    @Transient
    public Double getNetAmount() {

        return (double) Math.round(quantity * unitPrice);
    }

    @Transient
    public Double getVatAmount() {
        return (double) Math.round(getNetAmount() * vatRateInPercent * 0.01);
    }

    @Transient
    public Double getGrossAmount() {
        return getNetAmount() + getVatAmount();
    }



}
