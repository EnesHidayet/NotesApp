package com.bilgeadam.NotesApp.controller;

import com.bilgeadam.NotesApp.entity.Note;
import com.bilgeadam.NotesApp.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request)
    {
        return request.getRequestURI();
    }

    @GetMapping(path = "save")
    public ModelAndView save()
    {
        ModelAndView noteView = new ModelAndView("saveNote");
        Note note=new Note();
        noteView.addObject("note", note);
        return noteView;
    }

    @PostMapping(path = "save")
    public ModelAndView save(@ModelAttribute(name = "note") Note note) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        noteService.save(note, userDetails.getUsername());
        return new ModelAndView("redirect:/note");
    }

    @PostMapping(path = "delete")
    public ModelAndView delete(@ModelAttribute(name = "id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        noteService.deleteById(id, userDetails.getUsername());
        return new ModelAndView("redirect:/note");
    }

    @GetMapping(path = {"","/"})
    public ModelAndView getNotesByUser() {
        ModelAndView modelAndView = new ModelAndView("note");
        Note note = new Note();
        modelAndView.addObject("note", note);
        modelAndView.addObject("notes", noteService.findAll());
        return modelAndView;
    }

}
