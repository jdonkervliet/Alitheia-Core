package eu.sqooss.service.db;

import java.util.Date;

public interface IMailMessage extends IDAObject {

	int getDepth();
	void setDepth(int i);
	IDeveloper getSender();
	IMailMessage getParent();
	Date getSendDate();

}
