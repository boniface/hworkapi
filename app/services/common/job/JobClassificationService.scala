package services.common.job

import com.websudos.phantom.dsl._
import domain.common.job.JobClassification
import services.common.job.Impl.JobClassificationServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
trait JobClassificationService {


  def createOrUpdate(jobClassification:JobClassification):Future[ResultSet]

  def getJobClassificationById(evaluationId: String): Future[Option[JobClassification]]

  def getJobClassifications(evaluationId: String): Future[Seq[JobClassification]]
}

object JobClassificationService{
  def apply: JobClassificationService = new JobClassificationServiceImpl()

}
