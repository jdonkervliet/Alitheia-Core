package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class StoredProjectConfigTest extends DBTestSuper<StoredProjectConfig> {

	StoredProjectConfig storedProjectConfig;
	@Mock
	private ConfigurationOption confOpt;
	@Mock
	private StoredProject project;

	@Before
	public void setUp() throws Exception {
		storedProjectConfig = new StoredProjectConfig();
		super.setUp(storedProjectConfig);
	}

	@Test
	public void testSetId() {
		super.testSetId();
	}

	@Test
	public void testSetConfOpt() {
		storedProjectConfig.setConfOpt(confOpt);
		assertEquals(confOpt, storedProjectConfig.getConfOpt());
	}

	@Test
	public void testSetProject() {
		storedProjectConfig.setProject(project);
		assertEquals(project, storedProjectConfig.getProject());
	}

	@Test
	public void testSetValue() {
		String value = "nice-value";
		storedProjectConfig.setValue(value);
		assertEquals(value, storedProjectConfig.getValue());
	}

	@Test
	public void testFromProject() {
		List<StoredProjectConfig> list = new ArrayList<>();
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		List<StoredProjectConfig> test = StoredProjectConfig
				.fromProject(project);
		assertEquals(list, test);
	}

}
