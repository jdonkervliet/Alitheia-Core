package eu.sqooss.service.db;

public interface Project extends IDAObject{

	String getName();
	String getBtsUrl();
	String getMailUrl();
	String getScmUrl();
	ClusterNode getClusternode();
	boolean isEvaluated();

}
