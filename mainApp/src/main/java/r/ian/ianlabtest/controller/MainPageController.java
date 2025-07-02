package r.ian.ianlabtest.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Melton Smith
 * @since 21.07.2021
 */
@Controller
@RequestMapping({"/", "/main"})
public class MainPageController {

    @GetMapping
    public String handle(@RequestParam(value = "error", defaultValue = "false") boolean loginError, Model model, HttpSession session) {
        if (loginError){
            return "main";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "main";
        }

        return "redirect:/home";
    }
}
