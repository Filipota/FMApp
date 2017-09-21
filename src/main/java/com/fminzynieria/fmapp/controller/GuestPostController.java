package com.fminzynieria.fmapp.controller;

import com.fminzynieria.fmapp.entities.GuestPostEntity;
import com.fminzynieria.fmapp.repository.GuestPostRepository;
import com.fminzynieria.fmapp.service.GuestPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class GuestPostController {

    @Autowired
    private GuestPostService guestPostService;

    @RequestMapping("/guestbook")
    public String guestBook(Model model) {
        model.addAttribute("GuestPosts",guestPostService.getAllPosts());
        return "guestbook";
    }

    @GetMapping(path = "add")
    public @ResponseBody
    String addNewPost(@RequestParam String author, @RequestParam String content,
                      @RequestParam Date dateTime, @RequestParam String email,
                      @RequestParam Boolean showMail) {

        return guestPostService.addNewPost(author, content, dateTime, email, showMail);
    }


    @GetMapping(path = "all")
    public @ResponseBody
    Iterable<GuestPostEntity> getAllPosts() {

        return guestPostService.getAllPosts();
    }

}
