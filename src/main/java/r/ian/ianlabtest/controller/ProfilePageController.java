package r.ian.ianlabtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import r.ian.ianlabtest.controller.abstractcontroller.BaseAuthorizedController;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.data.dto.UserInfoDTO;
import r.ian.ianlabtest.data.repo.UserRepo;
import r.ian.ianlabtest.sec.AuthenticationFacade;

import java.security.Principal;
import java.util.Objects;

/**
 * @author Melton Smith
 * @since 26.07.2021
 */
@Controller
public class ProfilePageController extends BaseAuthorizedController {

    @Autowired
    public ProfilePageController(AuthenticationFacade authenticationFacade, UserRepo userRepo) {
        super(userRepo);
    }

    @GetMapping(value = "/profile")
    public String getHandle(Model model, Principal principal){
        super.prepare(model, principal);
        return "personal/profile";
    }
}
