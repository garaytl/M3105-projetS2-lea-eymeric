package fr.iutvalence.java.Notit;

import java.io.File;

/**
 * 
 * @author G19
 *
 */
public class Path {

	/**
	 * To check the path.
	 * 
	 * @param path
	 * @return boolean
	 */
	public static boolean checkPath(String path) {
		File file = new File(path);
		if (file.exists())
			return true;
		return false;
	}

	/**
	 * To create a path.
	 */
	public static void createPath(String path) {
		File file = new File(path);
		file.mkdirs();
	}
	
	/**
	 * To return the higher id of a note.  
	 * 
	 * @param path
	 * @return (max+1) the id for a new note
	 */
	public static int maxFileInPath(String path) {
		int max = 0;
		if(checkPath(path)){
			File repository = new File(path);
			File[] files = repository.listFiles();
			if(files.length > 0){
				max = (int) (files[0].getName().charAt(0) - 48);
				for (int index = 0; index < files.length; index++) {
					if (max < (int) (files[index].getName().charAt(0)-48))
						max = (int) (files[index].getName().charAt(0)-48);
				}
			}
		}
		max++;
		return max;
	}
}
