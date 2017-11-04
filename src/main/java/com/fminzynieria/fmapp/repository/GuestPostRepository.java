package com.fminzynieria.fmapp.repository;

import com.fminzynieria.fmapp.entities.GuestPostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestPostRepository extends CrudRepository<GuestPostEntity, Long> {
}
