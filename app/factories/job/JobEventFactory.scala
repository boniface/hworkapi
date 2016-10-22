package factories.job

import domain.job.JobEvent
import org.joda.time.DateTime

/**
  * Created by hashcode on 2016/09/26.
  */
class JobEventFactory {
  def createJobEvent(values:Map[String, String],date:DateTime):JobEvent={
    JobEvent(jobId = values("jobId"),jobEventId = values("jobEventId"),date=date,event = values("event"))
  }

}