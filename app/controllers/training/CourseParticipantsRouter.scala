package controllers.training

import javax.inject.Inject

import controllers.common.util.MailController
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
 * Created by gavin.ackerman on 2016-12-04.
 */
class CourseParticipantsRouter @Inject()
(courseParticipants: CourseParticipantsController)
  extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/courseparticipants/$org") =>
      courseParticipants.getCourseParticipants(org)
    case POST(p"/courseparticipants/create") =>
      courseParticipants.createOrUpdate
  }
}
