package services.common.demographics

import com.websudos.phantom.dsl._
import domain.common.demographics.Race
import services.common.demographics.Impl.RaceServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
trait RaceService {

  def createOrUpdate(race:Race):Future[ResultSet]

  def getRaceById(race:String):Future[Option[Race]]

  def getRaces(race:String):Future[Seq[Race]]

}

object RaceService{
  def apply: RaceService = new RaceServiceImpl()
}
