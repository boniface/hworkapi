package services.common.job.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.job.JobClassification
import repositories.common.job.JobClassificationRepository
import services.Service
import services.common.job.JobClassificationService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
class JobClassificationServiceImpl extends JobClassificationService with Service{
  override def createOrUpdate(jobClassification: JobClassification): Future[ResultSet] = {
    JobClassificationRepository.save(jobClassification)
  }

  override def getJobClassificationById(jobClassification: String): Future[Option[JobClassification]] = {
    JobClassificationRepository.getJobClassificationById(jobClassification)
  }

  override def getJobClassifications(jobClassification: String): Future[Seq[JobClassification]] = {
    JobClassificationRepository.getJobClassifications(jobClassification)
  }
}
