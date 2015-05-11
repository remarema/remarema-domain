package remarema.services.software;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.api.CreateSoftwareversion;
import remarema.api.UpdateVersion;
import remarema.api.VersionDetail;
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

	public void execute(CreateSoftwareversion command) {
		String pkgName = command.getSoftwarepackageName();
		Softwarepackage pkg = findSoftwarePackage(pkgName);

		Softwareversion version = new Softwareversion(command.getSoftwareName(), pkg);
		version.setSoftwarePath(command.getSoftwarePath());
		pkg.getSoftwareversion().add(version);
		em.persist(version);
	}

	private Softwarepackage findSoftwarePackage(String pkgName) {
		TypedQuery<Softwarepackage> query = em
				.createQuery(
						"SELECT o From Softwarepackage o WHERE o.softwarepackageName = :name",
						Softwarepackage.class);
		query.setParameter("name", pkgName);
		return query.getSingleResult();
	}

	public void updateVersion(UpdateVersion command) {
		Softwareversion version = em.find(Softwareversion.class,
				command.getSoftwareID());
		version.setVersionName(command.getSoftwareName());
		version.setSoftwarePath(command.getSoftwarePath());

		String pkgName = command.getSoftwarepackageName();
		Softwarepackage pkg = findSoftwarePackage(pkgName);
		version.setSoftwarepackage(pkg);

		em.flush();
	}

	public void removeVersion(VersionDetail detail) {
		Softwareversion version = em.find(Softwareversion.class,
				detail.getSoftwareID());
		if (version != null) {
			em.remove(version);
		}
	}

	public VersionDetail getVersionDetailForVersionID(VersionDetail detail) {
		int softwareID = detail.getSoftwareID();
		return mapVersionToVersionDetail(loadSoftware(softwareID));

	}

	VersionDetail mapVersionToVersionDetail(Softwareversion softwareversion) {
		VersionDetail version = new VersionDetail();
		version.setSoftwareID(softwareversion.getSoftwareID());
		version.setSoftwareName(softwareversion.getVersionName());
		version.setSoftwarePath(softwareversion.getSoftwarePath());

		Softwarepackage pkg = softwareversion.getSoftwarepackage();
		if (pkg != null) {
			version.setSoftwarepackageID(version.getSoftwarepackageID());
			version.setSoftwarepackageName(version.getSoftwarepackageName());
		}
		return version;
	}

	Softwareversion loadSoftware(int softwareID) {
		TypedQuery<Softwareversion> query = em.createQuery(
				"SELECT o FROM Softwareversion o WHERE o.softwareID ="
						+ softwareID, Softwareversion.class);
		return query.getSingleResult();
	}

	public List<VersionDetail> getVersionDetailForAllVersions(int packageID) {
		List<Softwareversion> results = loadAllSoftware(packageID);
		return mapVersionsToVersionDetail(results);
	}

	List<VersionDetail> mapVersionsToVersionDetail(List<Softwareversion> results) {
		List<VersionDetail> versionDetail = new ArrayList<VersionDetail>();

		for (Softwareversion result : results) {
			VersionDetail detail = new VersionDetail();
			detail.setSoftwareID(result.getSoftwareID());
			detail.setSoftwareName(result.getVersionName());

			if (result.hasPackage()) {
				Softwarepackage pkg = result.getSoftwarepackage();
				detail.setSoftwarepackageID(pkg.getSoftwarepackageID());
				detail.setSoftwarepackageName(pkg.getSoftwarepackageName());
			}
			versionDetail.add(detail);
		}
		return versionDetail;

	}

	List<Softwareversion> loadAllSoftware(int packageID) {
		TypedQuery<Softwareversion> query = em.createQuery(
				"SELECT o From Softwareversion o WHERE o.softwarepackage_softwarepackageID = :packageID ORDER BY o.versionName ",
				Softwareversion.class);
		query.setParameter("packageID", packageID);
		List<Softwareversion> results = query.getResultList();
		return results;
	}

}
