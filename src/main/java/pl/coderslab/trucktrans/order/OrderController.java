package pl.coderslab.trucktrans.order;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.trucktrans.company.CompanyRepository;
import pl.coderslab.trucktrans.contractor.ContractorRepository;
import pl.coderslab.trucktrans.converters.LocalDateConverter;
import pl.coderslab.trucktrans.driver.DriverRepository;
import pl.coderslab.trucktrans.model.*;
import pl.coderslab.trucktrans.trailer.TrailerRepository;
import pl.coderslab.trucktrans.tractor.TractorRepository;

import javax.persistence.Convert;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final TractorRepository tractorRepository;
    private final TrailerRepository trailerRepository;

    @GetMapping
    public String add(Model model) {
        model.addAttribute("order", new Order());
        return "orders/add";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "orders/add";
        }
        orderRepository.save(order);
        return "redirect:/orders/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Optional<Order> order = orderRepository.findById(id);
        model.addAttribute("order", order.orElseThrow(IllegalArgumentException::new));
        return "orders/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("order") @Valid Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "orders/edit";
        }
        orderRepository.save(order);
        return "redirect:/orders/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        model.addAttribute("orderId", id);
        return "orders/delete";
    }

    @GetMapping("/delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action) {
        if (action) {
            orderRepository.deleteById(id);
        }
        return "redirect:/orders/list";
    }


    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders/list";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable long id, Model model) {
        Optional<Order> order = orderRepository.findById(id);
        model.addAttribute("order", order.orElseThrow(IllegalArgumentException::new));
        return "orders/details";
    }

    @GetMapping("/contractor")
    public String getByContractor(@RequestParam("contractor") long contractorId, Model model) {
        if (contractorId != -1) {
            Contractor contractor = contractorRepository.findById(contractorId).orElseThrow(IllegalAccessError::new);
            model.addAttribute("orders", orderRepository.findByContractor(contractor));
        } else {
            model.addAttribute("orders", Collections.emptyList());
        }
        return "orders/list";
    }

    @GetMapping("/truck")
    public String getByTruck(@RequestParam("tractor") long truckId, Model model) {
        if (truckId != -1) {
            Tractor tractor = tractorRepository.findById(truckId).orElseThrow(IllegalAccessError::new);
            model.addAttribute("orders", orderRepository.findByTractor(tractor));
        } else {
            model.addAttribute("orders", Collections.emptyList());
        }
        return "orders/list";
    }

    @GetMapping("/trailer")
    public String getByTrailer(@RequestParam("trailer") long trailerId, Model model) {
        if (trailerId != -1) {
            Trailer trailer = trailerRepository.findById(trailerId).orElseThrow(IllegalAccessError::new);
            model.addAttribute("orders", orderRepository.findByTrailer(trailer));
        } else {
            model.addAttribute("orders", Collections.emptyList());
        }
        return "orders/list";
    }


    @GetMapping("/driver")
    public String getByDriver(@RequestParam("driver") long driverId, Model model) {
        if (driverId != -1) {
            Driver driver = driverRepository.findById(driverId).orElseThrow(IllegalAccessError::new);
            model.addAttribute("orders", orderRepository.findByDrivers(driver));
        } else {
            model.addAttribute("orders", Collections.emptyList());
        }
        return "orders/list";
    }


    @GetMapping("/order-number")
    public String getByOrderNumber(@RequestParam("orderNumber") String orderNumber, Model model) {
        model.addAttribute("orders", orderRepository.findByOrderNumber(orderNumber)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "orders/list";
    }

    @GetMapping("/date")
    public String getByDate(@RequestParam("date") LocalDate date, Model model) {
        model.addAttribute("orders", orderRepository.findByDate(date)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "orders/list";
    }

//    @GetMapping("/date-range")
//    public String getByDateRange(@RequestParam("start")LocalDate start, @RequestParam("end") LocalDate end, Model model) {
//        model.addAttribute("orders", orderRepository.findOrderByDateBetween(start, end));
//        return "orders/list";
//    }


    @GetMapping("/date-range")
    public String getByDateRange(@RequestParam("start") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate start,
                                 @RequestParam("end") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate end, Model model) {
        model.addAttribute("orders", orderRepository.findOrderByDateBetween(start, end));
        return "orders/list";
    }


    @ModelAttribute("company")
    public List<Company> companyData() {
        return companyRepository.findAll();
    }

    @ModelAttribute("contractors")
    public List<Contractor> contractors() {
        return contractorRepository.findAll();
    }

    @ModelAttribute("trucks")
    public List<Tractor> trucks() {
        return tractorRepository.findAll();
    }

    @ModelAttribute("trailers")
    public List<Trailer> trailers() {
        return trailerRepository.findAll();
    }

    @ModelAttribute("drivers")
    public List<Driver> drivers() {
        return driverRepository.findAll();
    }


}
