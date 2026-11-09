package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




// ==========================================
// 4. CONTROLLER LAYER (REST Endpoints)
// ==========================================
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // GET /api/notes -> Fetch all notes
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    // POST /api/notes -> Create a new note
    @PostMapping
    public Note createNote(@RequestBody NoteRequest request) {
        return noteService.createNote(request.title(), request.content());
    }

    // Record for incoming JSON request body payload
    public record NoteRequest(String title, String content) {}
}