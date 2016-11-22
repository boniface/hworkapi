package services.Training.Impl

import domain.training.competencies.Evaluation
import domain.training.courses.CourseType
import repositories.Training.competencies.EvaluationRepository
import repositories.Training.courses.CourseTypeRepository
import services.Service
import services.Training.{CourseTypeService, EvaluationService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseTypeServiceImpl extends CourseTypeService with Service{
  def createOrUpdate(courseType: CourseType): Future[ResultSet] = {
    CourseTypeRepository.save(courseType)
  }

  def getEvaluationById( id: String): Future[Option[CourseType]] = {
    CourseTypeRepository.getCourseTypesById( id)
  }

  def getEvaluation(): Future[Seq[CourseType]] = {
    CourseTypeRepository.getAllCourseTypeGroups
  }


}
