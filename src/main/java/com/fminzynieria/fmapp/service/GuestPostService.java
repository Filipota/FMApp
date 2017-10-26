package com.fminzynieria.fmapp.service;

import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.fminzynieria.fmapp.entities.GuestPostEntity;
import com.fminzynieria.fmapp.form.GuestPostForm;
import com.fminzynieria.fmapp.repository.GuestPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import java.util.*;

@Service
public class GuestPostService {

    @Autowired
    public GuestPostRepository guestPostRepository;


    public String addNewPost(String author, String email, String content, Boolean showMail) {

        Date date = new Date();

        GuestPostEntity guestPost = new GuestPostEntity();
        guestPost.setAuthor(author);
        guestPost.setContent(content);
        guestPost.setDateTime(date);
        guestPost.setEmail(email);
        guestPost.setShowEmail(showMail);
        guestPostRepository.save(guestPost);
        return "Saved!";
    }

    public String savePost(GuestPostForm guestPostForm) {

        Date currentDate = new Date();

        GuestPostEntity guestPost = new GuestPostEntity();
        guestPost.setAuthor(guestPostForm.getAuthor());
        guestPost.setEmail(guestPostForm.getEmail());
        guestPost.setContent(guestPostForm.getContent());
        guestPost.setDateTime(currentDate);
        guestPost.setShowEmail(guestPostForm.isShowEmail());
        guestPostRepository.save(guestPost);
        return "Zapisano poprawnie!";
    }

    public List<GuestPostEntity> getAllPosts() {
        Iterable<GuestPostEntity> posts=guestPostRepository.findAll();
        List<GuestPostEntity> postList=new ArrayList<>();
        posts.forEach(p->postList.add(p));
        Collections.sort(postList,(d1,d2)->d2.getDateTime().compareTo(d1.getDateTime()));
        return postList;
    }
}
