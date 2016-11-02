package factories.common.demographics

import domain.common.demographics.Race

class RaceFactory {
  def createRace(values:Map[String, String]):Race={
 Race(raceId = values("RaceId"),name = values("name"))
  }

}