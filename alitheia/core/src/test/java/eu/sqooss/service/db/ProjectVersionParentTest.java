package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ProjectVersionParentTest extends DBTestSuper<ProjectVersionParent> {

	ProjectVersionParent projectVersionParent;
	@Mock
	ProjectVersion child;
	@Mock
	ProjectVersion parent;
	@Mock
	ProjectVersionParentId projectVersionParentId;

	@Before
	public void setUp() throws Exception {
		projectVersionParent = new ProjectVersionParent();
		super.setUp(projectVersionParent);
	}

	@Test
	public void testSetIdLong() {
		super.testSetId();
	}

	@Test
	public void testProjectVersionParent() {
		assertNotNull(new ProjectVersionParent());
	}

	@Test
	public void testProjectVersionParentProjectVersionProjectVersion() {
		ProjectVersionParent other = new ProjectVersionParent(child, parent);
		assertEquals(child, other.getChild());
		assertEquals(parent, other.getParent());
	}

	@Test
	public void testSetParent() {
		projectVersionParent.setParent(parent);
		assertEquals(parent, projectVersionParent.getParent());
	}

	@Test
	public void testSetChild() {
		projectVersionParent.setChild(child);
		assertEquals(child, projectVersionParent.getChild());
	}

	@Test
	public void testSetIdProjectVersionParentId() {
		projectVersionParent.setId(projectVersionParentId);
		assertEquals(projectVersionParentId, projectVersionParent.getPk());
	}

}
