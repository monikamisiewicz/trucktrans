package pl.coderslab.trucktrans.tractor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.trucktrans.driver.DriverRepository;
import pl.coderslab.trucktrans.model.Driver;
import pl.coderslab.trucktrans.model.Tractor;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tractors")
@RequiredArgsConstructor
public class TractorController {

    private final TractorRepository tractorRepository;
    private final DriverRepository driverRepository;

    @GetMapping
    public String add(Model model) {
        model.addAttribute("tractor", new Tractor());
        return "tractors/add";
    }

    @PostMapping
    public String save(@ModelAttribute("tractor") @Valid Tractor tractor, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "tractors/add";
        }
        tractorRepository.save(tractor);
        return "redirect:/tractors/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Optional<Tractor> tractor = tractorRepository.findById(id);
        model.addAttribute("tractor", tractor.orElseThrow(IllegalArgumentException::new));
        return "tractors/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("tractor") @Valid Tractor tractor, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "tractors/edit";
        }
        tractorRepository.save(tractor);
        return "redirect:/tractors/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        model.addAttribute("tractorId", id);
        return "tractors/delete";
    }

    @GetMapping("/delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action) {
        if (action) {
            tractorRepository.deleteById(id);
        }
        return "redirect:/tractors/list";
    }


    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("tractors", tractorRepository.findAll());
        return "tractors/list";
    }

    @GetMapping("/make")
    public String getByMake(@RequestParam("make") String make, Model model) {
        model.addAttribute("tractors", tractorRepository.findByMake(make));
        return "tractors/list";
    }


    @GetMapping("/registration")
    public String getByRegistrationNumber(@RequestParam("registration") String registrationNumber, Model model) {
        model.addAttribute("tractors", tractorRepository.findByRegistrationNumber(registrationNumber)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "tractors/list";
    }

    @GetMapping("/registration-start")
    public String getByRegistrationNumberStart(@RequestParam("registration") String registrationNumber, Model model) {
        model.addAttribute("tractors", tractorRepository.findByRegistrationNumberStartsWith(registrationNumber));
        return "tractors/list";
    }

    @GetMapping("/vin")
    public String getByVin(@RequestParam("vin") String vin, Model model) {
        model.addAttribute("tractors", tractorRepository.findByVin(vin)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "tractors/list";
    }

    @GetMapping("/vin-start")
    public String getByVinStart(@RequestParam("vin") String vin, Model model) {
        model.addAttribute("tractors", tractorRepository.findByVinStartsWith(vin));
        return "tractors/list";
    }

    @GetMapping("/vin-end")
    public String getByVinEnd(@RequestParam("vin") String vin, Model model) {
        model.addAttribute("tractors", tractorRepository.findByVinEndsWith(vin));
        return "tractors/list";
    }



    @ModelAttribute("makes")
    public List<String> makes() {
        return Arrays.asList("Renault", "MAN", "DAF", "Volvo", "Scania", "Mercedes", "Iveco", "Lohr", "Titan","Freightliner", "Kenworth", "Mack");
    }

    @ModelAttribute("drivers")
    public List<Driver> drivers() {
        return driverRepository.findAll();
    }
}
