package services.job

import com.websudos.phantom.dsl._
import domain.job.Job
import services.job.Impl.JobServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/13.
  */
trait JobService {
  def createOrUpdate(job: Job): Future[ResultSet]

  def getJobById(company: String, id: String): Future[Option[Job]]

  def getCompanyJobs(company: String): Future[Seq[Job]]

}

object JobService{
  def apply: JobService = new JobServiceImpl()
}
