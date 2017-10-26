package com.fminzynieria.fmapp.controller;

import com.fminzynieria.fmapp.form.GuestPostForm;
import com.fminzynieria.fmapp.service.GuestPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GuestPostController {

    @Autowired
    private GuestPostService guestPostService;

    @RequestMapping("/guestbook")
    public String guestBook(Model model) {
        model.addAttribute("GuestPosts", guestPostService.getAllPosts());
        return "guestbook";
    }

    @RequestMapping(value = {"/newpost"}, method = RequestMethod.GET)
    public String newPostForm(Model model) {
        GuestPostForm guestPostForm = new GuestPostForm();
        model.addAttribute("guestPostForm", guestPostForm);
        return "newpost";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String saveGuestPost(Model model, @ModelAttribute("guestPostForm") GuestPostForm guestPostForm) {
        String saveResult = guestPostService.savePost(guestPostForm);
        return "redirect:/guestbook";
    }

//
//    public String addNewPost(@RequestParam String author, @RequestParam String email,
//                             @RequestParam String content, @RequestParam Boolean showMail) {
//        return guestPostService.addNewPost(author, email, content, showMail);

}
