package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class BugStatusTest extends DBTestSuperObjectProp<BugStatus> {

	BugStatus.Status stat;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new BugStatus());
		stat = BugStatus.Status.ASSIGNED;
	}
	
	public void testID() {
		super.testSetId();
	}

	@Test
	public final void testSetStatus() {
		String status = "StatusString";
		getObjectUnderTest().setStatus(status);
		assertEquals(status, getObjectUnderTest().getStatus());
	}

	@Test
	public final void testSetBugStatus() {
		getObjectUnderTest().setBugStatus(stat);
		assertEquals(stat, getObjectUnderTest().getBugStatus());
	}

	@Test
	public final void testGetBugStatusStatus() {
		super.testGetObjectProp_Null_1();
		assertNull(BugStatus.getBugStatus(stat));
	}
	
	@Test
	public final void testGetBugStatusStatus_2() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), BugStatus.getBugStatus(stat.toString(), false));
	}
	
	@Test
	public final void testGetBugStatusStatus_3() {
		super.testGetObjectProp_Null_1();
		assertNull(BugStatus.getBugStatus(stat.toString(), false));
	}
	
	@Test
	public final void testGetBugStatusStatus_3_5() {
		super.testGetObjectProp_Null_1();
		assertNull(BugStatus.getBugStatus(null, true));
	}
	
	@Test
	public final void testGetBugStatusStatus_4() {
		super.testGetObjectProp_Null_2();
		assertNull(BugStatus.getBugStatus("hallo", false));
	}
	
	@Test
	public final void testGetBugStatusStatus_5() {
		super.testGetObjectProp_Equals_2();
		assertEquals("hallo",BugStatus.getBugStatus("hallo", true).getStatus());
	}

	private void testOne(BugStatus.Status stat) {
		assertEquals(stat, BugStatus.Status.fromString(stat.toString()));
	}
	
	@Test
	public void testFromString_1() {
		testOne(BugStatus.Status.ASSIGNED);
	}
	
	@Test
	public void testFromString_2() {
		testOne(BugStatus.Status.CLOSED);
	}
	
	@Test
	public void testFromString_3() {
		testOne(BugStatus.Status.NEW);
	}
	
	@Test
	public void testFromString_4() {
		testOne(BugStatus.Status.REOPENED);
	}
	
	@Test
	public void testFromString_5() {
		testOne(BugStatus.Status.RESOLVED);
	}
	
	@Test
	public void testFromString_6() {
		testOne(BugStatus.Status.UNCONFIRMED);
	}
	
	@Test
	public void testFromString_7() {
		testOne(BugStatus.Status.UNKNOWN);
	}

	@Test
	public void testFromString_8() {
		testOne(BugStatus.Status.VERIFIED);
	}
}
