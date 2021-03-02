from pyspark import SparkConf
from pyspark import SparkContext
from pyspark import RDD

# Do the basic setup
myConf: SparkConf   = SparkConf().setAppName("WordCount").setMaster("local")
spark: SparkContext = SparkContext(conf=myConf)
spark.setLogLevel("ERROR")
print("-------------------------------")

# Read the file as an RDD where each item ("row") is a line from the
# text file
lines: RDD = spark.textFile("alice.txt")

result: RDD = lines.flatMap(lambda lineOfText: lineOfText.split()) \
                   .map(lambda word: (word, 1))      \
                   .reduceByKey(lambda a, b: a + b)

result.saveAsTextFile("output")
