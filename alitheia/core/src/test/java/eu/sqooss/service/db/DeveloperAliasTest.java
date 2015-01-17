package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class DeveloperAliasTest extends DBTestSuper<DeveloperAlias>{

	@Mock Developer d;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new DeveloperAlias());
	}

	@Test
	public final void testHashCode() {
		assertFalse(new DeveloperAlias().hashCode() == new DeveloperAlias("email", null).hashCode());
		assertFalse(new DeveloperAlias("", d).hashCode() == new DeveloperAlias("", null).hashCode());
		assertEquals(new DeveloperAlias().hashCode(), getObjectUnderTest().hashCode());
		assertEquals(getObjectUnderTest().hashCode(), getObjectUnderTest().hashCode());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testDeveloperAliasStringDeveloper() {
		String email = "s.hugtenburg@gmail.com";
		DeveloperAlias da = new DeveloperAlias(email, d);
		assertEquals(d, da.getDeveloper());
		assertEquals(email, da.getEmail());
	}

	@Test
	public final void testSetEmail() {
		String email = "s.hugtenburg@gmail.com";
		getObjectUnderTest().setEmail(email);
		assertEquals(email, getObjectUnderTest().getEmail());
	}

	@Test
	public final void testSetDeveloper() {
		getObjectUnderTest().setDeveloper(d);
		assertEquals(d, getObjectUnderTest().getDeveloper());
	}

	@Test
	public final void testEqualsObject_1() {
		assertEquals(new DeveloperAlias(), getObjectUnderTest());
	}
	
	@Test
	public final void testEquals_2() {
		assertFalse(new DeveloperAlias("", null).equals(getObjectUnderTest()));
	}
	
	@Test
	public final void testEquals_3() {
		assertTrue(new DeveloperAlias("", null).equals(new DeveloperAlias("", null)));		
	}

	@Test
	public final void testToString() {
		String mail = "hoi";
		getObjectUnderTest().setEmail(mail);
		assertEquals(mail, getObjectUnderTest().toString());
	}

}
