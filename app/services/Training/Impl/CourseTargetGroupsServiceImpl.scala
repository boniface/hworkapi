package services.Training.Impl

import domain.training.competencies.Evaluation
import domain.training.courses.CourseTargetGroups
import repositories.Training.competencies.EvaluationRepository
import repositories.Training.courses.CourseTargetGroupsRepository
import services.Service
import services.Training.{CourseTargetGroupService, EvaluationService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseTargetGroupsServiceImpl extends CourseTargetGroupService with Service{
  def createOrUpdate( courseTargetGroups: CourseTargetGroups): Future[ResultSet] = {
    CourseTargetGroupsRepository.save(courseTargetGroups)
  }

  def getCourseTargetGroupsById( id: String): Future[Option[CourseTargetGroups]] = {
    CourseTargetGroupsRepository.getCourseTargetGroupsById( id)
  }

  def getCourseTargetGroups(): Future[Seq[CourseTargetGroups]] = {
    CourseTargetGroupsRepository.getAllCourseCourseTargetGroups
  }


}
