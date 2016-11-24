package services.Training.Impl

import domain.training.courses.CourseCompetencies
import services.Service
import services.Training.CourseCompetenciesService

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.courses.CourseCompetenciesRepository
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseCompetenciesServiceImpl extends CourseCompetenciesService with Service{
  def createOrUpdate(courseCompetencies: CourseCompetencies): Future[ResultSet] = {
    CourseCompetenciesRepository.save(courseCompetencies)
  }

  def getCourseCompetenciesById( id: String, courseId: String): Future[Option[CourseCompetencies]] = {
    CourseCompetenciesRepository.getCourseCompetenciesById( id,courseId )
  }

  def getAllCourseCourseCompetencies(): Future[Seq[CourseCompetencies]] = {
    CourseCompetenciesRepository.getAllCourseCourseCompetencies
  }
  def getCourseCompetencies(id: String): Future[Seq[CourseCompetencies]] = {
    CourseCompetenciesRepository.getCourseCompetencies(id)
  }


}