package pl.coderslab.trucktrans.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.trucktrans.model.User;
import pl.coderslab.trucktrans.user.UserRepository;


import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "users/add";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "users/add";
        }
        userRepository.save(user);
        return "redirect:/users/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        model.addAttribute("user", user.orElseThrow(IllegalArgumentException::new));
        return "users/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "users/edit";
        }
        userRepository.save(user);
        return "redirect:/users/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCheck(@PathVariable long id, Model model) {
        model.addAttribute("userId", id);
        return "users/delete";
    }

    @GetMapping("/delete-action/{id}")
    public String delete(@PathVariable long id, @RequestParam("action") boolean action) {
        if (action) {
            userRepository.deleteById(id);
        }
        return "redirect:/users/list";
    }


    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/list";
    }

}

