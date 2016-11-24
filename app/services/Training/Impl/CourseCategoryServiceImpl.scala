package services.Training.Impl

import com.websudos.phantom.dsl._
import domain.training.courses.CourseCategory
//import repositories.Training.courses.CourseCategoryRepository
import services.Service
import services.Training.{CourseCategoryService}

import scala.collection.Seq
import scala.concurrent.Future
import repositories.training.courses.CourseCategoryRepository
/**
 * Created by gavin.ackerman on 2016-11-12.
 */
class CourseCategoryServiceImpl extends CourseCategoryService with Service{
  def createOrUpdate(courseCategory: CourseCategory): Future[ResultSet] = {
    CourseCategoryRepository.save(courseCategory)
  }

  def getCourseCategoryById( id: String, courseCategoryId:String): Future[Option[CourseCategory]] = {
    CourseCategoryRepository.getCourseCategoryById( id, courseCategoryId)
  }

  def getCourseCategorys(id: String): Future[Seq[CourseCategory]] = {
    CourseCategoryRepository.getCourseCategorys(id)
  }


}
