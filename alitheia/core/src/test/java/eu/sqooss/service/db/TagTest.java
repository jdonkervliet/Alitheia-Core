package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class TagTest extends DBTestSuper<Tag> {

	private Tag tag;

	@Mock
	private ProjectVersion projectVersion;

	private List<ProjectVersion> list;

	@Mock
	private StoredProject storedProject;

	@Before
	public void setUp() throws Exception {
		tag = new Tag();
		super.setUp(tag);

		list = new ArrayList<ProjectVersion>();
		list.add(projectVersion);
	}

	@Test
	public void testSetId() {
		super.testSetId();
	}

	@Test
	public void testSetProjectVersion() {
		tag.setProjectVersion(projectVersion);
		assertEquals(projectVersion, tag.getProjectVersion());
	}

	@Test
	public void testSetName() {
		String refname = "refname";
		tag.setName(refname);
		assertEquals(refname, tag.getName());
	}

	@Test
	public void testGetProjectVersionForNamedTag() {
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		Version test = Tag.getProjectVersionForNamedTag("", null);
		assertEquals(projectVersion, test);
	}

	@Test
	public void testGetTaggedVersions() {
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		List<Version> list = Tag.getTaggedVersions(storedProject);
		assertEquals(this.list, list);
	}

}
