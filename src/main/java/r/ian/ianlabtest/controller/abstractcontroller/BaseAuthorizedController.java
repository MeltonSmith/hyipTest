package r.ian.ianlabtest.controller.abstractcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.Model;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.data.dto.UserInfoDTO;
import r.ian.ianlabtest.data.repo.UserRepo;
import r.ian.ianlabtest.sec.AuthenticationFacade;

import java.security.Principal;
import java.util.Objects;

/**
 * @author Melton Smith
 * @since 29.07.2021
 */
public abstract class BaseAuthorizedController {
    protected final UserRepo userRepo;

    @Autowired
    protected BaseAuthorizedController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    protected void prepare(Model model, Principal principal){
        User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        UserInfoDTO userInfoDTO = new UserInfoDTO(userRepo.getById(Objects.requireNonNull(user).getId()));

        model.addAttribute("principalId", user.getId());
        model.addAttribute("userInfoDTO", userInfoDTO);

    }

}
