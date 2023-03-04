package by.application.productcatalog.controllers;

import by.application.productcatalog.model.entity.Product;
import by.application.productcatalog.model.entity.User;
import by.application.productcatalog.model.enums.UserType;
import by.application.productcatalog.service.ProductService;
import by.application.productcatalog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductCatalogController {

    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/search")
    public String searchMethod(
            @RequestParam String search,
            Model model) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("products", productService.findProductsByName(search));
        }
        setAuth(model);
        return "searchresult";
    }

    @PostMapping("/tobasket")
    public String toBasket(
            @RequestParam String id,
            @RequestParam String categoryId,
            @RequestParam String count,
            Model model) {
        try {
            productService.addToBasket(id, count, userService.getAuthUser());
        } catch (Exception e) {
            model.addAttribute("server_error", "Ошибка на сервере");
        }
        setAuth(model);
        return getPage(categoryId);
    }

    @PostMapping("/messagesend")
    public String message2(@RequestParam String comment, Model model) {
        if (userService.findAuthorizationUser()) {
            Optional<User> optional = userService.getAuthUser();
            if (comment != null) {
                productService.addMessage(optional.get(), comment);
            }
            model.addAttribute("questions", productService.getQuestionsByUserId(optional.get().getId()));
        }
        setAuth(model);
        return "supportpage";
    }

    @PostMapping("/saveu")
    public String saveu(
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam String password,
            Model model) {
        try {
            userService.update(name, login, password);
            if (userService.findAuthorizationUser() && userService.getAuthUser().isPresent()) {
                User user = userService.getAuthUser().get();
                model.addAttribute("name", user.getName());
                model.addAttribute("login", user.getLogin());
                model.addAttribute("password", user.getPassword());
            }
        } catch (Exception e) {
            model.addAttribute("server_error", "Ошибка на сервере");
        }
        setAuth(model);
        return "cabinetupage";
    }

    @PostMapping("/savea")
    public String savea(
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam String password,
            Model model) {
        try {
            userService.update(name, login, password);
            if (userService.findAuthorizationUser() && userService.getAuthUser().isPresent()) {
                User user = userService.getAuthUser().get();
                model.addAttribute("name", user.getName());
                model.addAttribute("login", user.getLogin());
                model.addAttribute("password", user.getPassword());
            }
        } catch (Exception e) {
            model.addAttribute("server_error", "Ошибка на сервере");
        }
        setAuth(model);
        return "cabinetapage";
    }

    @PostMapping("/delete")
    public String delete(
            @RequestParam String id,
            Model model) {
        productService.deleteProduct(id);
        setAuth(model);
        return "redirect:/catalog";
    }

    private void setAuth(Model model) {
        if (userService.findAuthorizationUser()) {
            Optional<User> optional = userService.getAuthUser();
            model.addAttribute("auth", "");
            if (optional.isPresent()) {
                if (optional.get().getTypeId().equals(UserType.USER.getValue())) {
                    model.addAttribute("user", "");
                } else {
                    model.addAttribute("admin", "");
                }
            }
        }
    }

    private String getPage(String categoryId) {
        switch (Integer.parseInt(categoryId)) {
            case 2:
                return "redirect:/catalog2";
            case 3:
                return "redirect:/catalog3";
            default:
                return "redirect:/catalog";
        }
    }

    /*@PostMapping("/addproduct")
    public String addProduct(
            @RequestParam Integer id,
            @RequestParam Integer category_id,
            @RequestParam String name,
            @RequestParam String info,
            @RequestParam String price,
            @RequestParam Integer count,
            @RequestParam String manufacturer,
            @RequestParam String releaseDate,
            @RequestParam String link,
           // @RequestParam byte[] photo,
            Product product) {
        try {
            productService.add(id, category_id, name, info, price, count, manufacturer, releaseDate, link);
        } catch (Exception e) {
            System.out.println(e);
        }
            return "catalog2page";
    }*/
}
