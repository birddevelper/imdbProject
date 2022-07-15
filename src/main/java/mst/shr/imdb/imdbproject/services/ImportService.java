package mst.shr.imdb.imdbproject.services;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public interface ImportService {

    /**
     * This method reads uploaded file content and calls appropriate method based on content to import data to DB.
     *
     * @param uploadedFile   uploaded file
     */
    void importDataset(MultipartFile uploadedFile) throws NumberFormatException, IOException, NoSuchAlgorithmException;




}
