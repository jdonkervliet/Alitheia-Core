package eu.sqooss.service.db;

import java.util.Set;

public interface EncapsUnit extends IDAObject{

	String getName();

	Set<ExecUnit> getExecUnits();

}
