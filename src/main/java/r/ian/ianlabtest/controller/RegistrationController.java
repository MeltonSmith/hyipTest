package r.ian.ianlabtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Melton Smith
 * @since 23.07.2021
 */
@Controller
public class RegistrationController {

    @GetMapping(value ={"/registration"})
    public String handle(Model model) {
        return "auth/registration";
    }

}
