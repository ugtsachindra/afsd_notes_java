package lk.notes.app.controller;

import lk.notes.app.dao.NoteDao;
import lk.notes.app.dto.NoteSaveDto;
import lk.notes.app.model.Note;
import lk.notes.app.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteDao noteDao;

    private final String File_Path = "E:/ACPT/AFSD/Projects/NotesApp/app/src/main/resources/static/image/";
    @PostMapping("/save")
    public boolean saveNote(@RequestParam(value="image") MultipartFile image, @RequestParam(value="title")String title, @RequestParam(value="description")String description){

            boolean status = false;
        try {

            Note note = new Note();
            note.setTitle(title);
            note.setDescription(description);
            note.setCreated(new Date());
            note.setSaved(1);
            String newFile = (new Date().getTime())+image.getOriginalFilename();

            note.setImage("/image/"+newFile);

            image.transferTo(new File(File_Path+newFile));
            status = noteDao.saveNote(note);

        }catch(Exception e){
            System.out.println(e);
        }


        return status;
    }
    @GetMapping("/")
    public List<Note> getAllNotes(){

        List<Note> notes = noteDao.getAllNotes();
        return notes;
    }

    @GetMapping("/search/{title}")
    public List<Note> getAllNotes(@PathVariable(value="title") String title){

        List<Note> notes = noteDao.serachNotesByTitle(title);
        System.out.println(notes);
        return notes;
    }

@GetMapping("/{id}")
public Note viewNote(@PathVariable(value="id") Integer noteId){
    Note note = null;
        try {
            note = noteDao.getNote(noteId);
        }catch(Exception e){
            System.out.println(e);
        }
        return note;
}
@PutMapping("/update")
public boolean editNote(@RequestParam(value="id") Integer noteId, @RequestParam(value="image") MultipartFile image, @RequestParam(value="title")String title, @RequestParam(value="description")String description){
    Note note = null;
    boolean status = false;
    try {
        note = noteDao.getNote(noteId);
        note.setTitle(title);
        note.setDescription(description);
        note.setCreated(new Date());
        note.setSaved(1);
        String newFile = (new Date().getTime())+image.getOriginalFilename();
        note.setImage("/image/"+newFile);

        status =noteDao.saveNote(note);

    }catch(Exception e){
        System.out.println(e);
    }
    return status;
}
@DeleteMapping("/{id}")
public boolean deleteNote(@PathVariable(value="id") Integer noteId){
    boolean status = false;
    try {
        noteDao.deleteNote(noteId);
        status=true;
    }catch(Exception e){
        System.out.println(e);
    }
    return status;
}

}
