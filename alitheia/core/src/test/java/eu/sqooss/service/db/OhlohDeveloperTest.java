package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class OhlohDeveloperTest extends DBTestSuperObjectProp<OhlohDeveloper> {

	final private String uname = "uname";
	final private String hash = "hash";
	final private String id = "id";
	
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new OhlohDeveloper());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testOhlohDeveloperStringStringString() {
		OhlohDeveloper od = new OhlohDeveloper(uname, hash, id);
		assertEquals(uname, od.getUname());
		assertEquals(hash, od.getEmailHash());
		assertEquals(id, od.getOhlohId());
		assertNotNull(od.getTimestamp());
	}

	@Test
	public final void testSetTimestamp() {
		Date dt = new Date();
		getObjectUnderTest().setTimestamp(dt);
		assertEquals(dt, getObjectUnderTest().getTimestamp());
	}

	@Test
	public final void testSetUname() {
		getObjectUnderTest().setUname(uname);
		assertEquals(uname, getObjectUnderTest().getUname());
	}

	@Test
	public final void testSetEmailHash() {
		getObjectUnderTest().setEmailHash(hash);
		assertEquals(hash, getObjectUnderTest().getEmailHash());
	}

	@Test
	public final void testSetOhlohId() {
		getObjectUnderTest().setOhlohId(id);
		assertEquals(id, getObjectUnderTest().getOhlohId());
	}

	@Test
	public final void testGetByOhlohId() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), OhlohDeveloper.getByOhlohId(id));
	}
	
	@Test
	public final void testGetByOhlohId_2() {
		super.testGetObjectProp_Null_1();
		assertNull(OhlohDeveloper.getByOhlohId(id));
	}
	
	@Test
	public final void testGetByEmailHash() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), OhlohDeveloper.getByEmailHash(hash));
	}
	
	@Test
	public final void testGetByEmailHash_2() {
		super.testGetObjectProp_Null_1();
		assertNull(OhlohDeveloper.getByEmailHash(hash));
	}
	
	@Test
	public final void testGetByUserName() {
		super.testGetObjectProp_Equals_1();
		assertEquals(1,  OhlohDeveloper.getByUserName(uname).size());
	}

}
