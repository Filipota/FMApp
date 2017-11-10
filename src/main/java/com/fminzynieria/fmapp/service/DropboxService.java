package com.fminzynieria.fmapp.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.WriteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class DropboxService {

    @Autowired
    DbxClientV2 dbxClientV2;

    public void fileUpload(File fileToUpload, String filePath) throws Exception {
        try (InputStream in = new FileInputStream(fileToUpload)) {
            FileMetadata metadata = dbxClientV2.files().uploadBuilder(filePath)
                    .withMode(WriteMode.ADD)
                    .withClientModified(new Date(fileToUpload.lastModified()))
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
}

