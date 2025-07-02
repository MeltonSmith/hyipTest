package r.ian.ianlabtest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import r.ian.ianlabtest.data.dto.UserInfoDTO;
import r.ian.ianlabtest.service.UserService;


/**
 * @author Melton Smith
 * @since 23.07.2021
 */
@Controller
@RequestMapping("registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String handle(Model model, @ModelAttribute UserInfoDTO userInfoDTO) {
        return "auth/registration";
    }

    @PostMapping()
    public String register(@Valid UserInfoDTO userInfoDTO, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()){
            return "auth/registration";
        }

        userService.registerUserFromDTO(userInfoDTO);
        redirectAttributes.addFlashAttribute("successfulRegistration", "Success! You can now sign in with your credentials");
        return "redirect:/main";
    }
}
