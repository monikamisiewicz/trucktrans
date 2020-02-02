package pl.coderslab.trucktrans.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Column(name = "order_number", unique = true)
    private String orderNumber;

    @Column(name = "direction_from")
    private String directionFrom;

    @Column(name = "direction_to")
    private String directionTo;

    @Column(name = "goods")
    private String goods;

    @Range(min = 0)
    @Column(name = "quantity", precision = 6, scale = 2)
    private Double quantity;

    @Column(name = "unit")
    private String unit;

    @Range(min = 0)
    @Column(name = "value", precision = 10, scale = 2)
    private Double value;

    @Size(max = 600)
    @Column(name = "comment")
    private  String comment;


    @ManyToOne
    private Company company;

    @ManyToOne
    private Contractor contractor;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Trailer trailer;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "drivers_orders", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private List<Driver> drivers = new ArrayList<>();

}
