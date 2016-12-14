package controllers.job

import com.google.gson.Gson
import domain.job.JobEvent
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by LuxoloM on 2016/12/14.
  */
class JobEventControllerTest  extends PlaySpec with OneAppPerTest {

  val gson = new Gson()
  val job = JobEvent(
    "1",
    "1",
    new DateTime("2016-12-13T21:39:45.618-08:00"),
    "Sandton"
  )

  "Routes" should {
    "JobEventController" should {

      "Create JobEvent Object in Through Controller" in {
        val request = route(app, FakeRequest(POST, "/jobEvent/create")
          .withJsonBody(Json.toJson(job)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Content is ", contentAsString(request))
      }

      "Get JobEvent From Controller" in {
        val request = route(app, FakeRequest(GET, "/jobEvent/")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }
    }
  }
}