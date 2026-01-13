package com.notes.demo.service;

import com.notes.demo.Api.ApiException;
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

    public Notes getNote(Integer id){
        Notes note = notesRepository.findSingelNoteById(id);
        if (note == null){
            throw new RuntimeException("note not found!");
        }
        return note;
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
        n.setTitle(notes.getTitle());
        n.setNote(notes.getNote());
        n.setEndingDate(notes.getEndingDate());
        notesRepository.save(n);
    }



}
