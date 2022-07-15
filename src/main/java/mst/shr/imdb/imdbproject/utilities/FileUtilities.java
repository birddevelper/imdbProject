package mst.shr.imdb.imdbproject.utilities;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class FileUtilities {

    public static void makeDir(String directoryName){
        File directory = new File(directoryName);
        if (!directory.exists()){
            directory.mkdir();
        }
    }

    public static File saveUploadedFile(MultipartFile uploadedFile, String directory, boolean randomPrefix) throws IOException, NoSuchAlgorithmException {

        String fileName = FilenameUtils.getName(uploadedFile.getName());
        if(randomPrefix){
            String prefix = RandomUtilities.RandomStringGenerator(10);
            fileName = prefix + fileName;
        }

        makeDir(directory);
        File file = new File( directory + "/" + fileName );
        uploadedFile.transferTo(file);

        return file;
    }
}
