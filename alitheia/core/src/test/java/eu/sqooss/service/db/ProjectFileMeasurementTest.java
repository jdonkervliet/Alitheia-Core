package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ProjectFileMeasurementTest extends
		DBTestSuper<ProjectFileMeasurement> {
	
	@Mock Metric m;
	@Mock ProjectFile f;
	
	String value = "hoi";

	@Before
	public void setUp() throws Exception {
		super.setUp(new ProjectFileMeasurement());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testProjectFileMeasurementMetricProjectFileString() {
		ProjectFileMeasurement pfm = new ProjectFileMeasurement(m, f, value);
		assertEquals(m, pfm.getMetric());
		assertEquals(f, pfm.getProjectFile());
		assertEquals(value, pfm.getResult());
	}

	@Test
	public final void testSetProjectFile() {
		getObjectUnderTest().setProjectFile(f);
		assertEquals(f, getObjectUnderTest().getProjectFile());
	}

	@Test
	public final void testSetMetric() {
		getObjectUnderTest().setMetric(m);
		assertEquals(m, getObjectUnderTest().getMetric());
	}

	@Test
	public final void testSetResult() {
		getObjectUnderTest().setResult(value);
		assertEquals(value, getObjectUnderTest().getResult());
	}

}
