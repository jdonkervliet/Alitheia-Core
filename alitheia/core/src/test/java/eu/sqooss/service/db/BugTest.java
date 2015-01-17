package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
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

public class BugTest extends DBTestSuperDoHQL3<Bug> {

	@Mock private BugStatus status;
	@Mock private Developer reporter;
	@Mock private BugResolution resolution;
	@Mock private BugPriority priority;
	@Mock private BugSeverity severity;
	@Mock private BugReportMessage bugReportMessage;
	@Mock private StoredProject project;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new Bug());
	}
	
	public void testID() {
		super.testSetId();
	}

	@Test
	public void testSetBugID() {
		String bugID = "bugID";
		getObjectUnderTest().setBugID(bugID);
		assertEquals(bugID, getObjectUnderTest().getBugID());
	}

	
	@Test
	public void testSetBugID_2()
		throws Exception {
		String bugID = "";
		getObjectUnderTest().setBugID(bugID);
		assertEquals(bugID, getObjectUnderTest().getBugID());
	}

	@Test
	public void testSetStatus() {
		getObjectUnderTest().setStatus(status);
		assertEquals(status, getObjectUnderTest().getStatus());
	}
	
	@Test
	public void testSetCreationTS() {
		Date creationTS = new Date();
		getObjectUnderTest().setCreationTS(creationTS);
		assertEquals(creationTS, getObjectUnderTest().getCreationTS());
	}

	@Test
	public void testSetDeltaTS() {
		Date deltaTS = new Date();
		getObjectUnderTest().setDeltaTS(deltaTS);
		assertEquals(deltaTS, getObjectUnderTest().getDeltaTS());
	}

	@Test
	public void testSetReporter() {
		getObjectUnderTest().setReporter(reporter);
		assertEquals(reporter, getObjectUnderTest().getReporter());
	}

	@Test
	public void testSetResolution() {
		getObjectUnderTest().setResolution(resolution);
		assertEquals(resolution, getObjectUnderTest().getResolution());
	}

	@Test
	public void testSetPriority() {
		getObjectUnderTest().setPriority(priority);
		assertEquals(priority, getObjectUnderTest().getPriority());
	}

	@Test
	public void testSetSeverity() {
		getObjectUnderTest().setSeverity(severity);
		assertEquals(severity, getObjectUnderTest().getSeverity());
	}

	@Test
	public void testSetShortDesc() {
		String shortDesc = "this is a bug";
		getObjectUnderTest().setShortDesc(shortDesc);
		assertEquals(shortDesc, getObjectUnderTest().getShortDesc());
	}

	@Test
	public void testSetProject() {
		getObjectUnderTest().setProject(project);
		assertEquals(project, getObjectUnderTest().getProject());
	}

	@Test
	public void testSetUpdateRun() {
		Date updateRun = new Date();
		getObjectUnderTest().setUpdateRun(updateRun);
		assertEquals(updateRun, getObjectUnderTest().getUpdateRun());
	}

	@Test
	public void testGetLastUpdate() {
		super.testGetStaticDoHQL3_ShouldBeNull();
		assertNull(Bug.getLastUpdate(project));
	}

	@Test
	public void testGetLastUpdate_2() {
		super.testGetStaticDoHQL3_ShouldBeObject();
		assertEquals(getObjectUnderTest(), Bug.getLastUpdate(project));
	}
	
	@Test 
	public void testGetLastUpdate_Null() {
		super.testGetStaticDoHQL3_ShouldBeNull();
		assertNull(Bug.getLastUpdate(null));
	}

	@Test
	public void testGetStaticDoHQL3_ShouldBeNull() {
		super.testGetStaticDoHQL3_ShouldBeNull();
		assertNull(Bug.getBug("bugId", project));
	}

	@Test
	public void testGetStaticDoHQL3_ShouldBeObject() {
		super.testGetStaticDoHQL3_ShouldBeObject();
		assertEquals(getObjectUnderTest(), Bug.getBug("bugId", project));
	}
	
	@Test
	public void testGetBugsForPriorityPriority() {
		List<Bug> list = new ArrayList<Bug>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, Bug.getBugsForPriority(priority).size());
	}
	
	@Test
	public void testGetBugsForPriorityPriority_2() {
		List<Bug> list = new ArrayList<Bug>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(getObjectUnderTest(), Bug.getBugsForPriority(priority).get(0));
	}
	
	@Test
	public void testGetBugsForResolutionResolution() {
		List<Bug> list = new ArrayList<Bug>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, Bug.getBugsForResolution(resolution).size());
	}
	
	@Test
	public void testGetBugsForResolutionResolution_2() {
		List<Bug> list = new ArrayList<Bug>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(getObjectUnderTest(), Bug.getBugsForResolution(resolution).get(0));
	}
	
	@Test
	public void testGetBugsForSeveritySeverity() {
		List<Bug> list = new ArrayList<Bug>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, Bug.getBugsForSeverity(severity).size());
	}
	
	@Test
	public void testGetBugsForSeveritySeverity_2() {
		List<Bug> list = new ArrayList<Bug>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(getObjectUnderTest(), Bug.getBugsForSeverity(severity).get(0));
	}
	
	@Test
	public void testGetBugsForStatusStatus() {
		List<Bug> list = new ArrayList<Bug>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, Bug.getBugsForStatus(status).size());
	}
	
	@Test
	public void testGetBugsForStatusStatus_2() {
		List<Bug> list = new ArrayList<Bug>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(getObjectUnderTest(), Bug.getBugsForStatus(status).get(0));
	}
	
}
