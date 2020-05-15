package srcs.log;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class LogReceiver
 */
@Stateless
@LocalBean
public class LogReceiver implements LogReceiverRemote {

    /**
     * Default constructor. 
     */
    public LogReceiver() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName="LogJPA")
    private EntityManager em;//permet de lire et rechercher des données 

  //pour un log donné, l’ajoutera dans la base de données.
	@Override
	public boolean newLog(Log log) {
		if(log != null){
			return false;
		}
		em.persist(log);
		em.flush();
		return true;
	}

	//même traitement que newLog mais l’invocation se fait de manière asynchrone(ejb oriente msg)
	@Override
	@Asynchronous
	public boolean newLogAsync(Log log) {
		return newLog(log);
	}

	//renvoie un tableau de toutes les machines enregistrées dans la base
	@Override
	public Machine[] getMachines() {
		Query q = em.createQuery("SELECT m FROM Machine m");
		List<?> l =q.getResultList();
		Machine[] res=new Machine[l.size()];
		return l.toArray(res);
	}

	//renvoie la liste de tous les logs de la base
	@SuppressWarnings("unchecked")
	@Override
	public Log[] getLogs() {
		Query q = em.createQuery("SELECT l FROM Log l");
		List<?> l =q.getResultList();
		Log[] res=new Log[l.size()];
		return l.toArray(res);
	}

	//pour un level donné, renvoie la liste de tous les logs associés
	@Override
	public Log[] getLogsWithLevel(String level) {
		Query query = em.createQuery("SELECT l FROM Log l WHERE l.level := my_value");
		query.setParameter("my_value", level);
		List<?> l =query.getResultList();
		Log[] res=new Log[l.size()];
		return l.toArray(res);
	}

	//efface le contenu des tables Machine et Log
	@Override
	public boolean mr_proper() {
		Query queryMac = em.createQuery("SELECT m FROM Machine m");
		Query queryLog = em.createQuery("SELECT l FROM Log l");
		@SuppressWarnings("unchecked")
		List<Machine> listMac =queryMac.getResultList();
		@SuppressWarnings("unchecked")
		List<Log> listLog =queryLog.getResultList();
		
		for(int i=0; i<listMac.size(); i++) {
			Machine m = em.find(Machine.class, listMac.get(i).getId());
			if(m != null){
				em.remove(m);
				em.flush();
			}else {
				return false;
			}
		}
		for(int i=0; i<listLog.size(); i++) {
			Log l = em.find(Log.class, listLog.get(i).getId());
			if(l != null){
				em.remove(l);
				em.flush();
			}else {
				return false;
			}
		}
		return true;
	}

	//pour une machine donnée, renvoie la liste des logs associés
	@Override
	public Log[] getLogsWithMachine(String name_machine) {
		Query query = em.createQuery("SELECT m.logs FROM Machine m");
		List<?> l = query.getResultList();
		Log[] res= new Log[l.size()];
		return l.toArray(res);
	}
    
}
