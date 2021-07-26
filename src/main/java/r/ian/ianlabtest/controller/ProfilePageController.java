package r.ian.ianlabtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.sec.AuthenticationFacade;

/**
 * @author Melton Smith
 * @since 26.07.2021
 */
@Controller
public class ProfilePageController {

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public ProfilePageController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping(value = "/profile")
    public String handle(Model model){
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("fullName", ((User) authentication.getPrincipal()).getPerson().getFullNameTrunc());

        return "personal/profile";
    }
}
