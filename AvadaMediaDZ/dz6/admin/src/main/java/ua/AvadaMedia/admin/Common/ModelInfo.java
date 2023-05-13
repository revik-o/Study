package ua.AvadaMedia.admin.Common;

import org.springframework.ui.Model;

public class ModelInfo {

    private Model model;

    public ModelInfo(Model model) {
        this.model = model;
    }

    public void addLinks() {
        model.addAttribute("main_link", "/admin/main/");
        model.addAttribute("banners_link", "/admin/banners/");
        model.addAttribute("movie_link", "/admin/movie/");
        model.addAttribute("cinema_link", "/admin/cinema/");
        model.addAttribute("news_link", "/admin/news/");
        model.addAttribute("stock_link", "/admin/stock/");
        model.addAttribute("pages_link", "/admin/pages/");
        model.addAttribute("main_page_link", "/admin/pages/main_page/");
        model.addAttribute("about_cinema_page_link", "/admin/pages/about_cinema_page/");
        model.addAttribute("cafe_bar_page_link", "/admin/pages/cafe_bar_page/");
        model.addAttribute("vip_hall_page_link", "/admin/pages/vip_hall_page/");
        model.addAttribute("advertising_page_link", "/admin/pages/advertising_page/");
        model.addAttribute("children_s_room_page_link", "/admin/pages/children_s_room_page/");
        model.addAttribute("contacts_page_link", "/admin/pages/contacts_page/");
        model.addAttribute("users_page_link", "/admin/pages/users_page/");
        model.addAttribute("mailing_page_link", "/admin/pages/mailing_page/");
    }

}
