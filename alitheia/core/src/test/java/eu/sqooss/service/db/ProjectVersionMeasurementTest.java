package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ProjectVersionMeasurementTest extends
		DBTestSuper<ProjectVersionMeasurement> {

	ProjectVersionMeasurement projectVersionMeasurement;
	@Mock
	Metric metric;
	@Mock
	ProjectVersion projectVersion;
	String result;

	@Before
	public void setUp() throws Exception {
		projectVersionMeasurement = new ProjectVersionMeasurement();
		super.setUp(projectVersionMeasurement);
	}

	@Test
	public void testSetId() {
		super.testSetId();
	}

	@Test
	public void testProjectVersionMeasurement() {
		assertNotNull(new ProjectVersionMeasurement());
	}

	@Test
	public void testProjectVersionMeasurementMetricProjectVersionString() {
		ProjectVersionMeasurement other = new ProjectVersionMeasurement(metric,
				projectVersion, result);

		assertNotNull(other);
		assertEquals(metric, other.getMetric());
		assertEquals(projectVersion, other.getProjectVersion());
		assertEquals(result, other.getResult());
	}

	@Test
	public void testSetProjectVersion() {
		projectVersionMeasurement.setProjectVersion(projectVersion);
		assertEquals(projectVersion,
				projectVersionMeasurement.getProjectVersion());
	}

	@Test
	public void testSetMetric() {
		projectVersionMeasurement.setMetric(metric);
		assertEquals(metric, projectVersionMeasurement.getMetric());
	}

	@Test
	public void testSetResult() {
		projectVersionMeasurement.setResult(result);
		assertEquals(result, projectVersionMeasurement.getResult());
	}

}
