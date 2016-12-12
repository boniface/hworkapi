package controllers.common.util.education

/**
  * Created by Aphiwe on 2016/12/12.
  */

import com.google.gson.Gson
import domain.common.demographics.Title
import domain.common.education.Evaluation
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class EvaluationTypeController extends PlaySpec with OneAppPerTest{

  val evaluation = Evaluation(
    "E",
    "Exam")

  "Routes" should {



    "EducationTypeController" should {

      "Create Evaluation Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/education/evaluation/create")
          .withJsonBody(Json.toJson(Evaluation)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Evaluation From Controller" in {
        val request = route(app, FakeRequest(GET, "/education/evaluation/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
