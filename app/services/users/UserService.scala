package services.users

import com.websudos.phantom.dsl._
import domain.users.User
import services.users.impl.UserServiceImpl

import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-24.
  */
trait UserService
{
  def createOrUpdate(personRole: User): Future[ResultSet]

  def getUserById(organisationId: String, userId: String): Future[Option[User]]

  def getUser(organisationId: String): Future[Seq[User]]
}

object UserService{
  def apply: UserService = new UserServiceImpl()
}