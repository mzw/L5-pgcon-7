```
$ mvn clean compile dependency:copy-dependencies
$ java -cp target/classes:target/dependency/* jp.mzw.l5.pgcon7.CLI src/test/resources/src1.txt
```