package mongo.service;

import java.util.List;

import mongo.model.Note;

public interface NoteService {
	   public void saveManyNotes(List<Note> notes);
	   public void saveOneNote(Note note);	   
	   public List<Note> getAllNotes(); 
	   public void deleteOneNote(Long noteId);
	   public Note findById(Long noteId);
	   public void dropAllNotes();
	   public List<Note> getNotesByUsername(String username,boolean completed);
	   public void setCheckedItem(Long id, Long itemId, Boolean checked);
}
