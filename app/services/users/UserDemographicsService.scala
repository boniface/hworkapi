package services.users

import com.websudos.phantom.dsl._
import domain.users.UserDemographics
import services.users.impl.UserDemographicsServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait UserDemographicsService {
  def createOrupdate(personDemographics : UserDemographics):Future[ResultSet]

  def getPersonDemographicsById(organisationId : String,userId: String, personDemographicsid : String): Future[Option[UserDemographics]]
  def getPersonDemographics(person: String):Future[Seq[UserDemographics]]
}
object UserDemographicsService{
  def apply: UserDemographicsServiceImpl = new UserDemographicsServiceImpl()
}
