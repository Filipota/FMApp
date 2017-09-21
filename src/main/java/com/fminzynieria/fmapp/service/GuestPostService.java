package com.fminzynieria.fmapp.service;

import com.fminzynieria.fmapp.entities.GuestPostEntity;
import com.fminzynieria.fmapp.repository.GuestPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class GuestPostService {

    @Autowired
    public GuestPostRepository guestPostRepository;


    public String addNewPost(String author, String content, Date dateTime, String email, Boolean showMail) {

        GuestPostEntity guestPost = new GuestPostEntity();
        guestPost.setAuthor(author);
        guestPost.setContent(content);
        guestPost.setDateTime(dateTime);
        guestPost.setEmail(email);
        guestPost.setShowEmail(showMail);
        guestPostRepository.save(guestPost);
        return "Saved!";
    }

    public Iterable<GuestPostEntity> getAllPosts() {
        return guestPostRepository.findAll();
    }
}
