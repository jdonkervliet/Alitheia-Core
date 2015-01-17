package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class DirectoryTest extends DBTestSuperObjectProp<Directory>{

	@Before
	public void setUp() throws Exception {
		super.setUp(new Directory());
	}

	@Test
	public final void testHashCode() {
		Directory d = new Directory();
		d.setPath("hoi");
		assertFalse(d.hashCode() == getObjectUnderTest().hashCode());
	}
	
	@Test
	public final void testHashCode_2() {
		Directory d = new Directory();
		assertEquals(d.hashCode(), getObjectUnderTest().hashCode());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testSetPath() {
		String path = "path/to/dir";
		getObjectUnderTest().setPath(path);
		assertEquals(path, getObjectUnderTest().getPath());
	}

	@Test
	public final void testSetFiles() {
		Set<IProjectFile> files = new HashSet<IProjectFile>();
		getObjectUnderTest().setFiles(files);
		assertEquals(files, getObjectUnderTest().getFiles());
	}

	@Test
	public final void testIsSubDirOf() {
		assertFalse(getObjectUnderTest().isSubDirOf(null));
	}

	@Test
	public final void testGetDirectory() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), Directory.getDirectory("", false));
	}
	
	@Test
	public final void testGetDirectory_2() {
		super.testGetObjectProp_Equals_2();
		assertFalse(Directory.getDirectory("", true) == null);
	}
	
	@Test
	public final void testGetDirectory_3() {
		super.testGetObjectProp_Null_1();
		assertNull(Directory.getDirectory("", false));
	}
	
	@Test
	public final void testGetDirectory_4() {
		super.testGetObjectProp_Null_2();
		assertNull(Directory.getDirectory("", true));
	}

	@Test
	public final void testToString() {
		assertNull(getObjectUnderTest().toString());
	}
	
	@Test
	public final void testToString_2() {
		String string = "path/to/dir";
		getObjectUnderTest().setPath(string);
		assertEquals(string, getObjectUnderTest().toString());
	}

	@Test
	public final void testEqualsObject() {
		assertEquals(getObjectUnderTest(), getObjectUnderTest());
	}
	
	@Test
	public final void testEqualsObject_2() {
		assertFalse(getObjectUnderTest().equals(new Directory()));
	}
	
	@Test
	public final void testEqualsObject_3() {
		assertFalse(getObjectUnderTest().equals(null));
	}
	
	@Test
	public final void testEqualsObject_4() {
		assertFalse(getObjectUnderTest().equals("object"));
	}
	
	@Test
	public final void testEqualsObject_5() {
		getObjectUnderTest().setPath("path/to/dir");
		assertFalse(getObjectUnderTest().equals(new Directory()));
	}

}
