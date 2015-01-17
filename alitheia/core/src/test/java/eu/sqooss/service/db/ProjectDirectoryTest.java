package eu.sqooss.service.db;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProjectDirectoryTest extends DBTestSuper<ProjectDirectory> {

	@Before
	public void setUp() throws Exception {
		super.setUp(new ProjectDirectory());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

}
