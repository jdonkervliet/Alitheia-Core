package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ProjectVersionTest extends DBTestSuper<ProjectVersion> {

	ProjectVersion projectVersion;
	@Mock
	StoredProject storedProject;
	@Mock
	ProjectFile projectFile;
	@Mock
	Tag tag;
	@Mock
	ProjectVersionMeasurement projectVersionMeasurement;
	@Mock
	ProjectVersionParent projectVersionParent;
	@Mock
	Branch branch;
	@Mock
	private Metric metric;
	@Mock
	ProjectFileState pfs;
	@Mock
	Directory dir;

	@Before
	public void setUp() throws Exception {
		projectVersion = new ProjectVersion();
		super.setUp(projectVersion);
	}

	@Test
	public void testHashCodeFalse() {
		ProjectVersion other = new ProjectVersion();
		projectVersion.setCommitMsg("bla");
		assertFalse(projectVersion.equals(other));
	}

	@Test
	public void testHashCodeTrue() {
		ProjectVersion other = new ProjectVersion();
		assertFalse(projectVersion.equals(other));
	}

	@Test
	public void testSetId() {
		super.testSetId();
	}

	@Test
	public void testProjectVersion() {
		assertNotNull(new ProjectVersion());
	}

	@Test
	public void testProjectVersionStoredProject() {
		ProjectVersion other = new ProjectVersion(storedProject);
		assertNotNull(other);
		assertEquals(storedProject, other.getProject());
	}

	@Test
	public void testSetProject() {
		projectVersion.setProject(storedProject);
		assertEquals(storedProject, projectVersion.getProject());
	}

	@Test
	public void testSetRevisionId() {
		String revId = "amazing-id";
		projectVersion.setRevisionId(revId);
		assertEquals(revId, projectVersion.getRevisionId());
	}

	@Test
	public void testSetTimestamp() {
		long timestamp = 750236;
		projectVersion.setTimestamp(timestamp);
		assertEquals(timestamp, projectVersion.getTimestamp());
	}

	@Test
	public void testSetDate() {
		Date date = new Date(0);
		projectVersion.setDate(date);
		assertEquals(date, projectVersion.getDate());
	}

	@Test
	public void testSetCommitter() {
		Developer developer = new Developer();
		projectVersion.setCommitter(developer);
		assertEquals(developer, projectVersion.getCommitter());
	}

	@Test
	public void testSetCommitMsg() {
		String commitMsg = "did-some-work";
		projectVersion.setCommitMsg(commitMsg);
		assertEquals(commitMsg, projectVersion.getCommitMsg());
	}

	@Test
	public void testSetSequence() {
		long sequence = 240753;
		projectVersion.setSequence(sequence);
		assertEquals(sequence, projectVersion.getSequence());
	}

	@Test
	public void testSetVersionFiles() {
		Set<IProjectFile> projectFileSet = new HashSet<>();
		projectFileSet.add(projectFile);
		projectVersion.setVersionFiles(projectFileSet);
		assertEquals(projectFileSet, projectVersion.getVersionFiles());
		assertEquals(1, projectFileSet.size());
	}

	@Test
	public void testSetVersionFiles_2() {
		Set<IProjectFile> projectFileSet = null;
		projectVersion.setVersionFiles(projectFileSet);
		assertFalse(projectVersion.getVersionFiles() == null);
	}

	@Test
	public void testSetTags() {
		Set<Tag> tagSet = new HashSet<>();
		tagSet.add(tag);
		projectVersion.setTags(tagSet);
		assertEquals(tagSet, projectVersion.getTags());
		assertEquals(1, tagSet.size());
	}

	@Test
	public void testSetMeasurements() {
		Set<ProjectVersionMeasurement> projectVersionMeasurementSet = new HashSet<>();
		projectVersionMeasurementSet.add(projectVersionMeasurement);
		projectVersion.setMeasurements(projectVersionMeasurementSet);
		assertEquals(projectVersionMeasurementSet,
				projectVersion.getMeasurements());
		assertEquals(1, projectVersionMeasurementSet.size());
	}

	@Test
	public void testSetParents() {
		Set<ProjectVersionParent> projectVersionParentSet = new HashSet<>();
		projectVersionParentSet.add(projectVersionParent);
		projectVersion.setParents(projectVersionParentSet);
		assertEquals(projectVersionParentSet, projectVersion.getParents());
		assertEquals(1, projectVersionParentSet.size());
	}

	@Test
	public void testSetParents_2() {
		Set<ProjectVersionParent> projectVersionParentSet = null;
		projectVersion.setParents(projectVersionParentSet);
		assertFalse(projectVersion.getParents() == null);
	}

	@Test
	public void testSetIncomingBranches() {
		Set<Branch> branchSet = new HashSet<>();
		branchSet.add(branch);
		projectVersion.setIncomingBranches(branchSet);
		assertEquals(branchSet, projectVersion.getIncomingBranches());
		assertEquals(1, branchSet.size());
	}

	@Test
	public void testSetIncomingBranches_2() {
		Set<Branch> branchSet = null;
		projectVersion.setIncomingBranches(branchSet);
		assertFalse(projectVersion.getIncomingBranches() == null);
	}

	@Test
	public void testSetOutgoingBranches() {
		Set<Branch> outgoingBranches = new HashSet<Branch>();
		getObjectUnderTest().setOutgoingBranches(outgoingBranches);
		assertEquals(outgoingBranches, getObjectUnderTest()
				.getOutgoingBranches());
	}

	@Test
	public void testSetOutgoingBranches_2() {
		Set<Branch> outgoingBranches = null;
		getObjectUnderTest().setOutgoingBranches(outgoingBranches);
		assertNotNull(getObjectUnderTest().getOutgoingBranches());
	}

	@Test
	public void testSetNamespaces() {
		Set<NameSpace> namespaces = new HashSet<NameSpace>();
		getObjectUnderTest().setNamespaces(namespaces);
		assertEquals(namespaces, getObjectUnderTest().getNamespaces());
	}

	@Test
	public void testSetNamespaces_2() {
		Set<NameSpace> namespaces = null;
		getObjectUnderTest().setNamespaces(namespaces);
		assertNotNull(getObjectUnderTest().getNamespaces());
	}

	private ProjectVersion setUpSeqs(long a, long b) {
		ProjectVersion pv = new ProjectVersion();
		String revisionId = "revId";
		pv.setSequence(b);
		pv.setProject(storedProject);
		pv.setRevisionId(revisionId);
		pv.setTimestamp(b);
		getObjectUnderTest().setProject(storedProject);
		getObjectUnderTest().setSequence(a);
		getObjectUnderTest().setTimestamp(a);
		getObjectUnderTest().setRevisionId(revisionId);
		return pv;
	}

	@Test
	public void testLte() {
		ProjectVersion pv = setUpSeqs(0, 42);
		assertTrue(getObjectUnderTest().lte(pv));
	}

	@Test
	public void testLte_2() {
		ProjectVersion pv = setUpSeqs(42, 0);
		assertFalse(getObjectUnderTest().lte(pv));
	}

	@Test
	public void testLte_3() {
		ProjectVersion pv = setUpSeqs(0, 0);
		assertTrue(getObjectUnderTest().lte(pv));
	}

	@Test
	public void testLt() {
		ProjectVersion pv = setUpSeqs(0, 42);
		assertTrue(getObjectUnderTest().lt(pv));
	}

	@Test
	public void testLt_2() {
		ProjectVersion pv = setUpSeqs(42, 0);
		assertFalse(getObjectUnderTest().lt(pv));
	}

	@Test
	public void testLt_3() {
		ProjectVersion pv = setUpSeqs(0, 0);
		assertFalse(getObjectUnderTest().lt(pv));
	}

	@Test
	public void testGte() {
		ProjectVersion pv = setUpSeqs(0, 42);
		assertFalse(getObjectUnderTest().gte(pv));
	}

	@Test
	public void testGte_2() {
		ProjectVersion pv = setUpSeqs(42, 0);
		assertTrue(getObjectUnderTest().gte(pv));
	}

	@Test
	public void testGte_3() {
		ProjectVersion pv = setUpSeqs(0, 0);
		assertTrue(getObjectUnderTest().gte(pv));
	}

	@Test
	public void testGt() {
		ProjectVersion pv = setUpSeqs(0, 42);
		assertFalse(getObjectUnderTest().gt(pv));
	}

	@Test
	public void testGt_2() {
		ProjectVersion pv = setUpSeqs(42, 0);
		assertTrue(getObjectUnderTest().gt(pv));
	}

	@Test
	public void testGt_3() {
		ProjectVersion pv = setUpSeqs(0, 0);
		assertFalse(getObjectUnderTest().gt(pv));
	}

	@Test
	public void testEq() {
		ProjectVersion pv = setUpSeqs(0, 42);
		assertFalse(getObjectUnderTest().eq(pv));
	}

	@Test
	public void testEq_2() {
		ProjectVersion pv = setUpSeqs(42, 0);
		assertFalse(getObjectUnderTest().eq(pv));
	}

	@Test
	public void testEq_3() {
		ProjectVersion pv = setUpSeqs(0, 0);
		assertTrue(getObjectUnderTest().eq(pv));
	}

	@Test
	public void testEq_4() {
		ProjectVersion pv = setUpSeqs(0, 0);
		getObjectUnderTest().setRevisionId("this is not the same");
		assertFalse(getObjectUnderTest().eq(pv));
	}

	@Test
	public void testGetPreviousVersion() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertNull(getObjectUnderTest().getPreviousVersion());
	}

	@Test
	public void testGetPreviousVersion_2() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = null;
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertNull(getObjectUnderTest().getPreviousVersion());
	}

	@Test
	public void testGetPreviousVersion_3() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(getObjectUnderTest(), getObjectUnderTest()
				.getPreviousVersion());
	}

	@Test
	public void testGetNextVersion() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertNull(getObjectUnderTest().getNextVersion());
	}

	@Test
	public void testGetNextVersion_2() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = null;
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertNull(getObjectUnderTest().getNextVersion());
	}

	@Test
	public void testGetNextVersion_3() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(getObjectUnderTest(), getObjectUnderTest()
				.getNextVersion());
	}

	@Test
	public void testGetVersionByRevision() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertNull(ProjectVersion.getVersionByRevision(storedProject, ""));
	}

	@Test
	public void testGetVersionByRevision_2() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = null;
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertNull(ProjectVersion.getVersionByRevision(storedProject, ""));
	}

	@Test
	public void testGetVersionByRevision_3() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertEquals(getObjectUnderTest(),
				ProjectVersion.getVersionByRevision(storedProject, ""));
	}

	@Test
	public void testGetVersionByTimestamp() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertNull(ProjectVersion.getVersionByTimestamp(storedProject, 0L));
	}

	@Test
	public void testGetVersionByTimestamp_2() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = null;
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertNull(ProjectVersion.getVersionByTimestamp(storedProject, 0L));
	}

	@Test
	public void testGetVersionByTimestamp_3() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertEquals(getObjectUnderTest(),
				ProjectVersion.getVersionByTimestamp(storedProject, 0L));
	}

	@Test
	public void testGetFirstProjectVersion() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertNull(ProjectVersion.getFirstProjectVersion(storedProject));
	}

	@Test
	public void testGetFirstProjectVersion_2() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = null;
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertNull(ProjectVersion.getFirstProjectVersion(storedProject));
	}

	@Test
	public void testGetFirstProjectVersion_3() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(getObjectUnderTest(),
				ProjectVersion.getFirstProjectVersion(storedProject));
	}

	@Test
	public void testGetLastProjectVersion() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertNull(ProjectVersion.getLastProjectVersion(storedProject));
	}

	@Test
	public void testGetLastProjectVersion_2() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = null;
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertNull(ProjectVersion.getLastProjectVersion(storedProject));
	}

	@Test
	public void testGetLastProjectVersion_3() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(getObjectUnderTest(),
				ProjectVersion.getLastProjectVersion(storedProject));
	}

	@Test
	public void testGetLastMeasuredVersion() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertNull(ProjectVersion.getLastMeasuredVersion(metric, storedProject));
	}

	@Test
	public void testGetLastMeasuredVersion_3() {
		this.setUpSeqs(0, 0);
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(getObjectUnderTest(),
				ProjectVersion.getLastMeasuredVersion(metric, storedProject));
	}

	@Test
	public void testGetFilesCount() {
		super.setUpMockedDBService();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(null);
		assertEquals(0, getObjectUnderTest().getFilesCount(pfs));
	}

	@Test
	public void testGetFilesCount_2() {
		super.setUpMockedDBService();
		Long i = 131L;
		List<Long> list = new ArrayList<Long>();
		list.add(i);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertTrue(i == getObjectUnderTest().getFilesCount(pfs));
	}

	@Test
	public void testGetFilesCount_3() {
		super.setUpMockedDBService();
		Long i = 131L;
		List<Long> list = new ArrayList<Long>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, getObjectUnderTest().getFilesCount(pfs));
	}

	@Test
	public void testGetLiveFilesCount() {
		this.setUpSeqs(0, 0);
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(getObjectUnderTest());
		
		long lo = 42L;
		List<Long> lList = new ArrayList<Long>();
		lList.add(lo);
		
		super.setUpMockedDBService();
		Mockito.doReturn(list).doReturn(lList).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		
		assertEquals(lo, getObjectUnderTest().getLiveFilesCount());
	}
	
	@Test
	public void testGetLiveFilesCount_2() {
		ProjectVersion pv2 = this.setUpSeqs(8, 2);
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(pv2);
		
		long lo = 42L;
		List<Long> lList = new ArrayList<Long>();
		lList.add(lo);
		
		super.setUpMockedDBService();
		Mockito.doReturn(list).doReturn(lList).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		
		assertEquals(lo, getObjectUnderTest().getLiveFilesCount());
	}
	
	@Test
	public void testToString() {
		String name = "name";
		Mockito.doReturn(name).when(storedProject).toString();
		String revId = "revId";
		getObjectUnderTest().setProject(storedProject);
		getObjectUnderTest().setRevisionId(revId);
		
		String res = getObjectUnderTest().toString();
		assertTrue(res.contains(name));
		assertTrue(res.contains(revId));
	}
	
	@Test
	public void testGetFiles() {
		this.setUpSeqs(8, 2);
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(getObjectUnderTest());
		
		List<ProjectFile> lList = null;
		
		super.setUpMockedDBService();
		Mockito.doReturn(list).doReturn(lList).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		
		assertTrue(getObjectUnderTest().getFiles().isEmpty());
	}	
	
	@Test
	public void testGetFiles_2() {
		ProjectVersion pv2 = this.setUpSeqs(8, 2);
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(pv2);
		
		List<ProjectFile> lList = new ArrayList<ProjectFile>();
		lList.add(projectFile);
		
		super.setUpMockedDBService();
		Mockito.doReturn(list).doReturn(lList).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		
		assertEquals(lList, getObjectUnderTest().getFiles());
	}



	@Test
	public void testGetFilesDirectoryInt() {
		ProjectVersion pv2 = this.setUpSeqs(8, 2);
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(pv2);
		
		List<ProjectFile> lList = new ArrayList<ProjectFile>();
		lList.add(projectFile);
		
		super.setUpMockedDBService();
		Mockito.doReturn(list).doReturn(lList).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		
		assertEquals(lList, getObjectUnderTest().getFiles((Directory) null, 0));
	}

	@Test
	public void testGetFilesDirectory() {
		ProjectVersion pv2 = this.setUpSeqs(8, 2);
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(pv2);
		
		List<ProjectFile> lList = new ArrayList<ProjectFile>();
		lList.add(projectFile);
		
		super.setUpMockedDBService();
		Mockito.doReturn(list).doReturn(lList).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		
		assertEquals(lList, getObjectUnderTest().getFiles(dir));
	}

	@Test
	public void testGetFilesPattern() {
		ProjectVersion pv2 = this.setUpSeqs(8, 2);
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(pv2);
		
		List<ProjectFile> lList = new ArrayList<ProjectFile>();
		lList.add(projectFile);
		Mockito.doReturn("blah").when(projectFile).getFileName();
		
		super.setUpMockedDBService();
		Mockito.doReturn(list).doReturn(lList).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		
		assertEquals(0, getObjectUnderTest().getFiles(Pattern.compile("hoi")).size());
	}
	

	@Test
	public void testGetFilesPattern_2() {
		ProjectVersion pv2 = this.setUpSeqs(8, 2);
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(pv2);
		
		List<ProjectFile> lList = new ArrayList<ProjectFile>();
		lList.add(projectFile);
		Mockito.doReturn("hoi").when(projectFile).getFileName();
		
		super.setUpMockedDBService();
		Mockito.doReturn(list).doReturn(lList).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		
		assertEquals(lList, getObjectUnderTest().getFiles(Pattern.compile("hoi")));
	}

	@Test
	public void testGetFilesPatternInt() {
		ProjectVersion pv2 = this.setUpSeqs(8, 2);
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(pv2);
		
		List<ProjectFile> lList = new ArrayList<ProjectFile>();
		lList.add(projectFile);
		Mockito.doReturn("hoi").when(projectFile).getFileName();
		
		super.setUpMockedDBService();
		Mockito.doReturn(list).doReturn(lList).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		
		assertEquals(lList, getObjectUnderTest().getFiles(Pattern.compile("hoi",2)));
	}

	@Test
	public void testAllDirs() {
		ProjectVersion pv2 = this.setUpSeqs(8, 2);
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(pv2);
		
		List<ProjectFile> lList = new ArrayList<ProjectFile>();
		lList.add(projectFile);
		
		super.setUpMockedDBService();
		Mockito.doReturn(list).doReturn(lList).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		
		assertEquals(lList, getObjectUnderTest().allDirs());
	}

	@Test
	public void testAllFiles() {
		ProjectVersion pv2 = this.setUpSeqs(8, 2);
		
		List<ProjectVersion> list = new ArrayList<ProjectVersion>();
		list.add(pv2);
		
		List<ProjectFile> lList = new ArrayList<ProjectFile>();
		lList.add(projectFile);
		
		super.setUpMockedDBService();
		Mockito.doReturn(list).doReturn(lList).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
		
		assertEquals(lList, getObjectUnderTest().allFiles());
	}

	@Test
	public void testIsTag() {
		this.setUpSeqs(0, 0);
		List<Tag> list = new ArrayList<Tag>();
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertFalse(getObjectUnderTest().isTag());
	}


	@Test
	public void testIsTag_3() {
		this.setUpSeqs(0, 0);
		List<Tag> list = new ArrayList<Tag>();
		list.add(tag);
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertTrue(getObjectUnderTest().isTag());
	}

	@Test
	public void testIsBranch() {
		assertFalse(getObjectUnderTest().isBranch());
	}
	
	@Test
	public void testIsBranch_2() {
		Branch b1 = Mockito.mock(Branch.class);
		Branch b2 = Mockito.mock(Branch.class);
		Set<Branch> out = new HashSet<Branch>();
		out.add(b1);
		out.add(b2);
		Set<Branch> in = new HashSet<Branch>();
		in.add(b1);
		getObjectUnderTest().setOutgoingBranches(out);
		getObjectUnderTest().setIncomingBranches(in);
		assertTrue(getObjectUnderTest().isBranch());
	}
	
	@Test
	public void testIsBranch_3() {
		Branch b1 = Mockito.mock(Branch.class);
		Branch b2 = Mockito.mock(Branch.class);
		Set<Branch> out = new HashSet<Branch>();
		out.add(b1);
		out.add(b2);
		Set<Branch> in = new HashSet<Branch>();
		getObjectUnderTest().setOutgoingBranches(out);
		getObjectUnderTest().setIncomingBranches(in);
		assertFalse(getObjectUnderTest().isBranch());
	}
	
	@Test
	public void testIsBranch_4() {
		Branch b1 = Mockito.mock(Branch.class);
		Branch b2 = Mockito.mock(Branch.class);
		Set<Branch> out = new HashSet<Branch>();
		out.add(b1);
		Set<Branch> in = new HashSet<Branch>();
		in.add(b1);
		in.add(b2);
		getObjectUnderTest().setOutgoingBranches(out);
		getObjectUnderTest().setIncomingBranches(in);
		assertFalse(getObjectUnderTest().isBranch());
	}

	@Test
	public void testIsMerge() {
		assertFalse(getObjectUnderTest().isMerge());
	}
	
	@Test
	public void testIsMerge_2() {
		Branch b1 = Mockito.mock(Branch.class);
		Branch b2 = Mockito.mock(Branch.class);
		Set<Branch> out = new HashSet<Branch>();
		out.add(b1);
		out.add(b2);
		Set<Branch> in = new HashSet<Branch>();
		in.add(b1);
		getObjectUnderTest().setOutgoingBranches(out);
		getObjectUnderTest().setIncomingBranches(in);
		assertFalse(getObjectUnderTest().isMerge());
	}
	
	@Test
	public void testIsMerge_3() {
		Branch b1 = Mockito.mock(Branch.class);
		Branch b2 = Mockito.mock(Branch.class);
		Set<Branch> out = new HashSet<Branch>();
		out.add(b1);
		out.add(b2);
		Set<Branch> in = new HashSet<Branch>();
		getObjectUnderTest().setOutgoingBranches(out);
		getObjectUnderTest().setIncomingBranches(in);
		assertFalse(getObjectUnderTest().isMerge());
	}
	
	@Test
	public void testIsMerge_4() {
		Branch b1 = Mockito.mock(Branch.class);
		Branch b2 = Mockito.mock(Branch.class);
		Set<Branch> out = new HashSet<Branch>();
		out.add(b1);
		Set<Branch> in = new HashSet<Branch>();
		in.add(b1);
		in.add(b2);
		getObjectUnderTest().setOutgoingBranches(out);
		getObjectUnderTest().setIncomingBranches(in);
		assertTrue(getObjectUnderTest().isMerge());
	}
	
	@Test
	public void testIsMerge_5() {
		Branch b1 = Mockito.mock(Branch.class);
		Set<Branch> out = new HashSet<Branch>();
		Set<Branch> in = new HashSet<Branch>();
		in.add(b1);
		getObjectUnderTest().setOutgoingBranches(out);
		getObjectUnderTest().setIncomingBranches(in);
		assertFalse(getObjectUnderTest().isMerge());
	}

	@Test
	public void testGetBranch() {
		Branch b1 = Mockito.mock(Branch.class);
		Branch b2 = Mockito.mock(Branch.class);
		Branch b3 = Mockito.mock(Branch.class);
		Set<Branch> out = new HashSet<Branch>();
		out.add(b3);
		Set<Branch> in = new HashSet<Branch>();
		in.add(b1);
		in.add(b2);
		getObjectUnderTest().setOutgoingBranches(out);
		getObjectUnderTest().setIncomingBranches(in);
		assertEquals(b3, getObjectUnderTest().getBranch());
	}
	
	@Test
	public void testGetBranch_2() {
		Branch b1 = Mockito.mock(Branch.class);
		Branch b2 = Mockito.mock(Branch.class);
		Set<Branch> out = new HashSet<Branch>();
		out.add(b1);
		out.add(b2);
		Set<Branch> in = new HashSet<Branch>();
		in.add(b2);
		getObjectUnderTest().setOutgoingBranches(out);
		getObjectUnderTest().setIncomingBranches(in);
		assertEquals(b2, getObjectUnderTest().getBranch());
	}

	@Test
	public void testEqualsObject() {
		assertFalse(getObjectUnderTest().equals(null));
	}
	
	@Test
	public void testEqualsObject_2() {
		assertFalse(getObjectUnderTest().equals(123));
	}
	
	@Test
	public void testEqualsObject_3() {
		ProjectVersion pv2 = this.setUpSeqs(42, 42);
		assertEquals(getObjectUnderTest(), pv2);
	}

}

