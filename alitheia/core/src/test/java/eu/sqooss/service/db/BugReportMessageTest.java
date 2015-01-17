package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BugReportMessageTest extends DBTestSuperDoHQL2<BugReportMessage> {

	@Mock private Bug bug;
	@Mock private Developer dev;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new BugReportMessage());
	}

	@Test
	public void testID() {
		super.testSetId();
	}
	
	@Test
	public final void testBugReportMessageBug() {
		BugReportMessage brMessage = new BugReportMessage(bug);
		assertEquals(bug, brMessage.getBug());
	}

	@Test
	public final void testSetBug() {
		getObjectUnderTest().setBug(bug);
		assertEquals(bug, getObjectUnderTest().getBug());
	}

	@Test
	public final void testSetReporter() {
		getObjectUnderTest().setReporter(dev);
		assertEquals(dev, getObjectUnderTest().getReporter());
	}

	@Test
	public final void testSetTimestamp() {
		Date timestamp = new Date();
		getObjectUnderTest().setTimestamp(timestamp);
		assertEquals(timestamp, getObjectUnderTest().getTimestamp());
	}

	@Test
	public final void testSetText() {
		String test = "testMessage";
		getObjectUnderTest().setText(test);
		assertEquals(test, getObjectUnderTest().getText());
	}

	private BugReportMessage setUpBugReportMessage(long bugId, long pId) {
		Bug b = Mockito.mock(Bug.class);
		StoredProject p = Mockito.mock(StoredProject.class);
		
		Mockito.doReturn(p).when(b).getProject();
		Mockito.doReturn(Long.toString(bugId)).when(b).getBugID();
		Mockito.doReturn(pId).when(p).getId();
		return new BugReportMessage(b);
	}
	
	@Test // Test broken as long as equals is.
	public final void testEquals() {
		BugReportMessage brm1 = setUpBugReportMessage(0, 0);
		BugReportMessage brm2 = null;
		Date d1 = new Date();
		Date d2 = new Date();
		brm1.setTimestamp(d1);
		assertFalse(brm1.equals(brm2));
	}

	@Test
	public final void testEquals_2() throws InterruptedException {
		BugReportMessage brm1 = setUpBugReportMessage(0, 0);
		BugReportMessage brm2 = setUpBugReportMessage(0, 0);
		Date d1 = new Date();
		Thread.sleep(1);
		Date d2 = new Date();
		brm1.setTimestamp(d1);
		brm2.setTimestamp(d2);
		assertFalse(brm1.equals(brm2));
	}
	
	@Test
	public final void testEquals_3() throws InterruptedException {
		BugReportMessage brm1 = setUpBugReportMessage(0, 0);
		BugReportMessage brm2 = setUpBugReportMessage(0, 1);
		Date d1 = new Date();
		Date d2 = d1;
		brm1.setTimestamp(d1);
		brm2.setTimestamp(d2);
		assertFalse(brm1.equals(brm2));
	}
	
	@Test
	public final void testEquals_4() throws InterruptedException {
		BugReportMessage brm1 = setUpBugReportMessage(0, 0);
		BugReportMessage brm2 = setUpBugReportMessage(1, 0);
		Date d1 = new Date();
		Date d2 = d1;
		brm1.setTimestamp(d1);
		brm2.setTimestamp(d2);
		assertFalse(brm1.equals(brm2));
	}
	
	@Test
	public final void testEquals_Equal() throws InterruptedException {
		BugReportMessage brm1 = setUpBugReportMessage(0, 0);
		BugReportMessage brm2 = setUpBugReportMessage(0, 0);
		Date d1 = new Date();
		Thread.sleep(1);
		Date d2 = d1;
		brm1.setTimestamp(d1);
		brm2.setTimestamp(d2);
		assertTrue(brm1.equals(brm2));
	}
	
	@Test
	public final void testGetAllBugReportsForBugBug() {
		super.testGetStaticDoHQL2_ShouldBeNull();
		assertEquals(0, BugReportMessage.getAllReportMessageForBug(bug).size());
	}
	
	@Test
	public final void testGetAllBugReportsForBugBug_2() {
		super.testGetStaticDoHQL2_ShouldBeObject();
		assertEquals(getObjectUnderTest(), BugReportMessage.getAllReportMessageForBug(bug).get(0));
	}
	
	@Test
	public final void testGetAllDeveloperReportsForDeveloperDeveloper() {
		super.testGetStaticDoHQL2_ShouldBeNull();
		assertEquals(0, BugReportMessage.getAllReportMessageForDeveloper(dev).size());
	}
	
	@Test
	public final void testGetAllDeveloperReportsForDeveloperDeveloper_2() {
		super.testGetStaticDoHQL2_ShouldBeObject();
		assertEquals(getObjectUnderTest(), BugReportMessage.getAllReportMessageForDeveloper(dev).get(0));
	}
	
	

}
