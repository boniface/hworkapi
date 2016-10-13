package services.job

import com.websudos.phantom.dsl._
import domain.job.JobEvent
import services.job.Impl.JobEventServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/13.
  */
trait JobEventService {

  def createOrUpdate(jobEvent: JobEvent): Future[ResultSet]

  def getJobEventById(jobId: String, id: String): Future[Option[JobEvent]]

  def getJobEvents(jobId: String): Future[Seq[JobEvent]]
}

object JobEventService{
  def apply: JobEventService = new JobEventServiceImpl()
}
