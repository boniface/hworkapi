package services.setup

import repositories.users.UserRepository
import services.Service

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/11/27.
  */
object SetupService extends Service{

  def runSetUp = for {

    createTable <-UserRepository.create.ifNotExists().future()

  } yield createTable

}
