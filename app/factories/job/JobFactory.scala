package factories.job

import domain.job.Job
import org.joda.time.DateTime

/**
  * Created by hashcode on 2016/09/25.
  */
class JobFactory
{
  def createJob(values: Map[String, String], date: DateTime): Job=
  {
    Job(organisationId = values("organisationId"), jobId = values("jobId"), jobClassificationId = values("jobClassificationId"),
      title = values("title"), code = values("code"), description = values("description"), date = date, state = values("state"))
  }

}
