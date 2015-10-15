package JUnit;

import junit.framework.*;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(EditNoteTest.suite());
		
		return suite;
	}
}
