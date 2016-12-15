package controllers.common.util.training

import com.google.gson.Gson
import domain.training.competencies.Evaluation
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
class EvaluationControllerTest extends PlaySpec with OneAppPerTest {

  val evaluation  = Evaluation(
    "evaluationId",
    "name"
)

  "Routes" should {



    "EvaluationController" should {

      "Create Evaluation Object in Through Controller" in {
        val request =  route(app, FakeRequest(POST, "/training/evaluation/create")
          .withJsonBody(Json.toJson(evaluation)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get Evaluation From Controller" in {
        val request = route(app, FakeRequest(GET, "/training/evaluation/evaluationId")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }

}
