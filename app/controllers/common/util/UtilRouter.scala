package controllers.common.util

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by hashcode on 2016/09/23.
  */
class UtilRouter @Inject()
(mail: MailController)
  extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/mail/$org") =>
      mail.getMail(org)
    case POST(p"/mail/create") =>
      mail.createOrUpdate
  }
}
