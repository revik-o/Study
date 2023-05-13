package ua.AvadaMedia.adminREST.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
//@Scope("prototype")
public class UploadFileService {

    @Value("${uploadPath}")
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    @Deprecated
    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public void mkDir(String path) {
        File uploadFolder = new File(uploadPath + path);
        if (!uploadFolder.exists())
            uploadFolder.mkdirs();
    }

    public void writeFile(String path, byte[] bytes) throws IOException {
        File uploadFolder = new File(uploadPath);
        if (!uploadFolder.exists())
            uploadFolder.mkdirs();
        FileOutputStream outputStream = new FileOutputStream(uploadPath + path);
        outputStream.write(bytes);
    }

    // Movie_main_avadaMedia.png
    public boolean addPhoto(String pathForFile, String originalFilename, byte[] fileBytes) {
        try {
            String movieNamePath = pathForFile + "/" + originalFilename.substring(0, originalFilename.indexOf("_"));
            mkDir(movieNamePath); // create folder with movie name
            writeFile(movieNamePath + "/" + originalFilename.replaceAll(originalFilename.substring(0, originalFilename.indexOf("_") + 1), ""), fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String convertPathToValueForDataBase(File file, String entityPath) {
        return file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(entityPath + "/"));
    }

    public String createFilePath(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(uploadPath).append("/");
        for (String s : strings) stringBuilder.append(s).append("/");
        return stringBuilder.toString();
    }

}