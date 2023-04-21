package lk.notes.app.repo;

import lk.notes.app.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepo extends CrudRepository<Note, Integer> {

    List<Note> getAllBySaved(Integer id);

    List<Note> getNotesBySavedAndAndTitleContainingOrDescriptionContaining(@Param("saved")  Integer saved, @Param("title") String title, @Param("description") String description);

    Note getNoteById(Integer id);


}
