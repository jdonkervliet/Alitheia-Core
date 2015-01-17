package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import eu.sqooss.core.AlitheiaCore;

public class StoredProjectTest extends DBTestSuper<StoredProject> {

	private StoredProject storedProject;
	private List<Version> projectVersions;
	private Set<Developer> developers;
	private Set<MailingList> mailingLists;
	private Set<StoredProjectMeasurement> measurements;
	private Set<StoredProjectConfig> configOpts;
	@Mock
	private ClusterNode assignment;
	private Set<Branch> branches;
	private Set<Bug> bugs;

	private ConfigOption key;
	@Mock
	private StoredProjectConfig storedProjectConfig;
	@Mock
	private ConfigurationOption configOption;

	@Before
	public void setUp() throws Exception {
		storedProject = new StoredProject();
		projectVersions = new ArrayList<>();
		developers = new HashSet<>();
		mailingLists = new HashSet<>();
		measurements = new HashSet<>();
		configOpts = new HashSet<>();
		branches = new HashSet<>();
		bugs = new HashSet<>();
		key = ConfigOption.PROJECT_BTS_SOURCE;

		super.setUp(storedProject);
	}

	@Test
	public void testSetId() {
		super.testSetId();
	}

	@Test
	public void testSetName() {
		String name = "nice-name";
		storedProject.setName(name);
		assertEquals(name, storedProject.getName());
	}

	@Test
	public void testSetWebsiteUrl() {
		String url = "some-url";
		List<String> stringList = new ArrayList<>();
		stringList.add(url);
		List<ConfigurationOption> list = new ArrayList<>();
		list.add(configOption);
		Mockito.doReturn(stringList).when(configOption)
				.getValues(Mockito.any(StoredProject.class));

		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);

		storedProject.setWebsiteUrl(url);
		assertEquals(url, storedProject.getWebsiteUrl());
	}

	@Test
	public void testSetContactUrl() {
		String url = "some-url";
		List<String> stringList = new ArrayList<>();
		stringList.add(url);
		List<ConfigurationOption> list = new ArrayList<>();
		list.add(configOption);
		Mockito.doReturn(stringList).when(configOption)
				.getValues(Mockito.any(StoredProject.class));

		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);

		storedProject.setContactUrl(url);
		assertEquals(url, storedProject.getContactUrl());
	}

	@Test
	public void testSetBtsUrl() {
		String url = "some-url";
		List<String> stringList = new ArrayList<>();
		stringList.add(url);
		List<ConfigurationOption> list = new ArrayList<>();
		list.add(configOption);
		Mockito.doReturn(stringList).when(configOption)
				.getValues(Mockito.any(StoredProject.class));

		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);

		storedProject.setBtsUrl(url);
		assertEquals(url, storedProject.getBtsUrl());
	}

	@Test
	public void testSetScmUrl() {
		String url = "some-url";
		List<String> stringList = new ArrayList<>();
		stringList.add(url);
		List<ConfigurationOption> list = new ArrayList<>();
		list.add(configOption);
		Mockito.doReturn(stringList).when(configOption)
				.getValues(Mockito.any(StoredProject.class));

		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);

		storedProject.setScmUrl(url);
		assertEquals(url, storedProject.getScmUrl());
	}

	@Test
	public void testSetMailUrl() {
		String url = "some-url";
		List<String> stringList = new ArrayList<>();
		stringList.add(url);
		List<ConfigurationOption> list = new ArrayList<>();
		list.add(configOption);
		Mockito.doReturn(stringList).when(configOption)
				.getValues(Mockito.any(StoredProject.class));

		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		storedProject.setMailUrl(url);
		assertEquals(url, storedProject.getMailUrl());
	}

	@Test
	public void testSetProjectVersions() {
		storedProject.setProjectVersions(projectVersions);
		assertEquals(projectVersions, storedProject.getProjectVersions());
	}

	@Test
	public void testSetDevelopers() {
		storedProject.setDevelopers(developers);
		assertEquals(developers, storedProject.getDevelopers());
	}

	@Test
	public void testSetMailingLists() {
		storedProject.setMailingLists(mailingLists);
		assertEquals(mailingLists, storedProject.getMailingLists());
	}

	@Test
	public void testSetMeasurements() {
		storedProject.setMeasurements(measurements);
		assertEquals(measurements, storedProject.getMeasurements());
	}

	@Test
	public void testSetConfigOpts() {
		storedProject.setConfigOpts(configOpts);
		assertEquals(configOpts, storedProject.getConfigOpts());
	}

	@Test
	public void testSetClusternode() {
		storedProject.setClusternode(assignment);
		assertEquals(assignment, storedProject.getClusternode());
	}

	@Test
	public void testSetBranches() {
		storedProject.setBranches(branches);
		assertEquals(branches, storedProject.getBranches());
	}

	@Test
	public void testSetBugs() {
		storedProject.setBugs(bugs);
		assertEquals(bugs, storedProject.getBugs());
	}

	@Test
	public void testGetProjectByName() {
		List<StoredProject> list = new ArrayList<>();
		list.add(storedProject);
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		StoredProject test = StoredProject.getProjectByName("");
		assertEquals(storedProject, test);
	}

	@Test
	public void testGetProjectCount() {
		Long l = new Long(1337);
		List<Long> list = new ArrayList<>();
		list.add(l);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL1(list);
		int test = StoredProject.getProjectCount();
		assertEquals(l.intValue(), test);
	}

	@Test
	public void testGetVersionsCount() {
		Long l = new Long(1337);
		List<Long> list = new ArrayList<>();
		list.add(l);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		Long test = storedProject.getVersionsCount();
		assertEquals(l, test);
	}

	@Test
	public void testGetMailsCount() {
		Long l = new Long(1337);
		List<Long> list = new ArrayList<>();
		list.add(l);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		Long test = storedProject.getMailsCount();
		assertEquals(l, test);
	}

	@Test
	public void testGetBugsCount() {
		Long l = new Long(1337);
		List<Long> list = new ArrayList<>();
		list.add(l);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		Long test = storedProject.getBugsCount();
		assertEquals(l, test);
	}

}
