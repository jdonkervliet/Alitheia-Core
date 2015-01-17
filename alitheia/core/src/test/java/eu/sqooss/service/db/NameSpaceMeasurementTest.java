package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class NameSpaceMeasurementTest extends DBTestSuper<NameSpaceMeasurement> {

	@Mock Metric m;
	@Mock NameSpace ns;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new NameSpaceMeasurement());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testSetResult() {
		String result = "this is a result";
		getObjectUnderTest().setResult(result);
		assertEquals(result, getObjectUnderTest().getResult());
	}

	@Test
	public final void testSetMetric() {
		getObjectUnderTest().setMetric(m);
		assertEquals(m, getObjectUnderTest().getMetric());
	}

	@Test
	public final void testSetNamespace() {
		getObjectUnderTest().setNamespace(ns);
		assertEquals(ns, getObjectUnderTest().getNamespace());
	}
	
	@Test
	public final void testGetAllMeasurementsByNameSpace() {
		List<NameSpaceMeasurement> list = new ArrayList<NameSpaceMeasurement>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, NameSpaceMeasurement.getMeasurementsByNameSpace(ns).size());
	}
	
	@Test
	public final void testGetAllMetricsByType_2() {
		List<NameSpaceMeasurement> list = new ArrayList<NameSpaceMeasurement>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(getObjectUnderTest(), NameSpaceMeasurement.getMeasurementsByNameSpace(ns).get(0));
	}
}
