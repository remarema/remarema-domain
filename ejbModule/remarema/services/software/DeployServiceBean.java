package remarema.services.software;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import remarema.api.CreateDeployment;
import remarema.api.CreateSoftwareversion;
import remarema.domain.Softwarepackage;
import remarema.domain.Softwareversion;

/**
 * Session Bean implementation class DeployServiceBean
 */
@Stateless
@LocalBean
public class DeployServiceBean {

	@PersistenceContext
	public EntityManager em;

    public DeployServiceBean() {
    }
    
    public DeployServiceBean(EntityManager em){
    	this.em = em;
    }
    
    public void execute(CreateDeployment command) {
    	
    	
		String pkgName = command.getSoftwarepackageName();
		Softwarepackage pkg = findSoftwarePackage(pkgName);

		Softwareversion version = new Softwareversion(command.getSoftwareName(), pkg);
		version.setSoftwarePath(command.getSoftwarePath());
		pkg.getSoftwareversion().add(version);
		em.persist(version);
	} 
    
    

}
