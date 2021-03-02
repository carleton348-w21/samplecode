from pyspark.sql import SparkSession
from pyspark.sql import DataFrame
import pyspark.sql.functions as f

# Do the basic setup
spark: SparkSession = SparkSession.builder.appName("WordCount").getOrCreate()
# Read the file as a DataFrame where each item ("row") is a line from the
# text file
lines: DataFrame = spark.read.text("alice.txt")

result: DataFrame = lines.select(f.explode(f.split('value', ' ')).alias("words"))  \
.groupBy('words').count()

result.show()

result.coalesce(1).write.csv("output")

# lines: RDD = spark.textFile("alice.txt")

# result: RDD = lines.flatMap(lambda lineOfText: lineOfText.split()) \
#                    .map(lambda word: (word, 1))      \
#                    .reduceByKey(lambda a, b: a + b)

# result.saveAsTextFile("output")
