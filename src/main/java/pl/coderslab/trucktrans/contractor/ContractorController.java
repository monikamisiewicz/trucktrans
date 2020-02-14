package pl.coderslab.trucktrans.contractor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.trucktrans.driver.DriverRepository;
import pl.coderslab.trucktrans.model.Contractor;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/contractors")
@RequiredArgsConstructor
public class ContractorController {

    private final ContractorRepository contractorRepository;
    private final DriverRepository driverRepository;

    @GetMapping
    public String add(Model model) {
        model.addAttribute("contractor", new Contractor());
        return "contractors/add";
    }

    @PostMapping
    public String save(@ModelAttribute("contractor") @Valid Contractor contractor, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "contractors/add";
        }
        contractorRepository.save(contractor);
        return "redirect:/contractors/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Optional<Contractor> contractor = contractorRepository.findById(id);
        model.addAttribute("contractor", contractor.orElseThrow(IllegalArgumentException::new));
        return "contractors/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("contractor") @Valid Contractor contractor, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "contractors/edit";
        }
        contractorRepository.save(contractor);
        return "redirect:/contractors/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        model.addAttribute("contractorId", id);
        return "contractors/delete";
    }

    @GetMapping("/delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action) {
        if (action) {
            contractorRepository.deleteById(id);
        }
        return "redirect:/contractors/list";
    }


    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("contractors", contractorRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        return "contractors/list";
    }

    @GetMapping("/name")
    public String getByName(@RequestParam("name") String name, Model model) {
        model.addAttribute("contractors", contractorRepository.findByName(name)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "contractors/list";
    }

    @GetMapping("/name-start")
    public String getByNameStart(@RequestParam("name") String name, Model model) {
        model.addAttribute("contractors", contractorRepository.findByNameStartsWith(name));
        return "contractors/list";
    }

    @GetMapping("/nip")
    public String getByNip(@RequestParam("nip") String nip, Model model) {
        model.addAttribute("contractors", contractorRepository.findByNip(nip)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "contractors/list";
    }
}

