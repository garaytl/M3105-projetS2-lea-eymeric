package fr.iutvalence.java.Notit;

import java.io.IOException;
import java.util.Set;

/**
 * The application.
 * @author G19
 */
public class Application {
	
	/**
	 * The current year.
	 */
	private int currentYear;
	
	/**
	 * The constructor.
	 * @throws IOException
	 */
	public Application() throws IOException{
		this.currentYear = new Date().get(Date.YEAR);
	}
	
	/**
	 * edit a existing day note.
	 * @param dayNote
	 * @param title
	 * @param content
	 * @throws IOException
	 */
	public void editDayNotes(DayNote dayNote, String title, String content) throws IOException{
		dayNote.setTitle(title);
		dayNote.setContent(content);
		dayNote.editNote();
	}
	
	/**
	 * create a new day note.
	 * @param title
	 * @param content
	 * @param date
	 * @throws IOException
	 */
	public void createDayNotes(String title, String content, Date date) throws IOException{
		String path = "Notes/"+date.getDate();
		editDayNotes(new DayNote(Path.maxFileInPath(path), title, content, date), title, content);
	}

	/**
	 * edit a existing general note.
	 * @param generalNote
	 * @param title
	 * @param content
	 * @throws IOException
	 */
	public void editGNotes(GeneralNote generalNote, String title, String content) throws IOException{
		generalNote.setTitle(title);
		generalNote.setContent(content);
		generalNote.editNote();
	}
	
	/**
	 * create a new general note.
	 * @param title
	 * @param content
	 * @throws IOException
	 */
	public void createGNotes(String title, String content) throws IOException{
		String path = "GeneralNotes";
		editGNotes(new GeneralNote(Path.maxFileInPath(path), title, content), title, content);
	}
	
	/**
	 * delete a day note.
	 * @param dayNote
	 * @throws IOException
	 */
	public void deleteDayNotes(DayNote dayNote) throws IOException{
		dayNote.deleteNote();
	}
	
	/**
	 * delete a general note.
	 * @param generalNote
	 * @throws IOException
	 */
	public void deleteGNotes(GeneralNote generalNote) throws IOException{
		generalNote.deleteNote();
	}
	
	/**
	 * get the list of general note.
	 * @return
	 * @throws IOException
	 */
	public Set<GeneralNote> getGeneralNote() throws IOException{
		return new NoteList().getlistOfGeneralNote();
	}
	
	/**
	 * get the list of day note.
	 * @return
	 * @throws IOException
	 */
	public Set<DayNote> getDayNote() throws IOException{
		return new NoteList(new Date()).getlistOfDayNote();
	}
	
	/**
	 * get the calendar of a month (list of date).
	 * @param monthNumber
	 * @return
	 * @throws IOException
	 */
	public Calendar getCalendar(int monthNumber) throws IOException{
		return new Calendar(monthNumber, this.currentYear);
	}
}
