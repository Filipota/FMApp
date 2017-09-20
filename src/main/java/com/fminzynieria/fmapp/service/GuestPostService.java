package com.fminzynieria.fmapp.service;

import com.fminzynieria.fmapp.entities.GuestPostEntity;
import com.fminzynieria.fmapp.repository.GuestPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GuestPostService {

    @Autowired
    public GuestPostRepository guestPostRepository;


    public String addNewPost(String author, String content, LocalDateTime dateTime, String email, Boolean showMail) {

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
