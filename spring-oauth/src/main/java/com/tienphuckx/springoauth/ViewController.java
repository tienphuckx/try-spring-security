package com.tienphuckx.springoauth;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/profile")
    public String profile(OAuth2AuthenticationToken token, Model model) {
        MyUser user = new MyUser(
                token.getPrincipal().getAttribute("name"),
                token.getPrincipal().getAttribute("email"),
                token.getPrincipal().getAttribute("picture")  // This must match the attribute key used by Google OAuth2
        );
        model.addAttribute("user", user);
        return "user-profile";
    }

    @GetMapping("/login")
    public String login() {
        return "custom-login";
    }
}
