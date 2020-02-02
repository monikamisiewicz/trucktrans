package pl.coderslab.trucktrans;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.trucktrans.trailer.TrailerRepository;
import pl.coderslab.trucktrans.truck.TruckRepository;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final TruckRepository truckRepository;
    private final TrailerRepository trailerRepository;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/fleet")
    public String getFleet(Model model) {
        model.addAttribute("trucks", truckRepository.findAll());
        model.addAttribute("trailers", trailerRepository.findAll());
        return "fleet";
    }
}
