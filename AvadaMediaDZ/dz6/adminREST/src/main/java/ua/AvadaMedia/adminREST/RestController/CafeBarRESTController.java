package ua.AvadaMedia.adminREST.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.AvadaMedia.adminREST.Model.CafeBar;
import ua.AvadaMedia.adminREST.Model.GalleryForCafeBar;
import ua.AvadaMedia.adminREST.Model.SEOBlock;
import ua.AvadaMedia.adminREST.ModelDAO.ObjectModelDAOHibernateService;
import ua.AvadaMedia.adminREST.RequestBody.SEOBlockDependencies.CinemaDependencies.CafeBarRequestBody;
import ua.AvadaMedia.adminREST.Service.UploadFileService;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/CafeBarREST")
public class CafeBarRESTController implements IRESTController<CafeBar, CafeBarRequestBody> {

    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private ObjectModelDAOHibernateService<Object> commonDAO;

    private final String cafeBarPathForFiles = "/Cafe-Bar";

    @PostMapping("/addPhoto")
    @CrossOrigin
    @Override
    public boolean addPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadFileService.addPhoto(
                cafeBarPathForFiles,
                Objects.requireNonNull(file.getOriginalFilename()),
                file.getBytes()
        );
    }

    @PostMapping("/addNewEntity")
    @CrossOrigin
    @Override
    public boolean addNewEntity(@RequestBody CafeBarRequestBody cafeBarRequestBody) {
        CafeBar cafeBar = new CafeBar();
        cafeBar.setTitle(cafeBarRequestBody.getTitle());
        cafeBar.setDescription(cafeBarRequestBody.getDescription());
        cafeBar.setState(cafeBar.isState());
        String path4MainPath = "";
        File[] fileArray = new File(uploadFileService.createFilePath(cafeBarPathForFiles, cafeBarRequestBody.getTitle())).listFiles();
        assert fileArray != null;
        GalleryForCafeBar[] galleryArray = new GalleryForCafeBar[fileArray.length - 1];
        int index4GalleryArray = 0;
        for (File tempFile : fileArray) {
            if (tempFile.getName().contains("main"))
                path4MainPath = uploadFileService.convertPathToValueForDataBase(tempFile, cafeBarPathForFiles);
            else {
                GalleryForCafeBar gallery = new GalleryForCafeBar();
                gallery.setCafeBar(cafeBar);
                gallery.setPath_to_image(uploadFileService.convertPathToValueForDataBase(tempFile, cafeBarPathForFiles));
                galleryArray[index4GalleryArray] = gallery;
                ++index4GalleryArray;
            }
        }
        if (!path4MainPath.equals(""))
            cafeBar.setPath_to_main_image(path4MainPath);
        else return false;
        return commonDAO.workWithSession(session -> {
            if (cafeBarRequestBody.getSeoBlockId() != -1)
                cafeBar.setSeo_block(session.get(SEOBlock.class, cafeBarRequestBody.getSeoBlockId()));
            commonDAO.add(session, cafeBar, galleryArray);
            return true;
        });
    }

    @GetMapping(value = "/getAll", params = { "page" })
    @CrossOrigin
    @Override
    public Map<Long, CafeBar> getFullPage(@RequestParam("page") int page) {
        return null;
    }

    @Override
    public long getSize() {
        return 0;
    }

}
