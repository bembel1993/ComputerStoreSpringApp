package by.application.productcatalog.controllers;

import by.application.productcatalog.model.entity.User;
import by.application.productcatalog.model.enums.UserType;
import by.application.productcatalog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "loginpage";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "registerpage";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        userService.logout();
        return "loginpage";
    }

    @PostMapping("/login")
    public String loginPost(
            @RequestParam String login,
            @RequestParam String password,
            Model model) {
        try {
            if(login.isEmpty() || password.isEmpty()){
                model.addAttribute("params_not_found", "Не все данные указаны");
                return "loginpage";
            }

            Optional<User> optionalUser = userService.login(login, password);
            if(!optionalUser.isPresent()){
                model.addAttribute("user_not_found", "Пользователь не найден");
                return "loginpage";
            }

            if(optionalUser.get().getTypeId() == UserType.USER.getValue()){
                model.addAttribute("auth", "");
                model.addAttribute("user", optionalUser.get());
                return "redirect:/cabinetu";
            } else {
                model.addAttribute("auth", "");
                model.addAttribute("admin", optionalUser.get());
                return "redirect:/cabineta";
            }
        } catch(Exception e){
            model.addAttribute("server_error", "Ошибка на сервере");
        }
        return "redirect:/catalog";
    }

    @PostMapping("/register")
    public String registerPost(
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String confirm,
            @RequestParam String mail,
            @RequestParam String info,
            Model model) {
        try {
            if(name.isEmpty() || login.isEmpty() || password.isEmpty() || confirm.isEmpty()
                    || mail.isEmpty() || info.isEmpty()) {
                model.addAttribute("error", "Не все данные указаны");
                return "registerpage";
            }
            if(!password.equals(confirm)){
                model.addAttribute("pass", "Пароли не совпадают");
                return "registerpage";
            }
            if(mail.indexOf("@") < 0){
                model.addAttribute("mail", "Неверный формат e-mail");
                return "registerpage";
            }
            if(userService.checkLogin(login)){
                model.addAttribute("login", "Данный логин уже существует");
                return "registerpage";
            }
            userService.register(name, login, password, mail, info);
        } catch(Exception e) {
            model.addAttribute("server_error", "Ошибка на сервере");
        }
        return "loginpage";
    }


}
