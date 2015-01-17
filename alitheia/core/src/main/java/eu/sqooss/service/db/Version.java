package eu.sqooss.service.db;

import java.util.List;
import java.util.Set;

public interface Version extends IDAObject {

	String getRevisionId();
	void clearParents();
	Project getProject();
	long getSequence();
	List<IProjectFile> getFiles(Directory directory, int maskFiles);
	Set<IProjectFile> getVersionFiles();

}
