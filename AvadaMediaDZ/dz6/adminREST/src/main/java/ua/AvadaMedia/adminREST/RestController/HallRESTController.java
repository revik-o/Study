package ua.AvadaMedia.adminREST.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.AvadaMedia.adminREST.CommonMethod.Filter;
import ua.AvadaMedia.adminREST.Model.Cinema;
import ua.AvadaMedia.adminREST.Model.GalleryForHall;
import ua.AvadaMedia.adminREST.Model.Hall;
import ua.AvadaMedia.adminREST.Model.SEOBlock;
import ua.AvadaMedia.adminREST.ModelDAO.ObjectModelDAOHibernateService;
import ua.AvadaMedia.adminREST.RequestBody.SEOBlockDependencies.CinemaDependencies.HallRequestBody;
import ua.AvadaMedia.adminREST.Service.UploadFileService;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/HallREST")
public class HallRESTController implements IRESTController<Hall, HallRequestBody> {

    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private ObjectModelDAOHibernateService<Object> commonDAO;

    private final String hallPathForFiles = "/Hall";

    @PostMapping("/addPhoto")
    @CrossOrigin
    @Override
    public boolean addPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadFileService.addPhoto(
                hallPathForFiles,
                Objects.requireNonNull(file.getOriginalFilename()),
                file.getBytes()
        );
    }

    @PostMapping("/addNewEntity")
    @CrossOrigin
    @Override
    public boolean addNewEntity(@RequestBody HallRequestBody hallRequestBody) {
        Hall hall = new Hall();
        hall.setNumber(hallRequestBody.getNumber());
        hall.setDescription(hallRequestBody.getDescription());
        String path4Layout = "";
        String path4TopBanner = "";
        File[] fileArray = new File(uploadFileService.getUploadPath() + hallPathForFiles + "/" + hallRequestBody.getNumber() + "/").listFiles();
        assert fileArray != null;
        GalleryForHall[] galleryArray = new GalleryForHall[fileArray.length - 1];
        int indexForGalleryArray = 0;
        for (File tempFile : fileArray) {
            if (tempFile.getName().contains("layout"))
                path4Layout = tempFile.getAbsolutePath().substring(tempFile.getAbsolutePath().indexOf(hallPathForFiles + "/"));
            else if (tempFile.getName().contains("top_banner"))
                path4TopBanner = tempFile.getAbsolutePath().substring(tempFile.getAbsolutePath().indexOf(hallPathForFiles + "/"));
            else {
                GalleryForHall gallery = new GalleryForHall();
                gallery.setHall(hall);
                gallery.setPath_to_image(tempFile.getAbsolutePath().substring(tempFile.getAbsolutePath().indexOf(hallPathForFiles + "/")));
                galleryArray[indexForGalleryArray] = gallery;
                ++indexForGalleryArray;
            }
        }
        if (!path4Layout.equals(""))
            hall.setPath_to_layout_hall_image(path4Layout);
        else return false;
        if (!path4TopBanner.equals(""))
            hall.setPath_to_top_banner_image(path4TopBanner);
        return commonDAO.workWithSession(session -> {
            if (hallRequestBody.getCinemaId() != -1)
                hall.setCinema(session.get(Cinema.class, hallRequestBody.getCinemaId()));
            else return false;
            if (hallRequestBody.getSeoBlockId() != -1)
                hall.setSeo_block(session.get(SEOBlock.class, hallRequestBody.getSeoBlockId()));
            commonDAO.add(session, hall);
            commonDAO.add(session, Filter.filterForSortedArray(galleryArray));
            return true;
        });
    }

    @GetMapping(value = "/getAll", params = { "page" })
    @CrossOrigin
    @Override
    public Map<Long, Hall> getFullPage(@RequestParam("page") int page) {
        return null;
    }

    @Override
    public long getSize() {
        return 0;
    }

}