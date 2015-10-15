package fr.iutvalence.java.Notit;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * A list of Note.
 * 
 * @author G19
 *
 */
public class NoteList
{
	private Set<DayNote> listOfDayNote;
	private Set<GeneralNote> listOfGeneralNote;

	/**
	 * The NoteList's constructor who recover the list of general note.
	 * 
	 * @throws IOException
	 */
	public NoteList() throws IOException
	{
		String temporaryIdOfTheNote = "";
		int id = 0;
		String title = "";
		String content = "";
		this.listOfDayNote = new HashSet<DayNote>();
		this.listOfGeneralNote = new HashSet<GeneralNote>();
		File[] files = arrayOfGeneralNote();
		if (files != null){
			for (int index = 0; index < files.length; index++)
			{
				temporaryIdOfTheNote = "";
				id = 0;
				title = "";
				content = "";
				FileReader flux = new FileReader(files[index]);
				int letter;
				temporaryIdOfTheNote = reader(temporaryIdOfTheNote, flux);
				id = Integer.parseInt(temporaryIdOfTheNote);
				title = reader(title, flux);
				while ((letter = flux.read()) != -1)
				{
					content += (char) letter;
				}
	
				this.listOfGeneralNote.add(new GeneralNote(id, title, content));
				flux.close();
			       }
		}
	}

	private String reader(String character, FileReader flux) throws IOException 
	{
		int letter;
		while ((letter = flux.read()) != 13)
		{
			character += (char) letter;
		}
		return character;
	}

	/**
	 * The NoteList's constructor who recover the list of note of a day.
	 * 
	 * @param date
	 * @throws IOException
	 */
	public NoteList(Date date) throws IOException
	{
		String temporaryIdOfTheNote = "";
		int id = 0;
		String title = "";
		String content = "";
		this.listOfDayNote = new HashSet<DayNote>();
		this.listOfGeneralNote = new HashSet<GeneralNote>();
		File[] files = arrayOfDayNote(date);
		if (files != null)
		{
			for (int index = 0; index < files.length; index++)
			{
				temporaryIdOfTheNote = "";
				id = 0;
				title = "";
				content = "";
				FileReader flux = new FileReader(files[index]);
				int lettre;
				while ((lettre = flux.read()) != 13){
					temporaryIdOfTheNote += (char) (lettre);
				}
				id = Integer.parseInt(temporaryIdOfTheNote);
				title = reader(title, flux);
				while ((lettre = flux.read()) != -1)
				{
					content += (char) lettre;
				}
				listOfDayNote.add(new DayNote(id, title, content, date));
				flux.close();
			}
		}
	}

	/**
	 * Board of general note.
	 * 
	 * @return files
	 */
	private File[] arrayOfGeneralNote()
	{
		File[] files = null;
		File repertoire = new File("GeneralNotes");
		files = repertoire.listFiles();
		return files;
	}

	/**
	 * board of note of a day.
	 * 
	 * @param date
	 * @return files
	 */
	private File[] arrayOfDayNote(Date date)
	{
		File[] files = null;
		String pathName = "Notes/"+date.getDate();
		File repertoire = new File(pathName);
		if (repertoire.exists())
		{
			files = repertoire.listFiles();
		}
		return files;
	}

	/**
	 * Getter to get list of general note.
	 * 
	 * @return listOfGeneralNote
	 */
	public Set<GeneralNote> getlistOfGeneralNote()
	{
		return this.listOfGeneralNote;
	}

	/**
	 * Getter to get list of note of a day.
	 * 
	 * @return listOfDayNote
	 */
	public Set<DayNote> getlistOfDayNote()
	{
		return this.listOfDayNote;
	}
}