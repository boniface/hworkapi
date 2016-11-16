package services.Training.Impl

import domain.training.competencies.Evaluation
import domain.training.courses.CourseCompetencies
import repositories.Training.competencies.EvaluationRepository
import repositories.Training.courses.CourseCompetenciesRepository
import services.Service
import services.Training.{CourseCompetenciesService, EvaluationService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseCompetenciesServiceImpl extends CourseCompetenciesService with Service{
  def createOrUpdate(courseCompetencies: CourseCompetencies): Future[ResultSet] = {
    CourseCompetenciesRepository.save(courseCompetencies)
  }

  def getCourseCompetenciesById( id: String): Future[Option[CourseCompetencies]] = {
    CourseCompetenciesRepository.getCourseCompetenciesById( id)
  }

  def getCourseCompetencies(): Future[Seq[CourseCompetencies]] = {
    CourseCompetenciesRepository.getAllCourseCourseCompetencies
  }


}