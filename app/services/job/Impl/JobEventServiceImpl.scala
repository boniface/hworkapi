package services.job.Impl

import com.websudos.phantom.dsl._
import domain.job.JobEvent
import repositories.job.JobEventRepository
import services.Service
import services.job.{JobEventService, JobService}

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/13.
  */
class JobEventServiceImpl extends JobEventService with Service {
  def createOrUpdate(jobEvent: JobEvent): Future[ResultSet] = {
    JobEventRepository.save(jobEvent)
  }

  def getJobEventById(jobId: String, id: String): Future[Option[JobEvent]] = {
    JobEventRepository.getJobEventById(jobId, id)
  }

  def getJobEvents(jobId: String): Future[Seq[JobEvent]] = {
    JobEventRepository.getJobEvents(jobId)
  }


}
