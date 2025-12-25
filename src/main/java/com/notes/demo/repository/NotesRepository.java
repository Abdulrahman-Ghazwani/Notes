package com.notes.demo.repository;

import com.notes.demo.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {



    Notes findSingelNoteById (Integer id);




}
