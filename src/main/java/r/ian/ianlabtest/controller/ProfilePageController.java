package r.ian.ianlabtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import r.ian.ianlabtest.controller.abstractcontroller.BaseAuthorizedController;
import r.ian.ianlabtest.sec.AuthenticationFacade;

/**
 * @author Melton Smith
 * @since 26.07.2021
 */
@Controller
public class ProfilePageController extends BaseAuthorizedController {

//    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public ProfilePageController(AuthenticationFacade authenticationFacade) {
        super(authenticationFacade);
//        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping(value = "/profile")
    public String getHandle(Model model){
//        Authentication authentication = authenticationFacade.getAuthentication();
        super.prepare(model);

//        User user = (User) authentication.getPrincipal();
//        model.addAttribute("user", user);

        return "personal/profile";
    }
}
