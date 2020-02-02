package pl.coderslab.trucktrans.trailer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.mytrans.repository.DriverRepository;
import pl.coderslab.trucktrans.model.Driver;
import pl.coderslab.trucktrans.model.Trailer;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trailers")
@RequiredArgsConstructor
public class TrailerController {

    private final TrailerRepository trailerRepository;
    private final DriverRepository driverRepository;

    @GetMapping
    public String add(Model model) {
        model.addAttribute("trailer", new Trailer());
        return "trailers/add";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Trailer trailer, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "trailers/add";
        }
        trailerRepository.save(trailer);
        return "redirect:/trailers/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Optional<Trailer> trailer = trailerRepository.findById(id);
        model.addAttribute("trailer", trailer.orElseThrow(IllegalArgumentException::new));
        return "trailers/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute @Valid Trailer trailer, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "trailers/edit";
        }
        trailerRepository.save(trailer);
        return "redirect:/trailers/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        model.addAttribute("trailerId", id);
        return "trailers/delete";
    }

    @GetMapping("/delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action) {
        if (action) {
            trailerRepository.deleteById(id);
        }
        return "redirect:/trailers/list";
    }


    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("trailers", trailerRepository.findAll());
        return "trailers/list";
    }



//    @GetMapping("/driver")
//    public String getByDriver(@RequestParam("driver") long driverId, Model model) {
//        if (driverId != -1) {
//            Driver driver = driverRepository.findById(driverId).orElseThrow(IllegalAccessError::new);
//            model.addAttribute("trailers", trailerRepository.findByDriver(driver));
//        } else {
//            model.addAttribute("trailers", Collections.emptyList());
//        }
//        return "trailers/list";
//    }

    @GetMapping("/make")
    public String getByMake(@RequestParam("make") String make, Model model) {
        model.addAttribute("trailers", trailerRepository.findByMake(make));
        return "trailers/list";
    }

    @GetMapping("/model")
    public String getByModel(@RequestParam("model") String truckModel, Model model) {
        model.addAttribute("trailers", trailerRepository.findByModel(truckModel));
        return "trailers/list";
    }

    @GetMapping("/registration")
    public String getByRegistrationNumber(@RequestParam("registration") String registrationNumber, Model model) {
        model.addAttribute("trailers", trailerRepository.findByRegistrationNumber(registrationNumber)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "trailers/list";
    }

    @GetMapping("/registration-start")
    public String getByRegistrationNumberStart(@RequestParam("registration") String registrationNumber, Model model) {
        model.addAttribute("trailers", trailerRepository.findByRegistrationNumberStartsWith(registrationNumber));
        return "trailers/list";
    }

    @GetMapping("/vin")
    public String getByVin(@RequestParam("vin") String vin, Model model) {
        model.addAttribute("trailers", trailerRepository.findByVin(vin)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "trailers/list";
    }

    @GetMapping("/vin-start")
    public String getByVinStart(@RequestParam("vin") String vin, Model model) {
        model.addAttribute("trailers", trailerRepository.findByVinStartsWith(vin));
        return "trailers/list";
    }

    @GetMapping("/vin-end")
    public String getByVinEnd(@RequestParam("vin") String vin, Model model) {
        model.addAttribute("trailers", trailerRepository.findByVinEndsWith(vin));
        return "trailers/list";
    }



    @ModelAttribute("makes")
    public List<String> makes() {
        return Arrays.asList("Schmitz",  "Cargobull", "Krone", "Fruehauf", "LAG", "Kögel", "Benalu", "Samro", "Kässbohrer", "Wielton");
    }

    @ModelAttribute("drivers")
    public List<Driver> drivers() {
        return driverRepository.findAll();
    }
}
