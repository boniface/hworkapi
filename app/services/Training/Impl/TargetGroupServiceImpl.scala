package services.Training.Impl


import domain.training.courses.TargetGroup

import services.Service
import services.Training.{TargetGroupService}

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.courses.TargetGroupRepository
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

  def getTargetGroup(id: String): Future[Seq[TargetGroup]] = {
    TargetGroupRepository.getTargetGroup(id)
  }

}
