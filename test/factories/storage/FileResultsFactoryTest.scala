package factories.storage

import domain.storage.FileResults
import org.scalatest.FunSuite

/**
  * Created by SONY on 2016-09-28.
  */
class FileResultsFactoryTest extends FunSuite{

  test("testCreateFileResults"){
    val fileR = new FileResultsFactory;

    val values = Map("organisationId" -> "1",
      "fileResultsId" -> "F78",
      "url" -> "KJ1"
    );

    val fResults = fileR.createFileResults(values);

    assert(fResults == FileResults(organisationId = "1",
      fileResultsId = "F78",
      url = "KJ1",
      size = Option("KJ1")
    ));
  }
}
