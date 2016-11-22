package services.job.Impl

import com.websudos.phantom.dsl._
import domain.job.Job
import repositories.job.JobRepository
import services.Service
import services.job.JobService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/13.
  */
class JobServiceImpl extends JobService with Service{
  def createOrUpdate(job: Job): Future[ResultSet] = {
    JobRepository.save(job)
  }

  def getJobById(company: String, id: String): Future[Option[Job]] = {
    JobRepository.getJobById(company, id)
  }

  def getCompanyJobs(company: String): Future[Seq[Job]] = {
    JobRepository.getCompanyJobs(company)
  }


}
