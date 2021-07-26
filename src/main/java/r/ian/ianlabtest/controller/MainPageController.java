package r.ian.ianlabtest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Melton Smith
 * @since 21.07.2021
 */
@Controller
public class MainPageController {

    //TODO delete
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping(value ={"/main"})
    public String handle(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("appName", appName);
            return "main";
        }

        return "redirect:/home";
    }
}
