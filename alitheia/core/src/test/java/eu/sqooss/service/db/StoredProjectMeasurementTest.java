package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class StoredProjectMeasurementTest extends
		DBTestSuper<StoredProjectMeasurement> {

	private StoredProjectMeasurement storedProjectMeasurement;
	@Mock
	private StoredProject storedProject;
	@Mock
	private Metric metric;
	private String result = "nice-result";

	@Before
	public void setUp() throws Exception {
		storedProjectMeasurement = new StoredProjectMeasurement();
		super.setUp(storedProjectMeasurement);
	}

	@Test
	public void testSetId() {
		super.testSetId();
	}

	@Test
	public void testSetStoredProject() {
		storedProjectMeasurement.setStoredProject(storedProject);
		assertEquals(storedProject, storedProjectMeasurement.getStoredProject());
	}

	@Test
	public void testSetMetric() {
		storedProjectMeasurement.setMetric(metric);
		assertEquals(metric, storedProjectMeasurement.getMetric());
	}

	@Test
	public void testSetResult() {
		storedProjectMeasurement.setResult(result);
		assertEquals(result, storedProjectMeasurement.getResult());
	}

}
