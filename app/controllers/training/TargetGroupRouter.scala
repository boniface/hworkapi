package controllers.training

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
 * Created by gavin.ackerman on 2016-12-04.
 */
class TargetGroupRouter @Inject()
(targetGroup: TargetGroupController)
  extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/targetgroup/$org") =>
      targetGroup.getTargetGroupById(org)
    case POST(p"/targetgroup/create") =>
      targetGroup.createOrUpdate
  }
}
