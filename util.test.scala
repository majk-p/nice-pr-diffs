//> using scala "3"
//> using test.dep "org.scalameta::munit::0.7.29"
import os.Path

class Test extends munit.FunSuite {
  test("should read test file and find data") {
    val testRecordId    = "ENTOCR13RSCLZ6KU"
    val expectedVersion = 1.88
    val util            = JsonUtility.instance
    val testFilePath    = os.pwd / "test-data" / "1MB.json"
    val result          = util
      .findInFile(testFilePath)(_.id == testRecordId)
      .map(_.version)

    assert(result == Some(expectedVersion))
  }

}
