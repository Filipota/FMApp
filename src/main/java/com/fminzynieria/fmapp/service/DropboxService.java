package com.fminzynieria.fmapp.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.WriteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DropboxService {

    @Autowired
    DbxClientV2 dbxClientV2;

    public void fileUpload(MultipartFile fileToUpload, String filePath) throws Exception {
        try (ByteArrayInputStream in = new ByteArrayInputStream(fileToUpload.getBytes())) {
            String fullPath=filePath+"/"+fileToUpload.getOriginalFilename();
            FileMetadata metadata = dbxClientV2.files().uploadBuilder(fullPath)
                    .withMode(WriteMode.ADD)
                    .uploadAndFinish(in);
            System.out.println(metadata.toStringMultiline());
        } catch (UploadErrorException ex) {
            System.err.println("Error uploading to Dropbox: " + ex.getMessage());
            System.exit(1);
        } catch (DbxException ex) {
            System.err.println("Error uploading to Dropbox: " + ex.getMessage());
            System.exit(1);
        } catch (IOException ex) {
            System.err.println("Error reading from file \"" + fileToUpload + "\": " + ex.getMessage());
            System.exit(1);
        }
    }

    public List<String> getFolders() throws Exception {
        ListFolderResult result = dbxClientV2.files().listFolder("");
        List<String> folders = new ArrayList<>();
        result.getEntries().forEach(entry -> {
            folders.add(entry.getName());
        });
        result.getEntries().forEach(entry -> {
        });
        return folders;
    }
}

