package srcs.log;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface LogReceiverRemote {

	
	//pour un log donné, l’ajoutera dans la base de données.
	public boolean newLog(Log log);
	
	//même traitement que newLog mais l’invocation se fait de manière asynchrone
	public boolean newLogAsync(Log log);
	
	//renvoie un tableau de toutes les machines enregistrées dans la base
	public Machine[] getMachines();
	
	//renvoie la liste de tous les logs de la base
	public Log[] getLogs();
	
	//pour un level donné, renvoie la liste de tous les logs associés
	public Log[] getLogsWithLevel(String level);
	
	//efface le contenu des tables Machine et Log
	public boolean mr_proper();
	
	//pour une machine donnée, renvoie la liste des logs associés
	public Log[] getLogsWithMachine(String name_machine);
}
