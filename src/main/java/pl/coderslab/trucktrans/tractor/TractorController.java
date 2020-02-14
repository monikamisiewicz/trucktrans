package pl.coderslab.trucktrans.truck;

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
@RequestMapping("/trucks")
@RequiredArgsConstructor
public class TruckController {

    private final TruckRepository truckRepository;
    private final DriverRepository driverRepository;

    @GetMapping
    public String add(Model model) {
        model.addAttribute("truck", new Tractor());
        return "trucks/add";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Tractor tractor, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "trucks/add";
        }
        truckRepository.save(tractor);
        return "redirect:/trucks/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Optional<Tractor> truck = truckRepository.findById(id);
        model.addAttribute("truck", truck.orElseThrow(IllegalArgumentException::new));
        return "trucks/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute @Valid Tractor tractor, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "trucks/edit";
        }
        truckRepository.save(tractor);
        return "redirect:/trucks/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        model.addAttribute("truckId", id);
        return "trucks/delete";
    }

    @GetMapping("/delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action) {
        if (action) {
            truckRepository.deleteById(id);
        }
        return "redirect:/trucks/list";
    }


    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("trucks", truckRepository.findAll());
        return "trucks/list";
    }



//    @GetMapping("/driver")
//    public String getByDriver(@RequestParam("driver") long driverId, Model model) {
//        if (driverId != -1) {
//            Driver driver = driverRepository.findById(driverId).orElseThrow(IllegalAccessError::new);
//            model.addAttribute("trucks", truckRepository.findByDriver(driver));
//        } else {
//            model.addAttribute("trucks", Collections.emptyList());
//        }
//        return "trucks/list";
//    }

    @GetMapping("/make")
    public String getByMake(@RequestParam("make") String make, Model model) {
        model.addAttribute("trucks", truckRepository.findByMake(make));
        return "trucks/list";
    }

    @GetMapping("/model")
    public String getByModel(@RequestParam("model") String truckModel, Model model) {
        model.addAttribute("trucks", truckRepository.findByModel(truckModel));
        return "vehicles/list";
    }

    @GetMapping("/registration")
    public String getByRegistrationNumber(@RequestParam("registration") String registrationNumber, Model model) {
        model.addAttribute("trucks", truckRepository.findByRegistrationNumber(registrationNumber)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "trucks/list";
    }

    @GetMapping("/registration-start")
    public String getByRegistrationNumberStart(@RequestParam("registration") String registrationNumber, Model model) {
        model.addAttribute("trucks", truckRepository.findByRegistrationNumberStartsWith(registrationNumber));
        return "trucks/list";
    }

    @GetMapping("/vin")
    public String getByVin(@RequestParam("vin") String vin, Model model) {
        model.addAttribute("trucks", truckRepository.findByVin(vin)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "trucks/list";
    }

    @GetMapping("/vin-start")
    public String getByVinStart(@RequestParam("vin") String vin, Model model) {
        model.addAttribute("trucks", truckRepository.findByVinStartsWith(vin));
        return "trucks/list";
    }

    @GetMapping("/vin-end")
    public String getByVinEnd(@RequestParam("vin") String vin, Model model) {
        model.addAttribute("trucks", truckRepository.findByVinEndsWith(vin));
        return "trucks/list";
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
