package fr.iutvalence.java.Notit;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Set;

/**
 * The description of a date (year, month, day).
 * 
 * @author G19
 */

public class Date extends GregorianCalendar
{

	/**
	 * A list of note.
	 */
	private Set<DayNote> listOfNote;
	
	/**
	 * The name of the day.
	 */
	private String dayName;
	
	/**
	 * The name of the month.
	 */
	private String monthName;

	/**
	 * The Date's constructor.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	public Date(int year, int month, int day) throws IOException
	{
		super(year, month, day);
		this.listOfNote = new NoteList(this).getlistOfDayNote();
		this.searchName();
	}

	/**
	 * The Date's constructor to know the date of the day.
	 */
	public Date() throws IOException
	{
		super();
		this.listOfNote = new NoteList(this).getlistOfDayNote();
		this.searchName();
	}
	
	/**
	 * Define the day and month name.
	 */
	private void searchName()
	{
		DateFormatSymbols formatSymbol = new DateFormatSymbols(Locale.ENGLISH); // elementInYear = English date format
		String[] daysName = formatSymbol.getWeekdays();
		this.dayName = daysName[this.get(DAY_OF_WEEK)];
		String[] monthsName = formatSymbol.getMonths();
		this.monthName = monthsName[this.get(MONTH)];
	}
	
	/**
	 * Update the list of note.
	 * @throws IOException
	 */
	public void updateListOfNote() throws IOException{
		this.listOfNote = new NoteList(this).getlistOfDayNote();
	}
	
	/**
	 * Getter to get the list of note
	 * @return Set<DayNote>
	 */
	public Set<DayNote> getListOfNote() {
		return listOfNote;
	}
	
	/**
	 * Get a String of the date (Wednesday 10 June 2015).
	 * @return String
	 */
	public String getEntireDate(){
		return this.dayName + " " + this.get(DAY_OF_MONTH) + " " + this.monthName + " " + this.get(YEAR);
	}
	
	/**
	 * Get a String of the date (2015-05-10).
	 * @return String
	 */
	public String getDate(){
		return this.get(YEAR) + "-" + this.get(MONTH) + "-" + this.get(DAY_OF_MONTH);
	}
	
	/**
	 * Displays the date and the list of date attached.
	 */
	public String toString()
	{
		return this.get(DAY_OF_MONTH) + "/" + this.get(MONTH) + "/"
				+ this.get(YEAR) + " : " + this.listOfNote.size();
	}
}
