package remarema.services.software;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.api.software.CreatePackage;
import remarema.api.software.PackageDetail;
import remarema.api.software.UpdatePackage;
import remarema.domain.Softwarepackage;

/**
 * Das SoftwarepackageServiceBean stellt alle notwendigen Methoden für die
 * Verwaltung von Softwarepackages bereit. Diese Methoden umfassen das Anlegen,
 * Updaten, und Löschen von Softwarepackages.
 * 
 * @author Rebecca van Langelaan
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

	// Package-protected; dient nur zum Testen!
	SoftwarepackageServiceBean(EntityManager em) {
		this.em = em;
	}

	/**
	 * Die Methode <code>execute()</code> dient zur Speicherung eines neuen
	 * Softwarepackages in der Datenbank. Dazu wird beim Aufruf der Methode ein
	 * CreatePackage-Objekt mitübergeben. Dieses beinhaltet die selben Felder
	 * wie die Entity <code>Softwarepackage</code>.
	 * 
	 * Zuerst wird der Name des Packages ausgelesen und als String gespeichert.
	 * Danach wird ein neues Softwarepackage-Objekt angelegt. Des Weiteren wird
	 * mit der set-Methode der Package-Name gesetzt. Mit <code>em.persist</code>
	 * wird der Datensatz in der Datenbank abgespeichert.
	 * 
	 * @param command
	 */
	public void execute(CreatePackage command) {
		String packageName = command.getSoftwarepackageName();

		Softwarepackage pkg = new Softwarepackage();
		pkg.setSoftwarepackageName(packageName);

		em.persist(pkg);
	}

	/**
	 * Die Methode <code>updatePackage()</code> benötigt als Parameter ein
	 * UpdatePackage-Objekt. Von diesem wird die Id des Packages ausgelesen.
	 * Danach wird mit <code>em.find()</code> der passende Datensatz in der
	 * Datenbank gesucht. Danach wird der Name des Softwarepackages gesetzt und
	 * mit <code>em.flush()</code> wird der Datensatz in der Datenbank
	 * upgedated.
	 * 
	 * @param command
	 */
	public void updatePackage(UpdatePackage command) {
		int packageID = command.getSoftwarepackageID();
		Softwarepackage pkg = em.find(Softwarepackage.class, packageID);
		pkg.setSoftwarepackageName(command.getSoftwarepackageName());
		em.flush();
	}

	/**
	 * Diese Methode dient zum Löschen eines Packages. Beim Aufruf der Methode
	 * wird ein PackageDetail-Objekt mitübergeben. Von diesem wird die Id
	 * ausgelesen und mit <code>em.find()</code> wird der passende Datensatz in
	 * der Datenbank gesucht.
	 * 
	 * Ist das gesuchte Objekt nicht null, wird der Datensatz mit
	 * <code>em.remove()</code> gelöscht. Wurde in der Datenbank aber kein
	 * Datensatz zur Id gefunden, wird eine
	 * <code>IllegalArgumentException</code> geworfen.
	 * 
	 * @param command
	 */
	public void removePackage(PackageDetail command) {
		int packageID = command.getSoftwarepackageID();
		Softwarepackage pkg = em.find(Softwarepackage.class, packageID);

		if (pkg != null) {
			em.remove(pkg);
		} else {
			throw new IllegalArgumentException(
					"Das angegebene Softwarepackage existiert nicht!");
		}
	}

	/**
	 * Diese Methode benötigt als Parameter ein PackageDetail-Objekt, bei dem
	 * die Id des Softwarepackages gesetzt sein muss. Zuerst wird die Id des
	 * Packages ausgelesen, danach wird die Methode
	 * {@link #mapSoftwarepackageToPackageDetail(Softwarepackage)} aufgerufen.
	 * 
	 * @param detail
	 * @return PackageDetail-Objekt
	 */
	public PackageDetail getPackageDetailForPackageID(PackageDetail detail) {
		int packageID = detail.getSoftwarepackageID();
		return mapSoftwarepackageToPackageDetail(loadSoftwarePackage(packageID));

	}

	/**
	 * Diese Methode "wandelt" ein einzelnes Softwarepackage-Objekt in ein
	 * PackageDetail-Objekt um.
	 * 
	 * @param softwarePackage
	 * @return packageDetail-Objekt.
	 */
	private PackageDetail mapSoftwarepackageToPackageDetail(
			Softwarepackage softwarePackage) {
		PackageDetail packageDetail = new PackageDetail();
		packageDetail.setSoftwarepackageID(softwarePackage
				.getSoftwarepackageID());
		packageDetail.setSoftwarepackageName(softwarePackage
				.getSoftwarepackageName());

		return packageDetail;
	}

	/**
	 * Die Methode <code>loadSoftwarePackage()</code> benötigt als Parameter die
	 * Id eines Softwarepackages. Das gefundene Objekt wird dem Benutzer
	 * zurückgegeben.
	 * 
	 * @param packageID
	 * @return Softwarepackage-Objekt
	 */
	Softwarepackage loadSoftwarePackage(int packageID) {
		TypedQuery<Softwarepackage> query = em.createQuery(
				"SELECT o FROM Softwarepackage o WHERE o.softwarepackageID ="
						+ packageID, Softwarepackage.class);
		return query.getSingleResult();
	}

	/**
	 * Diese Methode wird auf der Weboberfläche aufgerufen, um alle Packages in
	 * einer Übersicht darstellen zu können. Zuerst wird die Methode
	 * {@link #loadAllPackages()} aufgerufen, danach die Methode
	 * {@link #mapSoftwarepackagesToPackageDetail(List)}.
	 * 
	 * @return Zurückgegeben wird eine Liste von PackageDetail-Objekten.
	 */
	public List<PackageDetail> getPackageDetailForAllPackages() {
		List<Softwarepackage> results = loadAllPackages();
		return mapSoftwarepackagesToPackageDetail(results);
	}

	/**
	 * Diese Methode "wandelt" die aus der Datenbank ausgelesenen
	 * Softwarepackages in PackageDetail-Objekte um. Diese werden dann wiederum
	 * in eine Liste gespeichert.Diese müssen in PackageDetail-Objekte
	 * umgewandelt werden.
	 * 
	 * @param results
	 * @return Liste vom Typ PackageDetail
	 */
	private List<PackageDetail> mapSoftwarepackagesToPackageDetail(
			List<Softwarepackage> results) {
		List<PackageDetail> packageDetail = new ArrayList<PackageDetail>();

		for (Softwarepackage result : results) {
			PackageDetail detail = new PackageDetail();

			detail.setSoftwarepackageID(result.getSoftwarepackageID());
			detail.setSoftwarepackageName(result.getSoftwarepackageName());

			packageDetail.add(detail);
		}
		return packageDetail;
	}

	/**
	 * Diese Methode ließt mit Hilfe einer Query alle Datensätze, die in der
	 * Tabelle <code>softwarepackage</code> gespeichert wurden, aus der
	 * Datenbank aus. Die Datensätze werden in eine List gespeichert - diese
	 * wird an den Benutzer zurückgegeben.
	 * 
	 * @return
	 */
	private List<Softwarepackage> loadAllPackages() {
		TypedQuery<Softwarepackage> query = em
				.createQuery(
						"SELECT o FROM Softwarepackage o ORDER BY o.softwarepackageName",
						Softwarepackage.class);
		return query.getResultList();
	}

}
