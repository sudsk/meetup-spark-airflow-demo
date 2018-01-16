package etl

import org.apache.spark.sql.SaveMode
import util.SparkJob


object Ib_Factual extends SparkJob {

  def main(args: Array[String]): Unit = {

    val inputPath = args(0)
    val outputPath = args(1)

    import spark.implicits._
    spark.read.json(inputPath)
      .withColumn("facebook_id", 'crosswalk_id_facebook)
      .withColumn("postalCode", 'postcode)
      .withColumn("city", 'locality)
      .write
      .mode(SaveMode.Overwrite)
      .parquet(outputPath)

  }

}