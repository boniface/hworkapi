package services.Training.Impl

import domain.training.competencies.Evaluation
import domain.training.courses.TargetGroup
import repositories.Training.competencies.EvaluationRepository
import repositories.Training.courses.TargetGroupRepository
import services.Service
import services.Training.{TargetGroupService, EvaluationService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class TargetGroupServiceImpl extends TargetGroupService with Service{
  def createOrUpdate(targetGroup: TargetGroup): Future[ResultSet] = {
    TargetGroupRepository.save(targetGroup)
  }

  def getTargetGroupById( id: String): Future[Option[TargetGroup]] = {
    TargetGroupRepository.getTargetGroupById( id)
  }

  def getTargetGroup(): Future[Seq[TargetGroup]] = {
    TargetGroupRepository.getAllTargetGroup
  }


}