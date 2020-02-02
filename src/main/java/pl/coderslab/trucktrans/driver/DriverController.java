package pl.coderslab.trucktrans.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.trucktrans.model.Driver;
import pl.coderslab.trucktrans.model.Trailer;
import pl.coderslab.trucktrans.model.Truck;
import pl.coderslab.trucktrans.trailer.TrailerRepository;
import pl.coderslab.trucktrans.truck.TruckRepository;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverRepository driverRepository;
    private final TruckRepository truckRepository;
    private final TrailerRepository trailerRepository;

    @GetMapping
    public String add(Model model) {
        model.addAttribute("driver", new Driver());
        return "drivers/add";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Driver driver, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "drivers/add";
        }
        driverRepository.save(driver);
        return "redirect:/drivers/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Optional<Driver> driver = driverRepository.findById(id);
        model.addAttribute("driver", driver.orElseThrow(IllegalArgumentException::new));
        return "drivers/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute @Valid Driver driver, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "drivers/edit";
        }
        driverRepository.save(driver);
        return "redirect:/drivers/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        model.addAttribute("driverId", id);
        return "drivers/delete";
    }

    @GetMapping("/delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action) {
        if (action) {
            driverRepository.deleteById(id);
        }
        return "redirect:/drivers/list";
    }


    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("drivers", driverRepository.findAll());
        return "drivers/list";
    }


    @GetMapping("/first-name")
    public String getByFirstName(@RequestParam("firstName") String firstName, Model model) {
        model.addAttribute("drivers", driverRepository.findByFirstName(firstName));
        return "drivers/list";
    }

    @GetMapping("/last-name")
    public String getByLastName(@RequestParam("lastName") String lastName, Model model) {
        model.addAttribute("drivers", driverRepository.findByLastName(lastName));
        return "drivers/list";
    }

    @GetMapping("/pesel")
    public String getByPesel(@RequestParam("pesel") String pesel, Model model) {
        model.addAttribute("drivers", driverRepository.findByPesel(pesel)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "drivers/list";
    }

    @ModelAttribute("trucks")
    public List<Truck> trucks() {
        return truckRepository.findAll();
    }

    @ModelAttribute("trailers")
    public List<Trailer> trailers() {
        return trailerRepository.findAll();
    }
}

