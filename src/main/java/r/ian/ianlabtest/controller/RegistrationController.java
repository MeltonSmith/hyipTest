package r.ian.ianlabtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public String handle(Model model) {
        model.addAttribute("userInfoDTO", new UserInfoDTO());
        return "auth/registration";
    }

    @PostMapping
    public String register(UserInfoDTO userInfoDTO, Model model, BindingResult result) {
        if (result.hasErrors()){
            model.addAttribute("errorMessage", "ALLE.");
            return "/main";
        }

        userService.registerUserFromDTO(userInfoDTO);
        return "redirect:/main";
    }
}