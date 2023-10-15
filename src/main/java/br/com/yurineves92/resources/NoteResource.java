package br.com.yurineves92.resources;

import br.com.yurineves92.entities.Note;
import br.com.yurineves92.services.NoteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteResource {
    @Inject
    NoteService noteService;

    @GET
    @RolesAllowed("admin")
    public List<Note> getAll(){
        return noteService.findAll();
    }
    @POST
    @RolesAllowed({"admin", "editor"})
    public Note add(Note note){
        return noteService.createNote(note);
    }

    @PUT
    @RolesAllowed({"admin", "editor"})
    @Path("/{id}")
    public Note update(@PathParam("id") UUID id, Note note){
        return noteService.updateNote(id, note);
    }

    @GET
    @RolesAllowed({"admin", "editor", "leitor"})
    @Path("/{id}")
    public Note getById(@PathParam("id") UUID id){
        return noteService.findNoteById(id);
    }

    @DELETE
    @RolesAllowed({"admin"})
    @Path("/{id}")
    public void delete(@PathParam("id") UUID id){
        noteService.deleteNote(id);
    }
}
