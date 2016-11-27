package services.Training

import domain.training.schedules.CourseFunding
import services.Training.Impl.CourseFundingServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-10.
 */
trait CourseFundingService {
  def createOrUpdate(course: CourseFunding): Future[ResultSet]

  def getCourseFundingById( id: String, fundingSourcesId: String): Future[Option[CourseFunding]]

  def getAllCourseFunding(): Future[Seq[CourseFunding]]

  def getCourseFunding(id: String): Future[Seq[CourseFunding]]

}

object CourseFundingService{
  def apply:CourseFundingService = new CourseFundingServiceImpl()
}
