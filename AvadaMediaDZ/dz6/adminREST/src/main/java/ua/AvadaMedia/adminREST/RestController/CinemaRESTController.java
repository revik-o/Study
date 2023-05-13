package ua.AvadaMedia.adminREST.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.AvadaMedia.adminREST.CommonMethod.Filter;
import ua.AvadaMedia.adminREST.Model.Cinema;
import ua.AvadaMedia.adminREST.Model.GalleryForCinema;
import ua.AvadaMedia.adminREST.Model.SEOBlock;
import ua.AvadaMedia.adminREST.ModelDAO.ObjectModelDAOHibernateService;
import ua.AvadaMedia.adminREST.RequestBody.SEOBlockDependencies.CinemaRequestBody;
import ua.AvadaMedia.adminREST.Service.UniversalHQLQueriesService;
import ua.AvadaMedia.adminREST.Service.UploadFileService;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/CinemaREST")
public class CinemaRESTController implements IRESTController<Cinema, CinemaRequestBody> {

    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private ObjectModelDAOHibernateService<Object> commonDAO;
    @Autowired
    private UniversalHQLQueriesService<Cinema, Long> queriesService;

    private final String cinemaPathForFiles = "/Cinema";

    @PostMapping("/addPhoto")
    @CrossOrigin
    @Override
    public boolean addPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadFileService.addPhoto(
                cinemaPathForFiles,
                Objects.requireNonNull(file.getOriginalFilename()),
                file.getBytes()
        );
    }

    @PostMapping("/addNewEntity")
    @CrossOrigin
    @Override
    public boolean addNewEntity(@RequestBody CinemaRequestBody requestBody) {
        Cinema cinema = new Cinema();
        cinema.setName(requestBody.getName());
        cinema.setDescription(requestBody.getDescription());
        cinema.setAbout_cinema(requestBody.getAboutCinema());
        cinema.setConditions(requestBody.getConditions());
        String path4Logo = "";
        String path4TopBanner = "";
        File[] fileArray = new File(uploadFileService.getUploadPath() + cinemaPathForFiles + "/" + requestBody.getName() + "/").listFiles();
        assert fileArray != null;
        GalleryForCinema[] galleryArray = new GalleryForCinema[fileArray.length - 1];
        int indexForGalleryArray = 0;
        for (File tempFile : fileArray) {
            if (tempFile.getName().contains("logo"))
                path4Logo = tempFile.getAbsolutePath().substring(tempFile.getAbsolutePath().indexOf(cinemaPathForFiles + "/"));
            else if (tempFile.getName().contains("top_banner"))
                path4TopBanner = tempFile.getAbsolutePath().substring(tempFile.getAbsolutePath().indexOf(cinemaPathForFiles + "/"));
            else {
                GalleryForCinema gallery = new GalleryForCinema();
                gallery.setCinema(cinema);
                gallery.setPath_to_image(tempFile.getAbsolutePath().substring(tempFile.getAbsolutePath().indexOf(cinemaPathForFiles + "/")));
                galleryArray[indexForGalleryArray] = gallery;
                ++indexForGalleryArray;
            }
        }
        if (!path4Logo.equals(""))
            cinema.setPath_to_logo_image(path4Logo);
        else return false;
        if (!path4TopBanner.equals(""))
            cinema.setPath_to_top_banner_image(path4TopBanner);
        return commonDAO.workWithSession(session -> {
            if (requestBody.getSeoBlockId() != -1)
                cinema.setSeo_block(session.get(SEOBlock.class, requestBody.getSeoBlockId()));
            commonDAO.add(session, cinema);
            commonDAO.add(session, Filter.filterForSortedArray(galleryArray));
            return true;
        });
    }

    @GetMapping(value = "/getAll", params = { "min_id" })
    @CrossOrigin
    @Override
    public Map<Long, Cinema> getFullPage(@RequestParam("min_id") int minId) {
        return queriesService.getContentFromDataBaseWithPagination(
                commonDAO, "Cinema", minId, 8, Cinema::getId
        );
    }

    @GetMapping("/getSize")
    @CrossOrigin
    @Override
    public long getSize() {
        return queriesService.getFullSizeTable(commonDAO, "Cinema");
    }
}