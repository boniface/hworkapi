package services.Training.Impl

import java.sql.ResultSet

import domain.training.competencies.Evaluation
import domain.training.courses.CourseCategory
import repositories.Training.competencies.EvaluationRepository
import repositories.Training.courses.CourseCategoryRepository
import services.Service
import services.Training.{CourseCategoryService, EvaluationService}
import scala.collection.Seq
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-12.
 */
class CourseCategoryServiceImpl extends CourseCategoryService with Service{
  def createOrUpdate(courseCategory: CourseCategory): Future[ResultSet] = {
    CourseCategoryRepository.save(courseCategory)
  }

  def getCourseCategoryById( id: String): Future[Option[CourseCategory]] = {
    CourseCategoryRepository.getCourseCategoryById( id)
  }

  def getCourseCategory(): Future[Seq[CourseCategory]] = {
    CourseCategoryRepository.getAllCourseCategory
  }


}
