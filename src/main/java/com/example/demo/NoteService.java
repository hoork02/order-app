package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repository;

    // Spring automatically injects the JPA repository here
    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> getAllNotes() {
        return repository.findAll(); // Fetches directly from PostgreSQL
    }

    public Note createNote(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Note title cannot be blank");
        }
        Note note = new Note(title, content);
        return repository.save(note); // Saves directly to PostgreSQL
    }
}