package factories.storage

import domain.storage.FileResults

/**
  * Created by SONY on 2016-09-28.
  */
class FileResultsFactory
{
  def createFileResults(values: Map[String, String]): FileResults=
  {
    FileResults(organisationId = values("organisationId"), fileResultsId = values("fileResultsId"),
      url = values("url"), size = Option("size"))
  }

}
