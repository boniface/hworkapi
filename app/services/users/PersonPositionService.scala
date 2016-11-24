package services.users

import com.websudos.phantom.dsl._
import domain.users.PersonPosition
import services.users.impl.PersonPositionServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait PersonPositionService {
  def createOrupdate(personPosition : PersonPosition):Future[ResultSet]

  def getPersonPositionById(organisationId : String,userId: String, personPositionid: String): Future[Option[PersonPosition]]
  def getAllPersonPositions(person: String):Future[Seq[PersonPosition]]
}

object PersonPositionService{
  def apply: PersonAttachmentService = new PersonPositionServiceImpl()
}