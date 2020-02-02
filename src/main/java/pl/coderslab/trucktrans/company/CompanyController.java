package pl.coderslab.trucktrans.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.trucktrans.model.Company;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository companyRepository;

    @GetMapping
    public String add(Model model) {
        model.addAttribute("company", new Company());
        return "company/add";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Company company, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "company/add";
        }
        companyRepository.save(company);
        return "redirect:/company/data";
    }



    @GetMapping("/delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        model.addAttribute("companyId", id);
        return "company/delete";
    }

    @GetMapping("/delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action) {
        if (action) {
            companyRepository.deleteById(id);
        }
        return "redirect:/company/data";
    }

    @GetMapping("/data")
    public String getData(Model model) {
        model.addAttribute("company", companyRepository.findAll());
        return "company/data";
    }
}
