package services.Training

import domain.training.courses.{CourseCompetencies, CourseCategory}
import services.Training.Impl.CourseCompetenciesServiceImpl
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-09.
 */
trait CourseCompetenciesService {
  def createOrUpdate(courseCompetencies: CourseCompetencies): Future[ResultSet]

  def getCourseCompetenciesById( id: String): Future[Option[CourseCompetencies]]

  def getCourseCompetenciess(): Future[Seq[CourseCompetencies]]

}

object CourseCompetenciesService{
  def apply: CourseCompetenciesService = new CourseCompetenciesServiceImpl()
}