package ua.AvadaMedia.adminREST.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.AvadaMedia.adminREST.CommonMethod.Filter;
import ua.AvadaMedia.adminREST.Model.Cinema;
import ua.AvadaMedia.adminREST.Model.GalleryForNews;
import ua.AvadaMedia.adminREST.Model.News;
import ua.AvadaMedia.adminREST.Model.SEOBlock;
import ua.AvadaMedia.adminREST.ModelDAO.ObjectModelDAOHibernateService;
import ua.AvadaMedia.adminREST.RequestBody.SEOBlockDependencies.CinemaDependencies.NewsRequestBody;
import ua.AvadaMedia.adminREST.Service.UploadFileService;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/NewsREST")
public class NewsRESTController implements IRESTController<News, NewsRequestBody> {

    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private ObjectModelDAOHibernateService<Object> commonDAO;

    private final String newsPathForFiles = "/News";

    @PostMapping("/addPhoto")
    @CrossOrigin
    @Override
    public boolean addPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadFileService.addPhoto(
                newsPathForFiles,
                Objects.requireNonNull(file.getOriginalFilename()),
                file.getBytes()
        );
    }

    @PostMapping("/addNewEntity")
    @CrossOrigin
    @Override
    public boolean addNewEntity(@RequestBody NewsRequestBody newsRequestBody) {
        News news = new News();
        news.setTitle(newsRequestBody.getTitle());
        news.setDescription(newsRequestBody.getDescription());
        news.setVideo_link(newsRequestBody.getVideoLink());
        news.setState(newsRequestBody.isState());
        news.setDate_of_publication(new Date(new Date().getTime()));
        String path4MainImage = "";
        File[] fileArray = new File(uploadFileService.getUploadPath() + newsPathForFiles + "/" + newsRequestBody.getTitle()).listFiles();
        assert fileArray != null;
        GalleryForNews[] galleryArray = new GalleryForNews[fileArray.length - 1];
        int indexForGalleryArray = 0;
        for (File tempFile : fileArray) {
            if (tempFile.getName().contains("main"))
                path4MainImage = uploadFileService.convertPathToValueForDataBase(tempFile, newsPathForFiles);
            else {
                GalleryForNews gallery = new GalleryForNews();
                gallery.setNews(news);
                gallery.setPath_to_image(uploadFileService.convertPathToValueForDataBase(tempFile, newsPathForFiles));
                galleryArray[indexForGalleryArray] = gallery;
                ++indexForGalleryArray;
            }
        }
        if (!path4MainImage.equals(""))
            news.setPath_to_main_image(path4MainImage);
        else return false;
        return commonDAO.workWithSession(session -> {
            if (newsRequestBody.getSeoBlockId() != -1)
                news.setSeo_block(session.get(SEOBlock.class, newsRequestBody.getSeoBlockId()));
            commonDAO.add(session, news);
            commonDAO.add(session, Filter.filterForSortedArray(galleryArray));
            return true;
        });
    }

    @GetMapping(value = "/getAll", params = { "page" })
    @CrossOrigin
    @Override
    public Map<Long, News> getFullPage(@RequestParam("page") int page) {
        return null;
    }

    @Override
    public long getSize() {
        return 0;
    }

}
