package ua.AvadaMedia.adminREST.RestController;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface IRESTController<V, T> {

    boolean addPhoto(MultipartFile file) throws IOException;
    boolean addNewEntity(T t);
    Map<Long, V> getFullPage(int page);
    long getSize();

}