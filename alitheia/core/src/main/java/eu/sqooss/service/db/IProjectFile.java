package eu.sqooss.service.db;

import java.util.List;
import java.util.Set;

public interface IProjectFile extends IDAObject {

	String getFileName();
	boolean getIsDirectory();
	String getName();
	Directory getDir();
	Version getProjectVersion();
	boolean isAdded();
	void setValidUntil(Version previous);
	ProjectFileState getState();
	IProjectFile getCopyFrom();
	void setState(ProjectFileState state);
	void setIsDirectory(boolean b);
	void setCopyFrom(IProjectFile copyFrom);
	IProjectFile getPreviousFileVersion();
	boolean isDeleted();
	boolean isReplaced();
	List<ExecUnit> getChangedExecutionUnits();
	Set<EncapsUnit> getEncapsulationUnits();
	Boolean isModule();
	void setModule(Boolean b);
	IProjectFile getEnclosingDirectory();

}
