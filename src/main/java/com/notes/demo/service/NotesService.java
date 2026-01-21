package com.notes.demo.service;

import com.notes.demo.Api.ApiException;
import com.notes.demo.model.Note;
import com.notes.demo.model.User;
import com.notes.demo.repository.NotesRepository;
import com.notes.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final NotesRepository notesRepository;
    private final UserRepository userRepository;


    public List<Note> getAllNotes(){
        return notesRepository.findAll();
    }

    public Note getNote(Long id){
        Note note = notesRepository.findSingelNoteById(id);
        if (note == null){
            throw new RuntimeException("note not found!");
        }
        return note;
    }

    public void addNote(Note note, Long userId){
        User user = userRepository.findUserById(userId);
        if (note == null){
            throw new RuntimeException("note not found");
        }
        note.setUser(user);
        notesRepository.save(note);
    }


    public void deleteNote(Long id){
        Note note = notesRepository.findSingelNoteById(id);
        if (note == null){
            throw new ApiException("note not found!");
        }
        notesRepository.delete(note);
    }


    public void updateNote(Long id, Note note){
        Note n = notesRepository.findSingelNoteById(id);
        if (n == null){
            throw new ApiException("note not found!");
        }
        n.setTitle(note.getTitle());
        n.setNote(note.getNote());
        n.setEndingDate(note.getEndingDate());
        notesRepository.save(n);
    }



}
