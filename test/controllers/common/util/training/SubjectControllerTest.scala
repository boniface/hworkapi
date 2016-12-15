package controllers.common.util.training

import com.google.gson.Gson
import domain.common.util.Mail
import domain.training.courses.Subject
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.Helpers._
import com.google.gson.Gson
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.libs.json.Json
import play.api.test._
/**
 * Created by gavin.ackerman on 2016-12-09.
 */
class SubjectControllerTest extends PlaySpec with OneAppPerTest {

  val subject = Subject(
    "subjectId",
    "topic",
    "subjectCode",
    "description",
   23
 )

  "Routes" should {



    "SubjectController" should {

      "Create Subject Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/subject/create")
          .withJsonBody(Json.toJson(subject)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }
      "Get Subject From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/subject/subjectId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
