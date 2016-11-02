package factories.organisations
import domain.organisations.OrganisationLogo
import org.joda.time.DateTime

/**
 * Created by Isiphile on 2016/10/03.
 */
class OrganisationLogoFactory {

  def createOrganisationLogo(values:Map[String, String],size:Option[String],date:DateTime):OrganisationLogo={
    OrganisationLogo(organisationId = values("organisationId"),organisationLogoId = values("organisationLogoId"),url = values("url"),size = Option("size"),
      description = values("description"), mime = values("mime"), date=date)
  }
}
