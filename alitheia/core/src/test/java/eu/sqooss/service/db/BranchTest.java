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
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * @author stefan
 *
 */
public class BranchTest extends DBTestSuperDoHQL2<Branch> {

	@Mock
	private StoredProject project;
	@Mock
	private ProjectVersion projectVersion;
	private String name;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp(new Branch());
		name = "this is the name";
	}

	public void testID() {
		super.testSetId();
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.service.db.Branch#Branch(eu.sqooss.service.db.StoredProject, java.lang.String)}
	 * .
	 */
	@Test
	public final void testBranchStoredProjectString() {
		Branch branch = new Branch(project, name);
		assertEquals(project, branch.getProject());
		assertEquals(name, branch.getName());
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.service.db.Branch#setName(java.lang.String)}.
	 */
	@Test
	public final void testSetName() {
		getObjectUnderTest().setName(name);
		assertEquals(name, getObjectUnderTest().getName());
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.service.db.Branch#setProject(eu.sqooss.service.db.StoredProject)}
	 * .
	 */
	@Test
	public final void testSetProject() {
		getObjectUnderTest().setProject(project);
		assertEquals(project, getObjectUnderTest().getProject());
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.service.db.Branch#setBranchIncoming(java.util.Set)}.
	 */
	@Test
	public final void testSetBranchIncoming() {
		Set<Version> set = new HashSet<Version>();
		getObjectUnderTest().setBranchIncoming(set);
		assertEquals(set, getObjectUnderTest().getBranchIncoming());
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.service.db.Branch#setBranchIncoming(java.util.Set)}.
	 */
	@Test
	public final void testSetBranchIncoming_2() {
		Set<Version> set = new HashSet<Version>();
		set.add(projectVersion);
		getObjectUnderTest().setBranchIncoming(set);
		assertEquals(set, getObjectUnderTest().getBranchIncoming());
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.service.db.Branch#setBranchOutgoing(java.util.Set)}.
	 */
	@Test
	public final void testSetBranchOutgoing() {
		Set<Version> set = new HashSet<Version>();
		getObjectUnderTest().setBranchOutgoing(set);
		assertEquals(set, getObjectUnderTest().getBranchOutgoing());
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.service.db.Branch#setBranchOutgoing(java.util.Set)}.
	 */
	@Test
	public final void testSetBranchOutgoing_2() {
		Set<Version> set = new HashSet<Version>();
		set.add(projectVersion);
		getObjectUnderTest().setBranchOutgoing(set);
		assertEquals(set, getObjectUnderTest().getBranchOutgoing());
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.service.db.Branch#fromName(eu.sqooss.service.db.StoredProject, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testFromName() {
		super.testGetStaticDoHQL2_ShouldBeNull();
		assertNull(Branch.fromName(project, name, false));
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.service.db.Branch#fromName(eu.sqooss.service.db.StoredProject, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testFromName_2() {
		List<Branch> list = new ArrayList<Branch>();
		this.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		List<Branch> list2 = new ArrayList<Branch>();
		list2.add(getObjectUnderTest());
		Mockito.doReturn(list).doReturn(list2).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		assertEquals(getObjectUnderTest(), Branch.fromName(project, name, true));
		Mockito.verify(dbService).addRecord((DAObject) Matchers.any());
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.service.db.Branch#fromName(eu.sqooss.service.db.StoredProject, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testFromName_3() {
		List<Branch> list = new ArrayList<Branch>();
		list.add(getObjectUnderTest());
		this.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(getObjectUnderTest(), Branch.fromName(project, name, true));
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.service.db.Branch#suggestName(eu.sqooss.service.db.StoredProject)}
	 * .
	 */
	@Test
	public final void testSuggestName() {
		List<Long> list = new ArrayList<Long>();
		this.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals("1", Branch.suggestName(project));
	}

	@Test
	public final void testSuggestName_2() {
		List<Long> list = new ArrayList<Long>();
		list.add(new Long(1231));
		this.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals("1232", Branch.suggestName(project));
	}

}
