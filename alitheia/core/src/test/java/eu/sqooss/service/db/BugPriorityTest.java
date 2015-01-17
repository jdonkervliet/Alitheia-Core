/**
 * 
 */
package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import eu.sqooss.core.AlitheiaCore;
import eu.sqooss.service.db.BugPriority.Priority;

/**
 * @author stefan
 *
 */
public class BugPriorityTest extends DBTestSuperObjectProp<BugPriority> {

	private Priority priority;
	
	@Mock private Bug bug;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp(new BugPriority());
		priority = BugPriority.Priority.LOW;
	}
	
	public void testID() {
		super.testSetId();
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.BugPriority#setpriority(java.lang.String)}.
	 */
	@Test
	public final void testSetpriority() {
		String priority = "this is not crucial";
		getObjectUnderTest().setpriority(priority);
		assertEquals(priority, getObjectUnderTest().getPriority());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.BugPriority#setBugPriority(eu.sqooss.service.db.BugPriority.Priority)}.
	 */
	@Test
	public final void testSetBugPriority() {
		getObjectUnderTest().setBugPriority(priority);
		assertEquals(priority, getObjectUnderTest().getBugPriority());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.BugPriority#setPriority(java.lang.String)}.
	 */
	@Test
	public final void testSetPriority() {
		String priority = "this is not crucial";
		getObjectUnderTest().setPriority(priority);
		assertEquals(priority, getObjectUnderTest().getPriority());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.BugPriority#getBugPriority(eu.sqooss.service.db.BugPriority.Priority)}.
	 */
	@Test
	public final void testGetBugPriorityPriority() {
		assertNull(getObjectUnderTest().getBugPriority());
	}
	
	/**
	 * Test method for {@link eu.sqooss.service.db.BugPriority#getBugPriority(eu.sqooss.service.db.BugPriority.Priority)}.
	 */
	@Test
	public final void testGetBugPriorityPriority_2() {
		getObjectUnderTest().setBugPriority(priority);
		assertEquals(priority, getObjectUnderTest().getBugPriority());
	}	

	/**
	 * Test method for {@link eu.sqooss.service.db.BugPriority#getBugPriority(java.lang.String, boolean)}.
	 */
	@Test
	public final void testGetBugPriorityStringBoolean() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), BugPriority.getBugPriority("low", false));
	}
	
	@Test
	public final void testGetBugPriorityStringBoolean_2() {
		super.testGetObjectProp_Null_1();
		assertNull(BugPriority.getBugPriority("low", false));
	}
	
	@Test
	public final void testGetBugPriorityStringBoolean_3() {
		super.testGetObjectProp_Null_1();
		assertNull(BugPriority.getBugPriority(null, true));
	}
	
	@Test
	public final void testGetBugPriorityStringBoolean_4() {
		super.testGetObjectProp_Null_2();
		assertNull(BugPriority.getBugPriority("unknown priority", true));
	}
	
	@Test
	public final void testGetBugPriorityStringBoolean_5() {
		String priorityString = "This is an unknown priority!";
		super.testGetObjectProp_Equals_2();
		BugPriority bp = BugPriority.getBugPriority(priorityString, true);
		assertEquals(bp.getPriority(), priorityString);
	}
	
	@Test
	public final void testGetBugPriorityStatic() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), BugPriority.getBugPriority(priority));
	}
	
	@Test
	public final void testGetBugPriorityStatic_Null() {
		assertNull(BugPriority.getBugPriority(null));
	}

	@Test
	public final void testFromString_Low() {
		assertEquals(Priority.LOW, Priority.fromString(Priority.LOW.toString()));
	}
	
	@Test
	public final void testFromString_Med() {
		assertEquals(Priority.MEDIUM, Priority.fromString(Priority.MEDIUM.toString()));
	}
	
	@Test
	public final void testFromString_High() {
		assertEquals(Priority.HIGH, Priority.fromString(Priority.HIGH.toString()));
	}
	
	@Test
	public final void testFromString_Unknown() {
		assertEquals(Priority.UNKNOWN, Priority.fromString(Priority.UNKNOWN.toString()));
	}
	
	@Test
	public final void testFromString_Unknown_2() {
		assertEquals(Priority.UNKNOWN, Priority.fromString("Unspecified"));
	}
	
	@Test
	public final void testFromString_Null() {
		assertNull(Priority.fromString(null));
	}
	
	
}
