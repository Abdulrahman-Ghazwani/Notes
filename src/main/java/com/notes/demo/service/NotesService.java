package com.notes.demo.service;

import com.notes.demo.model.Notes;
import com.notes.demo.repository.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final NotesRepository notesRepository;


    public void getAllNotes(){
        List<Notes> notesList = notesRepository.findAll();
    }


    public void updateNote(String note, Integer id){
        List<Notes> notes = notesRepository.findNoteById(id);

//        if (id == null){
//            throw new
//        }
    }

}
