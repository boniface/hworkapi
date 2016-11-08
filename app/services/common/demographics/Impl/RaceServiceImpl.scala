package services.common.demographics.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.demographics.Race
import repositories.common.demographics.RaceRepository
import services.Service
import services.common.demographics.RaceService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
class RaceServiceImpl extends RaceService with Service{
  override def createOrUpdate(race: Race): Future[ResultSet] = {
    RaceRepository.save(race)
  }

  override def getRaceById(id: String): Future[Option[Race]] = {
    RaceRepository.getRaceById(id)
  }

  override def getRaces(id: String): Future[Seq[Race]] = {
    RaceRepository.getRaces(id)
  }
}
