package factories.organisations

import domain.organisations.OrganisationDocuments
import java.util.Date

/**
 * Created by Isiphile on 2016/10/03.
 */
class OrganisationDocumentFactory {

  def createOrganisationDocument(values:Map[String, String],date:Date,permissionSet: Set[String,String]):OrganisationDocuments={
    OrganisationDocuments(organisationId = values("organisationId"),organisationDocumentsId = values("organisationDocumentsId"), description =values("description"),url = values(" url"),mime = values("mime"),date=date, permission = permissionSet,state = values("state"))
  }

}
