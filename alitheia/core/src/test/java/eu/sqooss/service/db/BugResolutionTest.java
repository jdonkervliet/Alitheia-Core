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
import org.mockito.MockitoAnnotations;

import eu.sqooss.service.db.BugResolution.Resolution;
import eu.sqooss.service.db.BugResolution.Resolution;

public class BugResolutionTest extends DBTestSuperObjectProp<BugResolution> {
	
	BugResolution.Resolution res;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new BugResolution());
		res = Resolution.WONTFIX;
	}
	
	public void testID() {
		super.testSetId();
	}

	@Test
	public final void testSetResolution() {
		String resolution = "solved it";
		getObjectUnderTest().setResolution(resolution);
		assertEquals(resolution, getObjectUnderTest().getResolution());
	}

	@Test
	public final void testSetBugResolution() {
		getObjectUnderTest().setBugResolution(res);
		assertEquals(res, getObjectUnderTest().getBugResolution());
	}

	@Test
	public final void testGetBugResolutionResolution() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), BugResolution.getBugResolution(res));
	}

	@Test
	public final void testGetBugResolutionStringBoolean() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), BugResolution.getBugResolution("low", false));
	}
	
	@Test
	public final void testGetBugResolutionStringBoolean_2() {
		super.testGetObjectProp_Null_2();
		assertNull(BugResolution.getBugResolution("low", false));
	}
	
	@Test
	public final void testGetBugResolutionStringBoolean_3() {
		super.testGetObjectProp_Null_2();
		assertNull(BugResolution.getBugResolution(null, true));
	}
	
	@Test
	public final void testGetBugResolutionStringBoolean_4() {
		super.testGetObjectProp_Null_2();
		assertNull(BugResolution.getBugResolution("low", true));
	}
	
	@Test
	public final void testGetBugResolutionStringBoolean_5() {
		super.testGetObjectProp_Equals_2();
		assertEquals("low", BugResolution.getBugResolution("low", true).getResolution());
	}

	@Test
	public final void testFromString_1() {
		assertEquals(BugResolution.Resolution.DUPLICATE, BugResolution.Resolution.fromString("duplicate"));
	}
	
	@Test
	public final void testFromString_2() {
		assertEquals(BugResolution.Resolution.FIXED, BugResolution.Resolution.fromString("fixed"));
	}
	
	@Test
	public final void testFromString_3() {
		assertEquals(BugResolution.Resolution.INVALID, BugResolution.Resolution.fromString("invalid"));
	}
	
	@Test
	public final void testFromString_4() {
		assertEquals(BugResolution.Resolution.MOVED, BugResolution.Resolution.fromString("moved"));
	}
	
	@Test
	public final void testFromString_5() {
		assertEquals(BugResolution.Resolution.UNKNOWN, BugResolution.Resolution.fromString("unknown"));
	}
	
	@Test
	public final void testFromString_6() {
		assertEquals(BugResolution.Resolution.WONTFIX, BugResolution.Resolution.fromString("wontfix"));
	}

	@Test
	public final void testFromString_7() {
		assertEquals(BugResolution.Resolution.WORKSFORME, BugResolution.Resolution.fromString("worksforme"));
	}
}
