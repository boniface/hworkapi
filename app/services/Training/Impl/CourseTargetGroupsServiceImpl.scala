package services.Training.Impl

import domain.training.courses.CourseTargetGroups
import services.Service
import services.Training.{CourseTargetGroupService}

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.courses.CourseTargetGroupsRepository
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseTargetGroupsServiceImpl extends CourseTargetGroupService with Service{
  def createOrUpdate( courseTargetGroups: CourseTargetGroups): Future[ResultSet] = {
    CourseTargetGroupsRepository.save(courseTargetGroups)
  }

  def getCourseTargetGroupsById( id: String, courseId: String, targetGroupId: String): Future[Option[CourseTargetGroups]] = {
    CourseTargetGroupsRepository.getCourseTargetGroupsById( id,courseId, targetGroupId)
  }

  def getCourseTargetGroups(id: String): Future[Seq[CourseTargetGroups]] = {
    CourseTargetGroupsRepository.getCourseTargetGroups(id)
  }


}
