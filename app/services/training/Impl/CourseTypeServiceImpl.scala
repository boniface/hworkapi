package services.Training.Impl

//import domain.training.competencies.Evaluation
import domain.training.courses.CourseType
//import repositories.Training.competencies.EvaluationRepository
//import repositories.Training.courses.CourseTypeRepository
import services.Service
import services.Training.{CourseTypeService}

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.courses.CourseTypeRepository
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseTypeServiceImpl extends CourseTypeService with Service{
  def createOrUpdate(courseType: CourseType): Future[ResultSet] = {
    CourseTypeRepository.save(courseType)
  }

  def getCourseTypeById( id: String): Future[Option[CourseType]] = {
    CourseTypeRepository.getCourseTypesById( id)
  }

  def getCourseTypes(id: String): Future[Seq[CourseType]] = {
    CourseTypeRepository.getCourseTypes(id)
  }


}
