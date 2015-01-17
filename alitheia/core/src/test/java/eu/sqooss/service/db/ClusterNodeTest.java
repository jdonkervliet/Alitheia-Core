package eu.sqooss.service.db;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import eu.sqooss.service.db.ClusterNode;
import eu.sqooss.service.db.StoredProject;


public class ClusterNodeTest extends DBTestSuperObjectProp<ClusterNode> {

	@Before
	public void setUp() throws Exception {
		super.setUp(new ClusterNode());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testClusterNodeString() {
		String servername = "hoi";
		ClusterNode cn = new ClusterNode(servername);
		assertEquals(servername, cn.getName());
	}

	@Test
	public final void testSetName() {
		String name = "hoi";
		getObjectUnderTest().setName(name);
		assertEquals(name, getObjectUnderTest().getName());
	}

	@Test
	public final void testSetProjects() {
		Set<Project> set = new HashSet<Project>();
		getObjectUnderTest().setProjects(set);
		assertEquals(set, getObjectUnderTest().getProjects());
	}

	@Test
	public final void testGetClusteNodeByName() {
		super.testGetObjectProp_Null_1();
		assertNull(ClusterNode.getClusteNodeByName(""));
	}
	
	@Test
	public final void testGetClusteNodeByName_2() {
		super.testGetObjectProp_Null_1();
		assertNull(ClusterNode.getClusteNodeByName(null));
	}
	
	@Test
	public final void testGetClusteNodeByName_3() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), ClusterNode.getClusteNodeByName(""));
	}
	
	@Test
	public final void testGetClusteNodeByName_4() {
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(null);
		assertNull(ClusterNode.getClusteNodeByName(""));
	}

	@Test
	public final void testThisNode() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), ClusterNode.thisNode());
	}

}
