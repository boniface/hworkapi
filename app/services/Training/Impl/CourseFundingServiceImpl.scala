package services.Training.Impl

import domain.training.schedules.CourseFunding
import services.Service
import services.Training.CourseFundingService

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.schedules.CourseFundingRepository
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseFundingServiceImpl extends CourseFundingService with Service{
  def createOrUpdate(courseFunding: CourseFunding): Future[ResultSet] = {
    CourseFundingRepository.save(courseFunding)
  }

  def getCourseFundingById( id: String, fundingSourcesId: String): Future[Option[CourseFunding]] = {
    CourseFundingRepository.getCourseFundingById( id, fundingSourcesId)
  }

  def getAllCourseFunding(): Future[Seq[CourseFunding]] = {
    CourseFundingRepository.getAllCourseFunding
  }
  def getCourseFunding(id: String): Future[Seq[CourseFunding]] = {
    CourseFundingRepository.getCourseFunding(id)
  }

}
