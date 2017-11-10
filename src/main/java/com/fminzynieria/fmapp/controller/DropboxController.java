package com.fminzynieria.fmapp.controller;

import com.dropbox.core.v2.DbxClientV2;
import com.fminzynieria.fmapp.service.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

@Controller
@RequestMapping("dropbox")
public class DropboxController {

    @Autowired
    DropboxService dropboxService;

    @Autowired
    DbxClientV2 dbxClientV2;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") File file, @RequestParam("filePath") String filePath)
            throws Exception {
        dropboxService.fileUpload(file, filePath);
        return "Plik poprawnie załadowano do:  " + filePath + "!";
    }
}
