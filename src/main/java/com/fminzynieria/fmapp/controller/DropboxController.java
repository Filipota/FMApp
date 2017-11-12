package com.fminzynieria.fmapp.controller;

import com.dropbox.core.v2.DbxClientV2;
import com.fminzynieria.fmapp.service.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
        String filePath = "/" + name + "/Zdjęcia";
        model.addAttribute("pathName", filePath);
        return "fileUpload";
    }

    @RequestMapping(value = "/customer/document/link/{path}", method = RequestMethod.GET)
    public ModelAndView getDocumentsLink(Model model, @PathVariable String path) throws Exception {
        String realPath = "/" + path + "/Dokumenty";
        String dropboxLink = dropboxService.getDropboxLink(realPath);
        return new ModelAndView("redirect:" + dropboxLink);

    }

    @RequestMapping(value = "/customer/photo/link/{path}", method = RequestMethod.GET)
    public ModelAndView gethotosLink(Model model, @PathVariable String path) throws Exception {
        String realPath = "/" + path + "/Zdjęcia";
        String dropboxLink = dropboxService.getDropboxLink(realPath);
        return new ModelAndView("redirect:" + dropboxLink);
    }
}
