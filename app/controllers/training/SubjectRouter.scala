package controllers.training

import javax.inject.Inject

import controllers.training.SubjectController
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
 * Created by gavin.ackerman on 2016-12-04.
 */
class SubjectRouter @Inject()
(subject: SubjectController)
  extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/subject/$org") =>
      subject.getSubjectById(org)
    case POST(p"/subject/create") =>
      subject.createOrUpdate
  }
}
