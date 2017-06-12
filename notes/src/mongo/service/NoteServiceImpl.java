package mongo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import mongo.model.Note;
import mongo.model.NoteItem;

@CrossOrigin(origins="localhost:8100",maxAge=3600)
@Service
public class NoteServiceImpl implements NoteService {
	   
   @Autowired
   MongoTemplate mongoTempate;
	
   public void saveManyNotes(List<Note> notes) {		
	   notes.forEach(note->{mongoTempate.save(note);});		
   }	
   
   public void saveOneNote(Note note) {		
	  mongoTempate.save(note);	
   }	
   
   public List<Note> getAllNotes() {
	   List<Note> notes = mongoTempate.findAll(Note.class,"notes");
	   return notes;
   }	
   
   public List<Note> getNotesByUsername(String username, boolean archive) {
 	   Query query = Query.query(new Criteria().orOperator(Criteria.where("username").is(username).and("archive").is(archive),Criteria.where("shared").is(true).and("sharedForUsername").is(username)));
	   List<Note> notes = mongoTempate.find(query,Note.class,"notes");
	   return notes;
   }	
   
   public void deleteOneNote(Long noteId) {
	   mongoTempate.remove(Query.query(Criteria.where("noteId").is(noteId)), Note.class,"notes");
   }	
   
   public void dropAllNotes() {
	   mongoTempate.dropCollection("notes");
   }  
   
   public Note findById(Long id) {
	   Note note = mongoTempate.findOne(Query.query(Criteria.where("noteId").is(id)), Note.class,"notes");
	   return note;	   
   }	

   
   public void setCheckedItem(Long noteId, Long itemId, Boolean checked) {
	   Note note = mongoTempate.findOne(Query.query(Criteria.where("noteId").is(noteId)), Note.class,"notes");
       for(NoteItem item: note.getItems()) {
    	   if(itemId.equals(item.getItemId())) {
    		   item.setChecked(checked);
    		   mongoTempate.save(note);
    		   break;
    	   }
       }
       
   }	   
   
}
