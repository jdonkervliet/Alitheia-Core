package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ProjectVersionParentIdTest {

	ProjectVersionParentId projectVersionParentId;

	@Before
	public void setUp() throws Exception {
		projectVersionParentId = new ProjectVersionParentId();
	}

	@Test
	public void testSetParentId() {
		Long parentid = new Long(1575);
		projectVersionParentId.setParentid(parentid);
		assertEquals(parentid, projectVersionParentId.getParentid());
	}

	@Test
	public void testSetChildId() {
		Long childid = new Long(42745);
		projectVersionParentId.setChildid(childid);
		assertEquals(childid, projectVersionParentId.getChildid());
	}

	@Test
	public void testEqualsTrue() {
		Long childid = new Long(427173);
		Long parentid = new Long(64073);
		ProjectVersionParentId other = new ProjectVersionParentId();
		projectVersionParentId.setChildid(childid);
		projectVersionParentId.setParentid(parentid);
		other.setChildid(childid);
		other.setParentid(parentid);
		assertEquals(projectVersionParentId, other);
	}

	@Test
	public void testEqualsFalse() {
		Long childid = new Long(4723);
		ProjectVersionParentId other = new ProjectVersionParentId();
		projectVersionParentId.setChildid(childid);
		assertFalse(projectVersionParentId.equals(other));
	}

	@Test
	public void testEqualsObject() {
		assertFalse(projectVersionParentId.equals(new ArrayList<Integer>()));
	}

	@Test
	public void testHashCodeTrue() {
		Long childid = new Long(427173);
		Long parentid = new Long(64073);
		ProjectVersionParentId other = new ProjectVersionParentId();
		projectVersionParentId.setChildid(childid);
		projectVersionParentId.setParentid(parentid);
		other.setChildid(childid);
		other.setParentid(parentid);
		assertEquals(projectVersionParentId.hashCode(), other.hashCode());
	}

	@Test
	public void testHashCodeFalse() {
		Long parentid = new Long(753);
		Long childid1 = new Long(4723);
		Long childid2 = new Long(4625);
		ProjectVersionParentId other = new ProjectVersionParentId();
		projectVersionParentId.setChildid(childid1);
		projectVersionParentId.setParentid(parentid);
		other.setChildid(childid2);
		other.setParentid(parentid);
		assertFalse(projectVersionParentId.hashCode() == other.hashCode());
	}
}
