package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.schedules.ScheduledCourse
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class ScheduledCourseControllerTest extends PlaySpec with OneAppPerTest {
  //val gson = new Gson()
  val scheduledCourse = ScheduledCourse(
    "organisationId",
    "courseId",
    "scheduledCourseId",
    "venue",
    32,
    54,
    new DateTime("2004-12-13T21:39:45.618-08:00"),
  new DateTime("2004-12-13T21:39:45.618-08:00"),
  "locationId",
    new DateTime("2004-12-13T21:39:45.618-08:00"))

  "Routes" should {



    "ScheduledCourseController" should {

      "Create ScheduledCourse Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/scheduledcourse/create")
          .withJsonBody(Json.toJson(scheduledCourse)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }
      "Get ScheduledCourse From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/scheduledcourse/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
