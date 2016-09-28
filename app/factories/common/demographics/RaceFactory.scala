package factories.common.demographics

import domain.common.demographics.Race

class RaceFactory {
  def createRace(values:Map[String, String]):Race={
 Race(RaceId = values("RaceId"),name = values("name"))
  }

}