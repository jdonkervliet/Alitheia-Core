package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class MailingListThreadMeasurementTest extends
		DBTestSuperDoHQL2<MailingListThreadMeasurement> {

	@Mock Metric m;
	@Mock MailingListThread mt;
	String value = "value here";
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new MailingListThreadMeasurement());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testMailingListThreadMeasurementMetricMailingListThreadString() {
		MailingListThreadMeasurement mltm = new MailingListThreadMeasurement(m, mt, value);
		assertEquals(m, mltm.getMetric());
		assertEquals(mt, mltm.getThread());
		assertEquals(value, mltm.getResult());
	}

	@Test
	public final void testSetThread() {
		getObjectUnderTest().setThread(mt);
		assertEquals(mt, getObjectUnderTest().getThread());
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

	
	@Test
	public void testGetBugsForStatusStatus() {
		List<MailingListThreadMeasurement> list = new ArrayList<MailingListThreadMeasurement>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, MailingListThreadMeasurement.getAllMeasurementsForThread(mt).size());
	}
	
	@Test
	public void testGetBugsForStatusStatus_2() {
		List<MailingListThreadMeasurement> list = new ArrayList<MailingListThreadMeasurement>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(getObjectUnderTest(), MailingListThreadMeasurement.getAllMeasurementsForThread(mt).get(0));
	}
}
