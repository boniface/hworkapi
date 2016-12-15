package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.schedules.CourseParticipants
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.Helpers._
import play.api.test._
import play.api.libs.json.Json
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CourseParticipantControllerTest extends PlaySpec with OneAppPerTest {
 // val gson = new Gson()
  val courseParticipants = CourseParticipants(
    "scheduledCourseId",
    "userId"
)

  "Routes" should {



    "CourseParticipantsController" should {

      " Create CourseParticipants Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/courseparticipants/create")
          .withJsonBody(Json.toJson(courseParticipants)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get CourseParticipants From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/courseparticipants/scheduledCourseId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
