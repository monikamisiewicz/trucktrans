package pl.coderslab.trucktrans.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.trucktrans.model.Driver;
import pl.coderslab.trucktrans.model.Trailer;
import pl.coderslab.trucktrans.model.Tractor;
import pl.coderslab.trucktrans.trailer.TrailerRepository;
import pl.coderslab.trucktrans.tractor.TractorRepository;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverRepository driverRepository;
    private final TractorRepository tractorRepository;
    private final TrailerRepository trailerRepository;

    @GetMapping
    public String add(Model model) {
        model.addAttribute("driver", new Driver());
        return "drivers/add";
    }

    @PostMapping
    public String save(@ModelAttribute("driver") @Valid Driver driver, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "drivers/add";
        }
        if(driver.getTractor().getId()==null){
            driver.setTractor(null);
        }
        if(driver.getTrailer().getId()==null){
            driver.setTrailer(null);
        }
        driverRepository.save(driver);
        return "redirect:/drivers/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Optional<Driver> driver = driverRepository.findById(id);

        model.addAttribute("driver", driver.orElseThrow(IllegalArgumentException::new));
        List<Trailer> trailers = trailers();
        trailers.add(driver.get().getTrailer());
        model.addAttribute("driverTrailers", trailers);
        List<Tractor> tractors = tractors();
        tractors.add(driver.get().getTractor());
        model.addAttribute("driverTractors", tractors);
        return "drivers/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("driver") @Valid Driver driver, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "drivers/edit";
        }

        if(driver.getTractor().getId()==null){
            driver.setTractor(null);
        }
        if(driver.getTrailer().getId()==null){
            driver.setTrailer(null);
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

    @ModelAttribute("tractors")
    public List<Tractor> tractors() {
        List<Tractor> tr = driverRepository.findAll().stream()
                .map(driver -> driver.getTractor())
                .collect(Collectors.toList());

        List<Tractor> tractors = tractorRepository.findAll();
        List<Tractor> filteredTractors = tractors.stream()
                .filter(tractor -> !tr.contains(tractor))
                .collect(Collectors.toList());
        return filteredTractors;
    }

    @ModelAttribute("trailers")
    public List<Trailer> trailers() {
        List<Trailer> tr = driverRepository.findAll().stream()
                .map(driver -> driver.getTrailer())
                .collect(Collectors.toList());

        List<Trailer> trailers = trailerRepository.findAll();
        List<Trailer> filteredTrailers = trailers.stream()
                .filter(trailer -> !tr.contains(trailer))
                .collect(Collectors.toList());
        return filteredTrailers;
    }
}

