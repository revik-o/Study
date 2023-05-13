package ua.AvadaMedia.adminREST.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.AvadaMedia.adminREST.CommonMethod.Filter;
import ua.AvadaMedia.adminREST.Model.GalleryForMovie;
import ua.AvadaMedia.adminREST.Model.Movie;
import ua.AvadaMedia.adminREST.Model.SEOBlock;
import ua.AvadaMedia.adminREST.ModelDAO.ObjectModelDAOHibernateService;
import ua.AvadaMedia.adminREST.RequestBody.SEOBlockDependencies.MovieRequestBody;
import ua.AvadaMedia.adminREST.Service.UploadFileService;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/MovieREST")
public class MovieRESTController implements IRESTController<Movie, MovieRequestBody> {

    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private ObjectModelDAOHibernateService<Object> commonDAO;

    private final String moviePathForFiles = "/Movie";

    @PostMapping("/addPhoto")
    @CrossOrigin
    @Override
    public boolean addPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadFileService.addPhoto(
                moviePathForFiles,
                Objects.requireNonNull(file.getOriginalFilename()),
                file.getBytes()
        );
    }

    @PostMapping("/addNewEntity")
    @CrossOrigin
    @Override
    public boolean addNewEntity(@RequestBody MovieRequestBody requestBody) {
        Movie movie = new Movie();
        movie.setName(requestBody.getName());
        movie.setDescription(requestBody.getDescription());
        String path = "";
        File[] fileArray = new File(uploadFileService.getUploadPath() + moviePathForFiles + "/" + requestBody.getName() + "/").listFiles();
        assert fileArray != null;
        GalleryForMovie[] galleryArray = new GalleryForMovie[fileArray.length - 1];
        int indexForGalleryArray = 0;
        for (File tempFile : fileArray) {
            if (tempFile.getName().contains("main")) {
                path = tempFile.getAbsolutePath().substring(tempFile.getAbsolutePath().indexOf(moviePathForFiles + "/"));
            } else {
                GalleryForMovie gallery = new GalleryForMovie();
                gallery.setMovie(movie);
                gallery.setPath_to_image(tempFile.getAbsolutePath().substring(tempFile.getAbsolutePath().indexOf(moviePathForFiles + "/")));
                galleryArray[indexForGalleryArray] = gallery;
                ++indexForGalleryArray;
            }
        }
        if (!path.equals(""))
            movie.setPath_to_main_image(path);
        else
            return false;
        commonDAO.workWithSession(session -> {
            if (requestBody.getSeoBlockId() != -1)
                movie.setSeo_block(session.get(SEOBlock.class, requestBody.getSeoBlockId()));
            commonDAO.add(session, movie);
            commonDAO.add(session, Filter.filterForSortedArray(galleryArray));
        });
        return true;
    }

    @GetMapping(value = "/getAll", params = { "page" })
    @CrossOrigin
    @Override
    public Map<Long, Movie> getFullPage(@RequestParam("page") int page) {
        return null;
    }

    @Override
    public long getSize() {
        return 0;
    }
}