package r.ian.ianlabtest.controller.abstractcontroller;

import org.springframework.ui.Model;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.sec.AuthenticationFacade;

/**
 * @author Melton Smith
 * @since 29.07.2021
 */
public abstract class BaseAuthorizedController {
    protected final AuthenticationFacade authenticationFacade;

    protected BaseAuthorizedController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    protected void prepare(Model model){
        User user = (User) authenticationFacade.getAuthentication().getPrincipal();
        model.addAttribute("user", user);
    }

}
