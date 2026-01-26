package com.notes.demo.controller;

import com.notes.demo.model.Note;
import com.notes.demo.model.User;
import com.notes.demo.service.NotesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NotesController {

    private final NotesService notesService;

    @GetMapping("/fetch-notes")
    public ResponseEntity getAllNotes(){
        return ResponseEntity.ok().body(notesService.getAllNotes());
    }


    @PostMapping("/add-note/{userId}")
    public ResponseEntity addNote(@RequestBody @Valid Note note, @PathVariable Long userId){
        notesService.addNote(note, userId);
        return ResponseEntity.ok().body("note added successfully");
    }


    @PutMapping("/update-note/{id}")
    public ResponseEntity updateNote(@PathVariable Long id, @RequestBody @Valid Note note){
        notesService.updateNote(id, note);
        return ResponseEntity.ok().body("note updated successfully");
    }

    @DeleteMapping("/delete-note/{id}")
    public ResponseEntity deleteNote(@PathVariable Long id){
        notesService.deleteNote(id);
        return ResponseEntity.ok().body("note deleted successfully");
    }

    @GetMapping("/get-note/{id}")
    public ResponseEntity getNote(@PathVariable Long id){
        notesService.getNote(id);
        return ResponseEntity.ok().body(notesService.getNote(id));
    }
}
