package services.users

import com.websudos.phantom.dsl._
import domain.users.UserPosition
import services.users.impl.UserPositionServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait UserPositionService {
  def createOrupdate(personPosition : UserPosition):Future[ResultSet]

  def getPersonPositionById(organisationId : String,userId: String, personPositionid: String): Future[Option[UserPosition]]
  def getAllPersonPositions(person: String):Future[Seq[UserPosition]]
}

object UserPositionService{
  def apply: UserPositionService = new UserPositionServiceImpl()
}
