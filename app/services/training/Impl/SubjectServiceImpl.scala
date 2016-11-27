package services.Training.Impl
import domain.training.courses.Subject

import services.Service
import services.Training.{SubjectService}

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.courses.SubjectRepository
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class SubjectServiceImpl extends SubjectService with Service{
  def createOrUpdate(subject: Subject): Future[ResultSet] = {
    SubjectRepository.save(subject)
  }

  def getSubjectById( id: String): Future[Option[Subject]] = {
    SubjectRepository.getSubjectById( id)
  }

  def getSubject(id: String): Future[Seq[Subject]] = {
    SubjectRepository.getSubject(id)
  }
}
