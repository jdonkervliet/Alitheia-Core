package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MetricTest extends DBTestSuperObjectProp<Metric> {

	@Mock MetricType mt;
	@Mock Plugin pl;
	
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new Metric());
	}

	@Test
	public final void testHashCode() {
		assertEquals(new Metric().hashCode(), new Metric().hashCode());
	}
	
	@Test
	public final void testHashCode_2() {
		Metric diff = new Metric();
		diff.setId(18023);
		assertFalse(new Metric().hashCode() == diff.hashCode());
	}
	
	@Test
	public final void testHashCode_3() {
		Metric diff = new Metric();
		diff.setMnemonic("mnemonic");
		assertFalse(new Metric().hashCode() == diff.hashCode());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testSetMetricType() {
		getObjectUnderTest().setMetricType(mt);
		assertEquals(mt, getObjectUnderTest().getMetricType());
	}

	@Test
	public final void testSetDescription() {
		String description = "hi there";
		getObjectUnderTest().setDescription(description);
		assertEquals(description, getObjectUnderTest().getDescription());
	}

	@Test
	public final void testSetPlugin() {
		getObjectUnderTest().setPlugin(pl);
		assertEquals(pl, getObjectUnderTest().getPlugin());
	}

	@Test
	public final void testSetMnemonic() {
		String mnemonic = "mnemonic";
		getObjectUnderTest().setMnemonic(mnemonic);
		assertEquals(mnemonic, getObjectUnderTest().getMnemonic());
	}

	private void testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type t, boolean result) {
		Mockito.doReturn(t).when(mt).getEnumType();
		getObjectUnderTest().setMetricType(mt);
		
		List<Long> list = new ArrayList<Long>();
		if (result) {
			list.add(new Long(1));
		}
		
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(result, getObjectUnderTest().isEvaluated(null));
	}
	
	@Test
	public final void testIsEvaluated() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.BUG, false);
	}
	
	@Test
	public final void testIsEvaluated_2() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.DEVELOPER, false);
	}
	
	@Test
	public final void testIsEvaluated_3() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.ENCAPSUNIT, true);
	}
	
	@Test
	public final void testIsEvaluated_4() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.EXECUNIT, false);
	}
	
	@Test
	public final void testIsEvaluated_5() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.MAILING_LIST, false);
	}
	
	@Test
	public final void testIsEvaluated_6() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.MAILMESSAGE, false);
	}
	
	@Test
	public final void testIsEvaluated_7() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.MAILTHREAD, true);
	}
	
	@Test
	public final void testIsEvaluated_8() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.NAMESPACE, false);
	}
	
	@Test
	public final void testIsEvaluated_9() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.PROJECT, false);
	}

	@Test
	public final void testIsEvaluated_10() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.PROJECT_VERSION, false);
	}
	
	@Test
	public final void testIsEvaluated_11() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.SOURCE_DIRECTORY, true);
	}
	
	@Test
	public final void testIsEvaluated_12() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.SOURCE_FILE, false);
	}
	
	@Test
	public final void testIsEvaluated_13() {
		this.testIsEvaluatedWithMetricTypeAndBoolean(MetricType.Type.SOURCE_FILE, true);
	}

	@Test
	public final void testEqualsObject() {
		assertEquals(new Metric(), new Metric());
	}
	
	@Test
	public final void testEqualsObject_2() {
		assertFalse(new Metric().equals(null));
	}
	
	@Test
	public final void testEqualsObject_3() {
		assertFalse(new Metric().equals(new Integer(0)));
	}
	
	@Test
	public final void testEqualsObject_4() {
		assertEquals(new Metric(), getObjectUnderTest());
	}
	
	@Test
	public final void testEqualsObject_5() {
		getObjectUnderTest().setMnemonic("mnemonic");
		assertFalse(getObjectUnderTest().equals(new Metric()));
	}
	
	@Test
	public final void testEqualsObject_6() {
		getObjectUnderTest().setId(123);
		assertFalse(getObjectUnderTest().equals(new Metric()));
	}

	@Test
	public final void testToString() {
		Long id = 123L;
		String mnemonic = "mnemonic";
		getObjectUnderTest().setId(id);
		getObjectUnderTest().setMnemonic(mnemonic);
		assertTrue(getObjectUnderTest().toString().contains(mnemonic));
		assertTrue(getObjectUnderTest().toString().contains(Long.toString(id)));
	}

	@Test
	public final void testGetMetricByMnemonic() {
		super.testGetObjectProp_Null_1();
		assertNull(Metric.getMetricByMnemonic(""));
	}
	
	@Test
	public final void testGetMetricByMnemonic_2() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), Metric.getMetricByMnemonic(""));
	}

	@Test
	public final void testGetAllMetrics() {
		List<Metric> list = new ArrayList<Metric>();
		super.setUpMockedDBService();
		Mockito.doReturn(list).when(super.dbService).doHQL(Matchers.anyString());
		assertEquals(list, Metric.getAllMetrics());
	}
	
	@Test
	public final void testGetAllMetricsByType() {
		List<Metric> list = new ArrayList<Metric>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, Metric.getMetricsByType(mt).size());
	}
	
	@Test
	public final void testGetAllMetricsByType_2() {
		List<Metric> list = new ArrayList<Metric>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(getObjectUnderTest(), Metric.getMetricsByType(mt).get(0));
	}

}
