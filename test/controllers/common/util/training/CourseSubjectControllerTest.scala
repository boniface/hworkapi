package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.courses.CourseSubjects
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CourseSubjectControllerTest extends PlaySpec with OneAppPerTest {
 // val gson = new Gson()
  val subject = CourseSubjects(
    "organisationId",
    "courseId",
    "subjectId"

   )

  "Routes" should {



    "CourseSubjectController" should {

      "Create CourseSubjects Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/coursesubject/create")
          .withJsonBody(Json.toJson(subject)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }
      "Get CourseSubject From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/coursesubject/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
