package services.Training

import domain.training.courses.TargetGroup
import services.Training.Impl.TargetGroupServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-10.
 */
trait TargetGroupService {
  def createOrUpdate(course: TargetGroup): Future[ResultSet]

  def getTargetGroupById( id: String): Future[Option[TargetGroup]]

  def getTargetGroup(): Future[Seq[TargetGroup]]

}

object TargetGroupService{
  def apply:TargetGroupService = new TargetGroupServiceImpl()
}