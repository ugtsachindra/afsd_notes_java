package lk.notes.app.dao;

import lk.notes.app.model.Note;
import lk.notes.app.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteDao {

    @Autowired
    NoteRepo noteRepo;

    public boolean saveNote(Note note){
        boolean status = false;

        try {
            noteRepo.save(note);
            status=true;
        }catch(Exception e){
            System.out.println(e);
        }
        return status;
    }

    public List<Note> getAllNotes(){
        List<Note> notes=new ArrayList<>();
        try {
            notes = noteRepo.getAllBySaved(1);
        }catch(Exception e){
            System.out.println(e);
        }
        return notes;

    }

    public List<Note> serachNotesByTitle(String title){
        List<Note> notes=new ArrayList<>();
        try {
            notes = noteRepo.getNotesBySavedAndAndTitleContainingOrDescriptionContaining(1,title,title);
        }catch(Exception e){
            System.out.println(e);
        }
        return notes;

    }

    public Note getNote(Integer noteId){
        Note note = null;
        try {
            note = noteRepo.getNoteById(noteId);
        }catch (Exception e){
            System.out.println(e);
        }
        return note;
    }

    public Note editNote(Integer noteId){
        boolean status = false;
        Note note = null;
        try {
            note = noteRepo.getNoteById(noteId);
            noteRepo.save(note);
            status=true;
        }catch (Exception e){
            System.out.println(e);
        }

        return note;

    }

    public boolean deleteNote(Integer noteId){
        boolean status = false;
        Note note = null;
        try {
            note = noteRepo.getNoteById(noteId);
            noteRepo.delete(note);
            status=true;
        }catch (Exception e){
            System.out.println(e);
        }
        return status;
    }

}
