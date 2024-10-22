package com.bilgeadam.NotesApp.service;

import com.bilgeadam.NotesApp.entity.Note;
import com.bilgeadam.NotesApp.entity.User;
import com.bilgeadam.NotesApp.repository.NoteRepository;
import com.bilgeadam.NotesApp.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public void save(Note note,String username) {
        Optional<User> userOptional = userRepository.findById(username);
        if(userOptional.isEmpty()) {
            throw new RuntimeException("Kullanıcı bulunamadı...");
        }

        note.setUser(userOptional.get());
        noteRepository.save(note);
    }

    public void deleteById(Long id,String username) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if(noteOptional.isPresent() && noteOptional.get().getUser().getUsername().equals(username)) {
            noteRepository.delete(noteOptional.get());
        } else {
            throw new RuntimeException("Başaramadık...");
        }
    }

    public List<Note> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        System.out.println(currentUsername);
        List<Note> noteList =  noteRepository.findAllByOrderByIdDesc();
        List<Note> newList = new ArrayList<>();
        for (Note note: noteList){
            if (note.getUser().getUsername().equals(currentUsername)){
                newList.add(note);
            }
        }
        return newList;
    }
}
