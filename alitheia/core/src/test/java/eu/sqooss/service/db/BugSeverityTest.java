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
import org.mockito.Mockito;

/**
 * @author stefan
 *
 */
public class BugSeverityTest extends DBTestSuperObjectProp<BugSeverity> {

	BugSeverity.Severity sev;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp(new BugSeverity());
		sev = BugSeverity.Severity.BLOCKER;
	}
	
	public void testID() {
		super.testSetId();
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.BugSeverity#setSeverity(java.lang.String)}.
	 */
	@Test
	public final void testSetSeverity() {
		getObjectUnderTest().setSeverity(sev.toString());
		assertEquals(sev.toString(), getObjectUnderTest().getSeverity());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.BugSeverity#setBugseverity(eu.sqooss.service.db.BugSeverity.Severity)}.
	 */
	@Test
	public final void testSetBugseverity() {
		getObjectUnderTest().setBugseverity(sev);
		assertEquals(sev, getObjectUnderTest().getBugSeverity());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.BugSeverity#getBugseverity(eu.sqooss.service.db.BugSeverity.Severity)}.
	 */
	@Test
	public final void testGetBugseverity() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), BugSeverity.getBugseverity(sev));
	}

	@Test
	public final void testGetBugSeverityStringBoolean() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), BugSeverity.getBugSeverity("low", false));
	}
	
	@Test
	public final void testGetBugSeverityStringBoolean_2() {
		super.testGetObjectProp_Null_1();
		assertNull(BugSeverity.getBugSeverity("low", false));
	}
	
	@Test
	public final void testGetBugSeverityStringBoolean_3() {
		super.testGetObjectProp_Null_1();
		assertNull(BugSeverity.getBugSeverity(null, true));
	}
	
	@Test
	public final void testGetBugSeverityStringBoolean_4() {
		super.testGetObjectProp_Null_2();
		assertNull(BugSeverity.getBugSeverity("unknown priority", true));
	}
	
	@Test
	public final void testGetBugSeverityStringBoolean_5() {
		String priorityString = "This is an unknown priority!";
		super.testGetObjectProp_Equals_2();
		BugSeverity bugs = BugSeverity.getBugSeverity(priorityString, true);
		assertEquals(bugs.getSeverity(), priorityString);
	}
	

	@Test
	public final void testFromString_1() {
		testFromString(BugSeverity.Severity.BLOCKER);
	}
	
	@Test
	public final void testFromString_2() {
		testFromString(BugSeverity.Severity.CRITICAL);
	}
	
	@Test
	public final void testFromString_3() {
		testFromString(BugSeverity.Severity.ENHANCEMENT);
	}
	
	@Test
	public final void testFromString_4() {
		testFromString(BugSeverity.Severity.MAJOR);
	}
	
	@Test
	public final void testFromString_5() {
		testFromString(BugSeverity.Severity.MINOR);
	}
	
	@Test
	public final void testFromString_6() {
		testFromString(BugSeverity.Severity.NORMAL);
	}
	
	@Test
	public final void testFromString_7() {
		testFromString(BugSeverity.Severity.TRIVIAL);
	}
	
	@Test
	public final void testFromString_8() {
		testFromString(BugSeverity.Severity.UNKNOWN);
	}
	
	@Test
	public final void testFromString_9() {
		assertEquals(BugSeverity.Severity.UNKNOWN, BugSeverity.Severity.fromString("blah"));
	}
	
	private final void testFromString(BugSeverity.Severity sev) {
		assertEquals(sev, BugSeverity.Severity.fromString(sev.toString()));
	}

}
