package r.ian.ianlabtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import r.ian.ianlabtest.controller.abstractcontroller.BaseAuthorizedController;
import r.ian.ianlabtest.sec.AuthenticationFacade;

/**
 * @author Melton Smith
 * @since 26.07.2021
 */
@Controller
@RequestMapping("/home")
public class HomePageController extends BaseAuthorizedController {

//    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public HomePageController(AuthenticationFacade authenticationFacade) {
        super(authenticationFacade);
//        this.authenticationFacade = authenticationFacade;
    }


    @GetMapping
    public String getHandle(Model model) {
        super.prepare(model);

//        User user = (User) authenticationFacade.getAuthentication().getPrincipal();
//        model.addAttribute("user", user);

        return "homepage";
    }
}
