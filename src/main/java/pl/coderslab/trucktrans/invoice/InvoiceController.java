package pl.coderslab.trucktrans.invoice;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.trucktrans.company.CompanyRepository;
import pl.coderslab.trucktrans.contractor.ContractorRepository;
import pl.coderslab.trucktrans.driver.DriverRepository;
import pl.coderslab.trucktrans.model.Company;
import pl.coderslab.trucktrans.model.Contractor;
import pl.coderslab.trucktrans.model.Driver;
import pl.coderslab.trucktrans.model.Invoice;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;
    private final DriverRepository driverRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;

    @GetMapping
    public String add(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "invoices/add";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Invoice invoice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "invoices/add";
        }
        invoiceRepository.save(invoice);
        return "redirect:/invoices/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        model.addAttribute("invoice", invoice.orElseThrow(IllegalArgumentException::new));
        return "invoices/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute @Valid Invoice invoice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "invoices/edit";
        }
        invoiceRepository.save(invoice);
        return "redirect:/invoices/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        model.addAttribute("invoiceId", id);
        return "invoices/delete";
    }

    @GetMapping("/delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action) {
        if (action) {
            invoiceRepository.deleteById(id);
        }
        return "redirect:/invoices/list";
    }


    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("invoices", invoiceRepository.findAll());
        return "invoices/list";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable long id, Model model) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        model.addAttribute("invoice", invoice.orElseThrow(IllegalArgumentException::new));
        return "invoices/details";
    }


    @GetMapping("/contractor")
    public String getByContractor(@RequestParam("contractor") long contractorId, Model model) {
        if (contractorId != -1) {
            Contractor contractor = contractorRepository.findById(contractorId).orElseThrow(IllegalAccessError::new);
            model.addAttribute("invoices", invoiceRepository.findByContractor(contractor));
        } else {
            model.addAttribute("invoices", Collections.emptyList());
        }
        return "invoices/list";
    }


    @GetMapping("/number_contains")
    public String getByNumberContainig(@RequestParam("number") String number, Model model) {
        model.addAttribute("invoices", invoiceRepository.findByInvoiceNumberContaining(number));
        return "invoices/list";
    }

    @GetMapping("/invoice-number")
    public String getByNumber(@RequestParam("number") String number, Model model) {
        model.addAttribute("invoices", invoiceRepository.findByInvoiceNumber(number)
                .map(Collections::singletonList).orElse(Collections.emptyList()));
        return "invoices/list";
    }

    @GetMapping("/invoice-date")
    public String getByInvoiceDate(@RequestParam("date") LocalDate date, Model model) {
        model.addAttribute("invoices", invoiceRepository.findByInvoiceDate(date));
        return "invoices/list";
    }

    //problem z konwersją daty!!!!
    @GetMapping("/date-range")
    public String getByDateRange(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                 @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end, Model model) {
        model.addAttribute("invoices", invoiceRepository.findInvoiceByDateBetween(start, end));
        return "invoices/list";
    }

    @GetMapping("/method")
    public String getByPaymentMethod(@RequestParam("paymentMethod") String method, Model model) {
        model.addAttribute("invoices", invoiceRepository.findByPaymentMethod(method));
        return "invoices/list";
    }

    @ModelAttribute("paymentMethods")
    public List<String> payments() {
        return Arrays.asList("cash",  "pre-payment", "bank transfer");
    }

    @ModelAttribute("company")
    public List<Company> companyData() {
        return companyRepository.findAll();
    }

    @ModelAttribute("contractors")
    public List<Contractor> contractors() {
        return contractorRepository.findAll();
    }

    @ModelAttribute("drivers")
    public List<Driver> drivers() {
        return driverRepository.findAll();
    }



}
