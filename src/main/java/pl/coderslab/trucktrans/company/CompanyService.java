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
public class CompanyService {

//    private final CompanyRepository companyRepository;
//
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable long id, Model model) {
//        Optional<Company> company = companyRepository.findById(id);
//        model.addAttribute("company", company.orElseThrow(IllegalArgumentException::new));
//        return "company/edit";
//    }
//
//    @PostMapping("/edit/")
//    public String update(@ModelAttribute @Valid Company company, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "company/edit";
//        }
//        companyRepository.save(company);
//        return "redirect:/company/data";
//    }
}
