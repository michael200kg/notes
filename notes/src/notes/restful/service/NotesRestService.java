package notes.restful.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import mongo.model.Note;
import mongo.service.NoteService;


//@CrossOrigin( methods={RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS,RequestMethod.HEAD}, 
//              origins = {"http://176.195.58.172:8888","http://localhost:8100"}, 
//            maxAge = 3600)	
@RestController
@RequestMapping("/service")
public class NotesRestService {
    
	@Autowired
	NoteService noteService;

	public static Comparator<Note> noteDateComparator
                     = new Comparator<Note>() {

         public int compare(Note note1, Note note2) {
            //descending order
            return note2.getCreatedDate().compareTo(note1.getCreatedDate());
         }

    };	
	
	@RequestMapping(value="/notes/generate_note_id",method=RequestMethod.GET)
	Long generateNoteId() {
		List<Note> notes = noteService.getAllNotes();
		int maxId=0;
		for(Note note: notes) {
		   if(note.getNoteId().intValue()>maxId) {
			   maxId=note.getNoteId().intValue();
		   }
		}
		return Long.valueOf(maxId+1);
    }    
    
	@RequestMapping(value="/notes/find_by_user/{user_name}",method=RequestMethod.GET)
	List<Note> getNotesByUsername(@PathVariable(value="user_name" )String username,
			                      @RequestParam(name="archive", defaultValue="false")Boolean archive ) {
		List<Note> notes = noteService.getNotesByUsername(username, archive);
		Collections.sort(notes, noteDateComparator);
		return notes;
    }
	
	
	@RequestMapping(value="/notes/find_by_id/{id}",method=RequestMethod.GET)
	Note getNoteById(@PathVariable(value="id" )Long id) {
		return noteService.findById(id);
    }	
	
	
	@RequestMapping(value="/notes/save_one_note",method=RequestMethod.POST)
	String saveOneNote(@RequestBody(required=true) Note instance) {
		noteService.saveOneNote(instance);
		return "OK";
    }	
//	                       /notes/3/item/3/set_checked_item/true
	@RequestMapping(value="/notes/{id}/item/{item_id}/set_checked_item/{checked}",method=RequestMethod.POST)
	String setCheckedItem(@PathVariable(value="id" ) Long id,
			              @PathVariable(value="item_id" ) Long itemId,
			              @PathVariable(value="checked" ) Boolean checked ) {
		noteService.setCheckedItem(id, itemId, checked );
		return "OK";
    }		
	
	@RequestMapping(value="/notes/{id}/set_archive/{archive}",method=RequestMethod.POST)
	String setArchiveItem(@PathVariable(value="id" ) Long id,
			              @PathVariable(value="archive" ) Boolean archive ) {
		Note note = noteService.findById(id);
		note.setArchive(archive);
		noteService.saveOneNote(note);		
		return "OK";
    }		
	
	@RequestMapping(value="/notes/{id}/delete_one_note",method=RequestMethod.DELETE)
	String deleteOneNote(@PathVariable(value="id") Long id) {
		noteService.deleteOneNote(id);
		return "OK";
    }	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> rulesForCustomerNotFound(HttpServletRequest req, Exception e) 
	{
	   System.out.println(e.getMessage());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}
// 176.195.58.172:8888