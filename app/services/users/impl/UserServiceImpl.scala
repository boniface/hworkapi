package services.users.impl

import com.websudos.phantom.dsl._
import domain.users.User
import repositories.users.UserRepository
import services.Service
import services.users.UserService

import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-24.
  */
class UserServiceImpl extends UserService with Service {
  def createOrUpdate(personRole: User): Future[ResultSet] = {
    UserRepository.save(personRole)
  }

  def getUserById(organisationId: String, userId: String): Future[Option[User]] = {
    UserRepository.findById(organisationId, userId)
  }

  def getUser(organisationId: String): Future[Seq[User]] = {
    UserRepository.getUser(organisationId)
  }
  def getAllUser(): Future[Seq[User]] = {
    UserRepository.getAllUser
  }


}