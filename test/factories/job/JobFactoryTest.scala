package factories.job

import domain.job.Job
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by SONY on 2016-09-28.
  */
class JobFactoryTest extends FunSuite{

  test("testCreatePositionFunding"){
    val jobFactory = new JobFactory;

    val date = new DateTime(2016, 4, 2, 1, 20, 2, 0);

    val values = Map("organisationId" -> "1",
      "jobId" -> "1",
      "jobClassificationId" -> "1",
      "title" -> "KL",
      "code" -> "1123",
      "description" -> "job1",
      "state" -> "RSA"
    );

    val jobF = jobFactory.createJob(values, date);

    assert(jobF == Job(organisationId = "1",
      jobId = "1",
      jobClassificationId = "1",
      title = "KL",
      code = "1123",
      description = "job1",
      date = new DateTime(2016, 4, 2, 1, 20, 2, 0),
      state = "RSA"));
  }

}