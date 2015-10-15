package JUnit;

import java.io.IOException;

import fr.iutvalence.java.Notit.Date;
import fr.iutvalence.java.Notit.DayNote;
import fr.iutvalence.java.Notit.GeneralNote;
import junit.framework.*;

public class EditNoteTest extends TestCase {
	
	
	public EditNoteTest(String name) {
		super(name); 
	}

	
	/**
	 * Test editNote and setter for Day note.
	 * @throws IOException
	 */
	public void testEditNote() throws IOException {
		int id = 1;
		String title = "Creation Note1";
		String content = "This is the first note created";
		Date dateUsed = new Date(25,2,2015);//25-mars-2015
		DayNote theNote = new DayNote(id,title,content,dateUsed);
		
		//Test all parameter of theNote

		assertEquals(1,theNote.getNumber());
		assertEquals("Creation Note1",theNote.getTitle());
		assertEquals("This is the first note created",theNote.getContent());
		
		//Test after edit with setter
		
		theNote.setTitle("EditTitle");
		theNote.setContent("Edit Content");
		assertEquals("EditTitle",theNote.getTitle());
		assertEquals("Edit Content",theNote.getContent());
		
	}
	
	/**
	 * Test editNote and setter for General note.
	 * @throws IOException
	 */
	public void testEditGNote() throws IOException {
		int id = 1;
		String title = "Creation GNote1";
		String content = "This is the first General note created";
		GeneralNote theNote = new GeneralNote(id,title,content);
		
		//Test all parameter of theNote

		assertEquals(1,theNote.getNumber());
		assertEquals("Creation GNote1",theNote.getTitle());
		assertEquals("This is the first General note created",theNote.getContent());
		
		//Test after edit with setter
		
		theNote.setTitle("EditGTitle");
		theNote.setContent("Edit GContent");
		assertEquals("EditGTitle",theNote.getTitle());
		assertEquals("Edit GContent",theNote.getContent());
		
	}
	
	
	public static Test suite() {
	  TestSuite suite= new TestSuite();
	  suite.addTest(new EditNoteTest("testEditNote"));
	  suite.addTest(new EditNoteTest("testEditGNote"));
	  return suite;
	}


}
