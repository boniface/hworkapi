package factories.organisations

import domain.organisations.OrganisationDocuments
import java.util.Date

import org.joda.time.DateTime

/**
 * Created by Isiphile on 2016/10/03.
 */
class OrganisationDocumentFactory {

  def createOrganisationDocument(values:Map[String, String],date:DateTime,permissionSet: Set[String]):OrganisationDocuments={
    OrganisationDocuments(organisationId = values("organisationId"),organisationDocumentsId = values("organisationDocumentsId"),
      description =values("description"),url = values(" url"),mime = values("mime"),date=date, permission = permissionSet,state = values("state"))
  }

}
