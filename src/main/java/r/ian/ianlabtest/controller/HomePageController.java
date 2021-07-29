package r.ian.ianlabtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import r.ian.ianlabtest.controller.abstractcontroller.BaseAuthorizedController;
import r.ian.ianlabtest.data.repo.UserRepo;
import r.ian.ianlabtest.sec.AuthenticationFacade;

/**
 * @author Melton Smith
 * @since 26.07.2021
 */
@Controller
@RequestMapping("/home")
public class HomePageController extends BaseAuthorizedController {

    @Autowired
    public HomePageController(AuthenticationFacade authenticationFacade, UserRepo userRepo) {
        super(authenticationFacade, userRepo);
    }

    @GetMapping
    public String getHandle(Model model) {
        super.prepare(model);
        return "homepage";
    }
}
