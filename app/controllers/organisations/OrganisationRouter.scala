package controllers.organisations

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
 * Created by Isiphile on 2016/12/09.
 */
class OrganisationRouter @Inject()
(mail: OrganisationController)
  extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/organisation/$org") =>
      mail.getOrganisation(org)
    case POST(p"/organisation/create") =>
      mail.createOrUpdate
  }
}
