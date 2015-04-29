package remarema.services.software;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.api.CreatePackage;
import remarema.api.PackageDetail;
import remarema.api.UpdatePackage;
import remarema.domain.Softwarepackage;

/**
 * Session Bean implementation class SoftwarepackageServiceBean
 */
@Stateless
@LocalBean
public class SoftwarepackageServiceBean {

	@PersistenceContext
	public EntityManager em;

	/**
	 * EJB default constructor.
	 */
	public SoftwarepackageServiceBean() {
	}

	public SoftwarepackageServiceBean(EntityManager em) {
		this.em = em;
	}

	public void execute(CreatePackage softwarepackage) {
		String packageName = softwarepackage.getSoftwarepackageName();

		Softwarepackage pkg = new Softwarepackage();
		pkg.setSoftwarepackageName(packageName);

		em.persist(pkg);
	}
	
	public void updatePackage(UpdatePackage command){
		int packageID = command.getSoftwarepackageID();
		Softwarepackage pkg = em.find(Softwarepackage.class, packageID);
		pkg.setSoftwarepackageName(command.getSoftwarepackageName());
		em.flush();
	}
	
	public void removePackage(PackageDetail detail){
		int packageID = detail.getSoftwarepackageID();
		Softwarepackage pkg = em.find(Softwarepackage.class, packageID);
		
		if(pkg != null){
			em.remove(pkg);
		}
		else{
			throw new IllegalArgumentException("Das angegebene Softwarepackage existiert nicht!");
		}
	}

	public PackageDetail getPackageDetailForPackageID(PackageDetail detail) {
		int packageID = detail.getSoftwarepackageID();
		return mapSoftwarepackageToPackageDetail(loadSoftwarePackage(packageID));

	}

	private PackageDetail mapSoftwarepackageToPackageDetail(
			Softwarepackage softwarePackage) {
		PackageDetail packageDetail = new PackageDetail();
		packageDetail.setSoftwarepackageID(softwarePackage
				.getSoftwarepackageID());
		packageDetail.setSoftwarepackageName(softwarePackage
				.getSoftwarepackageName());

		return packageDetail;
	}

	Softwarepackage loadSoftwarePackage(int packageID) {
		TypedQuery<Softwarepackage> query = em.createQuery(
				"SELECT o FROM Softwarepackage o WHERE o.softwarepackageID ="
						+ packageID, Softwarepackage.class);
		return query.getSingleResult();
	}
	
	public List<PackageDetail> getPackageDetailForAllPackages(){
		List<Softwarepackage> results = loadAllPackages();
		return mapSoftwarepackagesToPackageDetail(results);
	}

	private List<PackageDetail> mapSoftwarepackagesToPackageDetail(
			List<Softwarepackage> results) {
		List<PackageDetail> packageDetail = new ArrayList<PackageDetail>();
		
		for(Softwarepackage result : results){
			PackageDetail detail = new PackageDetail();
			
			detail.setSoftwarepackageID(result.getSoftwarepackageID());
			detail.setSoftwarepackageName(result.getSoftwarepackageName());
		
			packageDetail.add(detail);
		}
		return packageDetail;
	}

	private List<Softwarepackage> loadAllPackages() {
		TypedQuery<Softwarepackage> query = em.createQuery(
				"SELECT o FROM Softwarepackage o ORDER BY o.softwarepackageName", Softwarepackage.class);
		return query.getResultList();
	}

}
