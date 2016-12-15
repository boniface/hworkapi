package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.courses.Course
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.Helpers._
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class CourseControllerTest extends PlaySpec with OneAppPerTest {
  //val gson = new Gson()
  val course = Course(
    "organisationId",
    "courseId",
    "name",
    "courseCategoryId",
    "courseCode",
    "trainingInstitutionId",
    "courseObjective",
    "courseTypeId",
    "criteriaId",
    "description"
  )

  "Routes" should {



    "CourseController" should {

      " Create Course Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/course/create")
          .withJsonBody(Json.toJson(course)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }


      "Get Course From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/course/organisationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
