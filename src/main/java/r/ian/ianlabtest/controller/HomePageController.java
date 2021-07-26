package r.ian.ianlabtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.sec.AuthenticationFacade;

/**
 * @author Melton Smith
 * @since 26.07.2021
 */
@Controller
@RequestMapping("/home")
public class HomePageController {

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public HomePageController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }


    @GetMapping
    public String handle(Model model) {
        User user = (User) authenticationFacade.getAuthentication().getPrincipal();
        model.addAttribute("fullName", user.getPerson().getFullNameTrunc());

        return "homepage";
    }
}
