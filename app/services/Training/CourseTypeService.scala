package services.Training

import domain.training.courses.CourseType
import services.Training.Impl.CourseTypeServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-09.
 */
trait CourseTypeService {
  def createOrUpdate(course: CourseType): Future[ResultSet]

  def getCourseTypeById( id: String): Future[Option[CourseType]]

  def getCourseTypes(): Future[Seq[CourseType]]

}

object CourseTypeService{
  def apply: CourseTypeService = new CourseTypeServiceImpl()
}
