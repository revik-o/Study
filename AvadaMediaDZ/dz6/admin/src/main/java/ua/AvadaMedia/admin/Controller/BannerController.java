package ua.AvadaMedia.admin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/banners")
public class BannerController {

    @GetMapping("/")
    public String index() {
        return "banners/index";
    }

    @GetMapping("/addNewBanner/")
    public String addNewBanner() {
        return "s";
    }

}