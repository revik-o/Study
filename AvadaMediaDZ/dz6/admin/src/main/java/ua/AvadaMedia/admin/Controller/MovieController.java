package ua.AvadaMedia.admin.Controller;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import ua.AvadaMedia.admin.Common.ModelInfo;
import ua.AvadaMedia.admin.Common.PostObject.FilesPO;
import ua.AvadaMedia.admin.Model.GalleryForMovie;
import ua.AvadaMedia.admin.Model.Movie;
import ua.AvadaMedia.admin.ModelDAO.IDelegateModelDAOHibernate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin/movie")
public class MovieController {

    @Value("${uploadPath}")
    private String uploadPath;

    @Autowired
    private IDelegateModelDAOHibernate hibernate;

    @GetMapping("/")
    public String index(Model model) {
        new ModelInfo(model).addLinks();
        model.addAttribute("movie_page_link", "/admin/movie/moviePage");
        return "movie/index";
    }

    @RequestMapping(value = "/moviePage", method = RequestMethod.GET)
    public String moviePage(
            @RequestParam(value = "id", required = false) Long id,
            Model model
    ) {
        model.addAttribute("filePO", new FilesPO());
        new ModelInfo(model).addLinks();
//        if (id != null) { }
        return "movie/movie_page";
    }

    @PostMapping("/moviePage")
    public RedirectView moviePagePostMethod(
            @RequestParam(value = "movie_name", required = false) String movieName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "main_picture", required = false) MultipartFile mainPicture,
            @ModelAttribute("filePO") FilesPO filesPO
//            @RequestParam(value = "trailer_link", required = false) String trailerLink,
//            @RequestParam(value = "two_d", required = false) Boolean twoD,
//            @RequestParam(value = "three_d", required = false) Boolean threeD,
//            @RequestParam(value = "imax", required = false) Boolean imax
            ) throws IOException {
        new File(uploadPath + "movie/" + movieName + "/").mkdirs();
        Movie movie = new Movie();
        movie.setName(movieName);
        movie.setDescription(description);
        String mainFilePath = uploadPath + "movie/" + movieName + "/" +
                new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
                        .format(new Date(System.currentTimeMillis())) + "_";
        String filePath = mainFilePath + mainPicture.getOriginalFilename();
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(mainPicture.getBytes());
        movie.setPath_to_main_image(filePath);
//        hibernate.add(movie);
        GalleryForMovie[] arrGalleryForMovies = new GalleryForMovie[filesPO.getSize()];
        for (int i = 0; i < arrGalleryForMovies.length; i++) {
            MultipartFile file = filesPO.getFile();
            String galleryPath = mainFilePath + file.getOriginalFilename();
            GalleryForMovie galleryForMovie = new GalleryForMovie();
            galleryForMovie.setMovie(movie);
            galleryForMovie.setPath_to_image(galleryPath);
            arrGalleryForMovies[i] = galleryForMovie;
            // write
            outputStream = new FileOutputStream(galleryPath);
            outputStream.write(file.getBytes());
        }



        for (GalleryForMovie galleryForMovie : arrGalleryForMovies) {
            System.out.println(galleryForMovie.getPath_to_image());
//            hibernate.add((GalleryForMovie) galleryForMovie);
        }
        return new RedirectView("/admin/movie/");
    }

}