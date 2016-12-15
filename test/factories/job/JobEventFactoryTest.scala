package factories.job

import domain.job.JobEvent
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by hashcode on 2016/09/26.
  */
class JobEventFactoryTest extends FunSuite{

  test("testCreatePositionFunding"){
    val jobEventFactory = new JobEventFactory;

    val date = new DateTime(2016, 4, 2, 1, 20, 2, 0);

    val values = Map("organisationId" -> "1",
      "jobId" -> "1",
      "state" -> "RSA"
    );

    val jobEventF = jobEventFactory.createJobEvent(values, date);

    assert(jobEventF == JobEvent(jobId = "1",
      jobEventId = "1",
      date = new DateTime(2016, 4, 2, 1, 20, 2, 0),
      event = "RSA"));
  }

}