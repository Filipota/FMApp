package com.fminzynieria.fmapp.controller;

import com.dropbox.core.v2.DbxClientV2;
import com.fminzynieria.fmapp.form.CustomerNameForm;
import com.fminzynieria.fmapp.service.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("dropbox")
public class DropboxController {

    @Autowired
    DropboxService dropboxService;

    @Autowired
    DbxClientV2 dbxClientV2;

    @RequestMapping(value = "/upload", headers = ("content-type=multipart/*"), produces = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, "application/json"},
            consumes = {"application/x-www-form-urlencoded", "application/json"}, method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath)
            throws Exception {
        dropboxService.fileUpload(file, filePath);
        return "redirect:/dropbox/customers";
    }

    @RequestMapping("/fileupload")
    public String fileUpload(Model model) throws Exception {
        model.addAttribute("folders", dropboxService.getFolders());
        return "fileupload";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String customers(Model model) throws Exception {
        model.addAttribute("folders", dropboxService.getFolders());
        return "customers";
    }


    @RequestMapping(value = "/customer/document/{name}")
    public String addDocumentToCustomer(Model model, @PathVariable String name) throws Exception {
        String filePath = "/" + name + "/Dokumenty";
        model.addAttribute("pathName", filePath);
        return "fileUpload";
    }

    @RequestMapping(value = "/customer/photo/{name}")
    public String addPhotoToCustomer(Model model, @PathVariable String name) throws Exception {
        String filePath = "/" + name + "/Zdjecia";
        model.addAttribute("pathName", filePath);
        return "fileUpload";
    }
}
