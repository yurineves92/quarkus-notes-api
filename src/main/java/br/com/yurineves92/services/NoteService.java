package br.com.yurineves92.services;


import br.com.yurineves92.entities.Note;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class NoteService {

    @Inject
    EntityManager em;

    public List<Note> findAll(){
        return em.createQuery("from Note", Note.class).getResultList();
    }

    @Transactional
    public Note createNote(Note note) {
        em.persist(note);

        return note;
    }

    @Transactional
    public Note updateNote(UUID id, Note noteUpdate) {
        Note note = em.find(Note.class, id);
        if(note != null) {
            note.setTitle(noteUpdate.getTitle());
            note.setBody(noteUpdate.getBody());
            em.merge(note);
        }

        return note;
    }

    public Note findNoteById(UUID id){
        return em.find(Note.class, id);
    }
    @Transactional
    public void deleteNote(UUID id){
        Note note = em.find(Note.class, id);
        if(note != null){
            em.remove(note);
        }
    }
}
