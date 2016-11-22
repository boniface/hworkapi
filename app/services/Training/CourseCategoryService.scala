package services.Training

import domain.training.courses.CourseCategory
import services.Training.Impl.CourseCategoryServiceImpl
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-09.
 */
trait CourseCategoryService {
  def createOrUpdate(courseCategory: CourseCategory): Future[ResultSet]

  def getCourseCategoryById( id: String): Future[Option[CourseCategory]]

  def getCourseCategorys(id: String): Future[Seq[CourseCategory]]

}

object CourseCategoryService{
  def apply: CourseCategoryService = new CourseCategoryServiceImpl()
}