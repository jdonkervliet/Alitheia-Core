package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import eu.sqooss.service.logging.Logger;

public class ProjectFileTest extends DBTestSuperDoHQL2<ProjectFile> {

	@Mock ProjectFileState state;
	@Mock Directory dir;
	@Mock ProjectVersion pv;
	@Mock StoredProject sp;
	
	String name = "name";
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new ProjectFile());
	}

	@Test
	public final void testHashCode() {
		ProjectFile a = new ProjectFile();
		a.setName(name);
		getObjectUnderTest().setName(name);
		assertEquals(a.hashCode(), getObjectUnderTest().hashCode());
	}
	
	@Test
	public final void testHashCode_2() {
		getObjectUnderTest().setName(name);
		assertEquals(getObjectUnderTest().hashCode(), getObjectUnderTest().hashCode());
	}
	
	@Test
	public final void testHashCode_3() {
		ProjectFile a = new ProjectFile();
		a.setName(name);
		getObjectUnderTest().setState(state);
		getObjectUnderTest().setName(name);
		assertFalse(a.hashCode() == getObjectUnderTest().hashCode());
	}
	
	@Test
	public final void testHashCode_4() {
		ProjectFile a = new ProjectFile();
		a.setName(name);		
		getObjectUnderTest().setDir(dir);
		getObjectUnderTest().setName(name);
		assertFalse(a.hashCode() == getObjectUnderTest().hashCode());
	}
	
	@Test
	public final void testHashCode_5() {
		ProjectFile a = new ProjectFile();
		a.setName(name);		
		getObjectUnderTest().setProjectVersion(pv);
		getObjectUnderTest().setName(name);
		assertFalse(a.hashCode() == getObjectUnderTest().hashCode());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testProjectFileProjectVersion() {
		ProjectFile pf = new ProjectFile(pv);
		assertEquals(pv, pf.getProjectVersion());
	}

	@Test
	public final void testProjectFileProjectFileProjectVersion() {
		Mockito.doReturn(sp).when(pv).getProject();
		getObjectUnderTest().setProjectVersion(pv);
		getObjectUnderTest().setName("name");
		getObjectUnderTest().setIsDirectory(false);
		getObjectUnderTest().setDir(dir);
		getObjectUnderTest().setState(state);
		ProjectFile pf = new ProjectFile(getObjectUnderTest(), pv);
		assertFalse(pf.equals(getObjectUnderTest()));
		assertEquals(pf.getIsDirectory(), getObjectUnderTest().getIsDirectory());
		assertEquals(pf.getName(), getObjectUnderTest().getName());
		assertEquals(pf.getDir(), getObjectUnderTest().getDir());
	}
	
	@Test
	public final void testProjectFileProjectFileProjectVersion_2() {
		Mockito.doReturn(sp).when(pv).getProject();
		Mockito.doReturn(1L).when(sp).getId();
		
		StoredProject sp2 = Mockito.mock(StoredProject.class);
		ProjectVersion pv2 = Mockito.mock(ProjectVersion.class);
		Mockito.doReturn(sp2).when(pv2).getProject();
		Mockito.doReturn(2L).when(sp2).getId();
		
		getObjectUnderTest().setProjectVersion(pv);
		boolean caught = false;
		try {
			new ProjectFile(getObjectUnderTest(), pv2);
		} catch(IllegalArgumentException e) {
			caught = true;
		}
		assertTrue(caught);
	}

	@Test
	public final void testSetName() {
		getObjectUnderTest().setName(name);
		assertEquals(name, getObjectUnderTest().getName());
	}

	@Test
	public final void testSetProjectVersion() {
		getObjectUnderTest().setProjectVersion(pv);
		assertEquals(pv, getObjectUnderTest().getProjectVersion());
	}

	@Test
	public final void testSetState() {
		getObjectUnderTest().setState(state);
		assertEquals(state, getObjectUnderTest().getState());
	}

	@Test
	public final void testSetModule() {
		getObjectUnderTest().setModule(true);
		assertTrue(getObjectUnderTest().isModule());
	}

	@Test
	public final void testSetIsDirectory() {
		getObjectUnderTest().setIsDirectory(true);
		assertTrue(getObjectUnderTest().getIsDirectory());
	}
	
	@Test
	public final void testSetDirectory() {
		getObjectUnderTest().setDirectory(true);
		assertTrue(getObjectUnderTest().getIsDirectory());
	}
	
	
	@Test
	public final void testIsDeleted() {
		Mockito.doReturn(ProjectFileState.STATE_DELETED).when(state).getStatus();
		getObjectUnderTest().setState(state);
		assertTrue(getObjectUnderTest().isDeleted());
		assertFalse(getObjectUnderTest().isAdded());
	}
	
	@Test
	public final void testIsAdded() {
		Mockito.doReturn(ProjectFileState.STATE_ADDED).when(state).getStatus();
		getObjectUnderTest().setState(state);
		assertTrue(getObjectUnderTest().isAdded());
		assertFalse(getObjectUnderTest().isModified());
	}
	
	
	@Test
	public final void testIsModified() {
		Mockito.doReturn(ProjectFileState.STATE_MODIFIED).when(state).getStatus();
		getObjectUnderTest().setState(state);
		assertTrue(getObjectUnderTest().isModified());
		assertFalse(getObjectUnderTest().isReplaced());
	}
	
	
	@Test
	public final void testIsReplaced() {
		Mockito.doReturn(ProjectFileState.STATE_REPLACED).when(state).getStatus();
		getObjectUnderTest().setState(state);
		assertTrue(getObjectUnderTest().isReplaced());
		assertFalse(getObjectUnderTest().isDeleted());
	}

	@Test
	public final void testSetDir() {
		getObjectUnderTest().setDir(dir);
		assertEquals(dir, getObjectUnderTest().getDir());
	}

	@Test
	public final void testSetValidFrom() {
		getObjectUnderTest().setValidFrom(pv);
		assertEquals(pv, getObjectUnderTest().getValidFrom());
	}

	@Test
	public final void testSetValidUntil() {
		getObjectUnderTest().setValidUntil(pv);
		assertEquals(pv, getObjectUnderTest().getValidUntil());
	}

	@Test
	public final void testSetCopyFrom() {
		getObjectUnderTest().setCopyFrom(getObjectUnderTest());
		assertEquals(getObjectUnderTest(), getObjectUnderTest().getCopyFrom());
	}

	@Test
	public final void testSetMeasurements() {
		Set<ProjectFileMeasurement> measurements = new HashSet<ProjectFileMeasurement>();
		getObjectUnderTest().setMeasurements(measurements);
		assertEquals(measurements, getObjectUnderTest().getMeasurements());
	}

	@Test
	public final void testSetEncapsulationUnits() {
		Set<EncapsUnit> encapsulationUnits = new HashSet<EncapsUnit>();
		getObjectUnderTest().setEncapsulationUnits(encapsulationUnits);
		assertEquals(encapsulationUnits, getObjectUnderTest().getEncapsulationUnits());
	}
	
	@Test
	public final void testSetEncapsulationUnits_2() {
		Set<EncapsUnit> encapsulationUnits = null;
		getObjectUnderTest().setEncapsulationUnits(encapsulationUnits);
		assertFalse(getObjectUnderTest().getEncapsulationUnits() == null);
	}

	@Test
	public final void testSetExecutionUnits() {
		Set<ExecUnit> executionUnits = new HashSet<ExecUnit>();
		getObjectUnderTest().setExecutionUnits(executionUnits);
		assertEquals(executionUnits, getObjectUnderTest().getExecutionUnits());
	}
	
	@Test
	public final void testSetExecutionUnits_2() {
		Set<ExecUnit> executionUnits = null;
		getObjectUnderTest().setExecutionUnits(executionUnits);
		assertFalse(getObjectUnderTest().getExecutionUnits() == null);
	}

	@Test
	public final void testGetFileName() {
		getObjectUnderTest().setDir(dir);
		getObjectUnderTest().setName(name);
		Mockito.doReturn("path").when(dir).getPath();
		assertTrue(getObjectUnderTest().getFileName().contains(name));
		assertTrue(getObjectUnderTest().getFileName().contains(dir.getPath()));
	}
	
	@Test
	public final void testGetFileName_2() {
		getObjectUnderTest().setDir(dir);
		getObjectUnderTest().setName(name);
		Mockito.doReturn("path/").when(dir).getPath();
		assertTrue(getObjectUnderTest().getFileName().contains(name));
		assertTrue(getObjectUnderTest().getFileName().contains(dir.getPath()));
	}

	@Test
	public final void testToDirectory() {
		getObjectUnderTest().setIsDirectory(false);
		assertNull(getObjectUnderTest().toDirectory());
	}
	
	@Test
	public final void testToDirectory_2() {
		getObjectUnderTest().setIsDirectory(true);
		getObjectUnderTest().setDir(dir);
		getObjectUnderTest().setName(name);
		Mockito.doReturn("path/").when(dir).getPath();	
		List<Directory> list = new ArrayList<Directory>();
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertNull(getObjectUnderTest().toDirectory());
	}
	
	@Test
	public final void testToDirectory_3() {
		getObjectUnderTest().setIsDirectory(true);
		getObjectUnderTest().setDir(dir);
		getObjectUnderTest().setName(name);
		Mockito.doReturn("path/").when(dir).getPath();	
		List<Directory> list = null;
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertNull(getObjectUnderTest().toDirectory());
	}
	
	@Test
	public final void testToDirectory_4() {
		getObjectUnderTest().setIsDirectory(true);
		getObjectUnderTest().setDir(dir);
		getObjectUnderTest().setName(name);
		Mockito.doReturn("path/").when(dir).getPath();	
		List<Directory> list = new ArrayList<Directory>();
		list.add(dir);
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertEquals(dir, getObjectUnderTest().toDirectory());
	}

	@Test
	public final void testGetPreviousFileVersion() {
		super.setUpMockedDBService();
		Mockito.doReturn(ProjectFileState.STATE_ADDED).when(state).getStatus();
		getObjectUnderTest().setState(state);
		assertNull(getObjectUnderTest().getPreviousFileVersion());
	}
	
	@Test
	public final void testGetPreviousFileVersion_2() {
		List<ProjectFile> list = new ArrayList<ProjectFile>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		
		setUpATonOfBasicInformation();
		
		assertNull(getObjectUnderTest().getPreviousFileVersion());
	}
	
	@Test
	public final void testGetPreviousFileVersion_3() {
		List<ProjectFile> list = new ArrayList<ProjectFile>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		
		getObjectUnderTest().setCopyFrom(getObjectUnderTest());
		setUpATonOfBasicInformation();
		
		assertEquals(getObjectUnderTest(), getObjectUnderTest().getPreviousFileVersion());
	}	

	private void setUpATonOfBasicInformation() {
		Mockito.doReturn(ProjectFileState.STATE_MODIFIED).when(state).getStatus();
		getObjectUnderTest().setState(state);
		getObjectUnderTest().setDir(dir);
		getObjectUnderTest().setProjectVersion(pv);
		getObjectUnderTest().setName(name);
		Mockito.doReturn(sp).when(pv).getProject();
		Mockito.doReturn("path/").when(dir).getPath();	
		
		Logger logger = Mockito.mock(Logger.class);
		Mockito.doReturn(logger).when(super.dbService).logger();
	}

	@Test
	public final void testGetDeletionVersion() {
		super.setUpMockedDBService();
		Mockito.doReturn(ProjectFileState.STATE_DELETED).when(state).getStatus();
		getObjectUnderTest().setState(state);
		getObjectUnderTest().setProjectVersion(pv);
		
		assertEquals(pv, ProjectFile.getDeletionVersion(getObjectUnderTest()));
	}
	
	@Test
	public final void testGetDeletionVersion_2() {
		super.setUpMockedDBService();
		Mockito.doReturn(ProjectFileState.STATE_MODIFIED).when(state).getStatus();
		getObjectUnderTest().setState(state);
		this.setUpATonOfBasicInformation();
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(pv);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		
		assertEquals(pv, ProjectFile.getDeletionVersion(getObjectUnderTest()));
	}
	
	@Test
	public final void testGetDeletionVersion_3() {
		super.setUpMockedDBService();
		Mockito.doReturn(ProjectFileState.STATE_MODIFIED).when(state).getStatus();
		getObjectUnderTest().setState(state);
		getObjectUnderTest().setProjectVersion(null);
		this.setUpATonOfBasicInformation();
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		
		assertNull(ProjectFile.getDeletionVersion(getObjectUnderTest()));
	}

	@Test
	public final void testGetEnclosingDirectory() {
		this.setUpATonOfBasicInformation();
		List<ProjectFile> list = new ArrayList<ProjectFile>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertNull(getObjectUnderTest().getEnclosingDirectory());
	}
	
	@Test
	public final void testGetEnclosingDirectory_2() {
		this.setUpATonOfBasicInformation();
		List<ProjectFile> list = new ArrayList<ProjectFile>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(getObjectUnderTest(), getObjectUnderTest().getEnclosingDirectory());
	}

	@Test
	public final void testGetFileModifications() {
		this.setUpATonOfBasicInformation();
		List<ProjectFile> list = new ArrayList<ProjectFile>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(list, ProjectFile.getFileModifications(getObjectUnderTest()));
	}

	@Test
	public final void testFindFileLongStringStringString() {
		super.setUpMockedDBService();
		assertNull(ProjectFile.findFile(null, null, null, null));
	}
	
	@Test
	public final void testFindFileLongStringStringStringBoolean() {
		super.setUpMockedDBService();
		assertNull(ProjectFile.findFile(123L, null, null, null));
	}

	@Test
	public final void testFindFileLongStringStringStringBoolean_2() {
		this.setUpATonOfBasicInformation();
		List<ProjectFile> list = new ArrayList<ProjectFile>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(getObjectUnderTest(), ProjectFile.findFile(12L, "", "", "", true));
	}
	
	@Test
	public final void testFindFileLongStringStringStringBoolean_3() {
		this.setUpATonOfBasicInformation();
		List<ProjectFile> list = new ArrayList<ProjectFile>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertNull(ProjectFile.findFile(12L, "", "", "", false));
	}

	@Test
	public final void testGetChangedExecutionUnits() {
		List<ExecutionUnit> list = new ArrayList<ExecutionUnit>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(list, getObjectUnderTest().getChangedExecutionUnits());
	}

	@Test
	public final void testToString() {
		this.setUpATonOfBasicInformation();
		String s = getObjectUnderTest().toString();
		assertTrue(s.contains(dir.getPath()));
		assertTrue(s.contains(name));
		assertTrue(s.contains(state.toString()));
	}

	@Test
	public final void testEqualsObject() {
		assertFalse(getObjectUnderTest().equals(null));
	}
	
	@Test
	public final void testEqualsObject_2() {
		assertFalse(getObjectUnderTest().equals(123L));
	}
	
	@Test
	public final void testEqualsObject_3() {
		this.setUpATonOfBasicInformation();
		assertEquals(getObjectUnderTest(), getObjectUnderTest());
	}
	
	@Test
	public final void testGetAllMetricsByType() {
		super.testGetStaticDoHQL2_ShouldBeNull();
		assertEquals(0, ProjectFile.getProjectFilesByState(state).size());
	}
	
	@Test
	public final void testGetAllMetricsByType_2() {
		super.testGetStaticDoHQL2_ShouldBeObject();
		assertEquals(getObjectUnderTest(), ProjectFile.getProjectFilesByState(state).get(0));
	}


}
