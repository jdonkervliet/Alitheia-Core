package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import eu.sqooss.service.tds.PathChangeType;

public class ProjectFileStateTest extends DBTestSuper<ProjectFileState> {

	@Before
	public void setUp() throws Exception {
		super.setUp(new ProjectFileState());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testToString() {
		assertNull(getObjectUnderTest().toString());
	}

	@Test
	public final void testToString_2() {
		getObjectUnderTest().setStatus(ProjectFileState.STATE_ADDED);
		setUpFromStatus(getObjectUnderTest());
		assertEquals("ADDED", ProjectFileState.added().toString());
	}

	@Test
	public final void testToString_3() {
		getObjectUnderTest().setStatus(ProjectFileState.STATE_MODIFIED);
		setUpFromStatus(getObjectUnderTest());
		assertEquals("MODIFIED", ProjectFileState.modified().toString());
	}

	@Test
	public final void testToString_4() {
		getObjectUnderTest().setStatus(ProjectFileState.STATE_DELETED);
		setUpFromStatus(getObjectUnderTest());
		assertEquals("DELETED", ProjectFileState.deleted().toString());
	}

	@Test
	public final void testToString_5() {
		getObjectUnderTest().setStatus(ProjectFileState.STATE_REPLACED);
		setUpFromStatus(getObjectUnderTest());
		assertEquals("REPLACED", ProjectFileState.replaced().toString());
	}

	@Test
	public final void testFromStatus() {
		super.setUpMockedDBService();
		assertNull(ProjectFileState.fromStatus(0));
	}

	@Test
	public final void testFromStatus_2() {
		setUpFromStatus(getObjectUnderTest());
		assertEquals(getObjectUnderTest(), ProjectFileState.fromStatus(0));
	}

	@Test
	public final void testFromStatus_3() {
		super.setUpMockedDBService();
		Mockito.doReturn(true).when(super.dbService).isDBSessionActive();
		List<ProjectFileState> list = new ArrayList<ProjectFileState>();
		list.add(getObjectUnderTest());
		Mockito.doReturn(new ArrayList<ProjectFileState>())
				.doReturn(list)
				.when(dbService)
				.findObjectsByProperties((Class<DAObject>) Matchers.any(),
						Matchers.anyMap());
		assertEquals(getObjectUnderTest(), ProjectFileState.fromStatus(0));
	}

	private void setUpFromStatus(ProjectFileState object) {
		super.setUpMockedDBService();
		Mockito.doReturn(true).when(super.dbService).isDBSessionActive();
		List<ProjectFileState> list = new ArrayList<ProjectFileState>();
		list.add(object);
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
	}

	@Test
	public final void testFromPathChangeType() {
		assertNull(ProjectFileState.fromPathChangeType(PathChangeType.UNKNOWN));
	}

	@Test
	public final void testFromPathChangeType_2() {
		getObjectUnderTest().setStatus(ProjectFileState.STATE_MODIFIED);
		setUpFromStatus(getObjectUnderTest());
		assertEquals(ProjectFileState.modified(),
				ProjectFileState.fromPathChangeType(PathChangeType.MODIFIED));
	}
	
	@Test
	public final void testFromPathChangeType_3() {
		getObjectUnderTest().setStatus(ProjectFileState.STATE_DELETED);
		setUpFromStatus(getObjectUnderTest());
		assertEquals(ProjectFileState.deleted(),
				ProjectFileState.fromPathChangeType(PathChangeType.DELETED));
	}
	
	@Test
	public final void testFromPathChangeType_4() {
		getObjectUnderTest().setStatus(ProjectFileState.STATE_ADDED);
		setUpFromStatus(getObjectUnderTest());
		assertEquals(ProjectFileState.added(),
				ProjectFileState.fromPathChangeType(PathChangeType.ADDED));
	}
	
	@Test
	public final void testFromPathChangeType_5() {
		getObjectUnderTest().setStatus(ProjectFileState.STATE_REPLACED);
		setUpFromStatus(getObjectUnderTest());
		assertEquals(ProjectFileState.replaced(),
				ProjectFileState.fromPathChangeType(PathChangeType.REPLACED));
	}
	

	@Test
	public final void testSetStatus() {
		getObjectUnderTest().setStatus(12);
		assertEquals(12, getObjectUnderTest().getStatus());
	}


}
