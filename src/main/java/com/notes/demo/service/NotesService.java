package com.notes.demo.service;

import Api.ApiException;
import com.notes.demo.model.Notes;
import com.notes.demo.repository.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final NotesRepository notesRepository;


    public List<Notes> getAllNotes(){
        return notesRepository.findAll();
    }

    public void addNote(Notes notes){
        notesRepository.save(notes);
    }


    public void deleteNote(Integer id){
        Notes notes = notesRepository.findSingelNoteById(id);
        if (notes == null){
            throw new ApiException("note not found!");
        }
        notesRepository.delete(notes);
    }


    public void updateNote(Integer id, Notes notes){
        Notes n = notesRepository.findSingelNoteById(id);
        if (n == null){
            throw new ApiException("note not found!");
        }
        n.setNote(notes.getNote());
        notesRepository.save(n);
    }



}
