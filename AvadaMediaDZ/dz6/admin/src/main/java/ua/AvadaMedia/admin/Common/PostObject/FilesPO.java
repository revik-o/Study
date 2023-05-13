package ua.AvadaMedia.admin.Common.PostObject;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayDeque;
import java.util.Deque;

// TODO
@Deprecated
public class FilesPO {

    private Deque<MultipartFile> files = new ArrayDeque<>();
//    private MultipartFile file;

    public int getSize() {
        return files.size();
    }

    public MultipartFile getFile() {
        return files.pollFirst();
    }

    public void setFile(MultipartFile file) {
        files.push(file);
    }

}
