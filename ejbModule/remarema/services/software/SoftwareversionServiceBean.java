package remarema.services.software;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.domain.Node;
import remarema.domain.Softwarepackage;
import remarema.domain.Softwareversion;

/**
 * Session Bean implementation class SoftwareversionServiceBean
 */
@Stateless
@LocalBean
public class SoftwareversionServiceBean {
	
	@PersistenceContext
	protected EntityManager em;

    /**
     * EJB default constructor. 
     */
    public SoftwareversionServiceBean() {
    }

	public SoftwareversionServiceBean(EntityManager em) {
		this.em = em;
	}
	
	public Softwareversion addSoftwareversion(String path, int packageID){
		Softwarepackage softwarepackage = em.find(Softwarepackage.class, packageID);
		Softwareversion sv = new Softwareversion(path, softwarepackage);
		em.persist(sv);
		return sv;
	}
	
	public List<Softwareversion> findAllSoftwareversions(){
		TypedQuery<Softwareversion> query = em.createQuery(
				"SELECT sv FROM Softwareversion sv", Softwareversion.class);
		return query.getResultList();
	}
	
	public int findAnzahlSoftwareversions(){
		int a = findAllSoftwareversions().size()+1;    //+1, da wir unten in der Schleife mit 1 beginnen
		return a;
	}
    
    public String[] getAllSoftwareName(){
    	List<Softwareversion> softwareList = new ArrayList<Softwareversion>();
    	softwareList = findAllSoftwareversions();
    	int softwareAnzahl = findAnzahlSoftwareversions();

    	String[] softwareString = new String[softwareAnzahl];
    	
    	
    	for(int i = 1; i < softwareAnzahl; i++){
        	softwareString[i] = softwareList.get((i-1)).getVersionName();
        	
    	}
    	return softwareString;
    }
	
    
    

}
