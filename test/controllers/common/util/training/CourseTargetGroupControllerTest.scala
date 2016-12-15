package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.courses.CourseTargetGroups
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.Helpers._
import com.google.gson.Gson
import play.api.test.Helpers._
import play.api.test._
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CourseTargetGroupControllerTest extends PlaySpec with OneAppPerTest {
 /// val gson = new Gson()
  val courseTargetGroups = CourseTargetGroups(
    "organisationId",
    "courseId",
    "targetGroupId"
 )

  "Routes" should {



    "CourseTargetGroupsController" should {

      "Create CourseTargetGroups Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/coursetargetgroups/create")
          .withJsonBody(Json.toJson(courseTargetGroups)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get CourseTargetGroups From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/coursetargetgroups/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
