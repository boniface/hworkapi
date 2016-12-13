package controllers.common.util.job

/**
  * Created by Aphiwe on 2016/12/12.
  */

import domain.common.address.AddressType
import com.google.gson.Gson
import domain.common.job.JobClassification
import domain.common.util.Mail
import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class JobClassificationControllerTest extends PlaySpec with OneAppPerTest{


  val jobClassification = JobClassification("100","available","occupied","698","256","till","person needed")

  "Routes" should{

    "JobClassificationController" should{

      "Create JobClassification Object in Through Controller" in {
        val request = route(app,FakeRequest(POST,"/job/jobClassification/create")
          .withJsonBody(Json.toJson(jobClassification)))
          .get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Content is ", contentAsString(request))

      }

      "Get JobClassification From Controller" in {
        val request = route(app,FakeRequest(GET,"/job/jobClassification/")).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println("The Output",contentAsJson(request))
      }
    }
  }
}
