package services.Training

import domain.training.courses.Subject
import services.Training.Impl.SubjectServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-10.
 */
trait SubjectService {
  def createOrUpdate(course: Subject): Future[ResultSet]

  def getSubjectById( id: String): Future[Option[Subject]]

  def getSubject(id: String): Future[Seq[Subject]]

}

object SubjectService{
  def apply:SubjectService = new SubjectServiceImpl()
}