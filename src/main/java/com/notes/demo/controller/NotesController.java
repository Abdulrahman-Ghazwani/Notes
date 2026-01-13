package com.notes.demo.controller;

import com.notes.demo.model.Notes;
import com.notes.demo.service.NotesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NotesController {

    private final NotesService notesService;

    @GetMapping("/fetch-notes")
    public ResponseEntity getAllNotes(){
        return ResponseEntity.ok().body(notesService.getAllNotes());
    }


    @PostMapping("/add-note")
    public ResponseEntity addNote(@RequestBody @Valid Notes notes){
        notesService.addNote(notes);
        return ResponseEntity.ok().body("note added successfully");
    }


    @PutMapping("/update-note/{id}")
    public ResponseEntity updateNote(@PathVariable Integer id, @RequestBody @Valid Notes notes){
        notesService.updateNote(id, notes);
        return ResponseEntity.ok().body("note updated successfully");
    }

    @DeleteMapping("/delete-note/{id}")
    public ResponseEntity deleteNote(@PathVariable Integer id){
        notesService.deleteNote(id);
        return ResponseEntity.ok().body("note deleted successfully");
    }

    @GetMapping("/get-note/{id}")
    public ResponseEntity getNote(@PathVariable Integer id){
        notesService.getNote(id);
        return ResponseEntity.ok().body(notesService.getNote(id));
    }

}
