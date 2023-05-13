package ua.AvadaMedia.adminREST.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.AvadaMedia.adminREST.Model.Cinema;
import ua.AvadaMedia.adminREST.Model.GalleryForStock;
import ua.AvadaMedia.adminREST.Model.SEOBlock;
import ua.AvadaMedia.adminREST.Model.Stock;
import ua.AvadaMedia.adminREST.ModelDAO.ObjectModelDAOHibernateService;
import ua.AvadaMedia.adminREST.RequestBody.SEOBlockDependencies.CinemaDependencies.StockRequestBody;
import ua.AvadaMedia.adminREST.Service.UploadFileService;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/StockREST")
public class StockRESTController implements IRESTController<Stock, StockRequestBody> {

    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private ObjectModelDAOHibernateService<Object> commonDAO;

    private final String stockPathForFiles = "/Srock";

    @PostMapping("/addPhoto")
    @CrossOrigin
    @Override
    public boolean addPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadFileService.addPhoto(
                stockPathForFiles,
                Objects.requireNonNull(file.getOriginalFilename()),
                file.getBytes()
        );
    }

    @PostMapping("/addNewEntity")
    @CrossOrigin
    @Override
    public boolean addNewEntity(@RequestBody StockRequestBody stockRequestBody) {
        Stock stock = new Stock();
        stock.setTitle(stockRequestBody.getTitle());
        stock.setDescription(stockRequestBody.getDescription());
        stock.setVideo_link(stockRequestBody.getVideoLink());
        stock.setState(stockRequestBody.isState());
        String path4MainImage = "";
        File[] fileArray = new File(uploadFileService.createFilePath(stockPathForFiles, stockRequestBody.getTitle())).listFiles();
        GalleryForStock[] galleryArray = new GalleryForStock[fileArray.length - 1];
        int indexForGalleryArray = 0;
        for (File tempFile : fileArray) {
            if (tempFile.getName().contains("main"))
                path4MainImage = uploadFileService.convertPathToValueForDataBase(tempFile, stockPathForFiles);
            else {
                GalleryForStock gallery = new GalleryForStock();
                gallery.setStock(stock);
                gallery.setPath_to_image(uploadFileService.convertPathToValueForDataBase(tempFile, stockPathForFiles));
                galleryArray[indexForGalleryArray] = gallery;
                ++indexForGalleryArray;
            }
        }
        if (!path4MainImage.equals(""))
            stock.setPath_to_main_image(path4MainImage);
        else return false;
        return commonDAO.workWithSession(session -> {
            if (stockRequestBody.getSeoBlockId() != -1)
                stock.setSeo_block(session.get(SEOBlock.class, stockRequestBody.getSeoBlockId()));
            commonDAO.add(session, stock, galleryArray);
            return true;
        });
    }

    @GetMapping(value = "/getAll", params = { "page" })
    @CrossOrigin
    @Override
    public Map<Long, Stock> getFullPage(@RequestParam("page") int page) {
        return null;
    }

    @Override
    public long getSize() {
        return 0;
    }

}
