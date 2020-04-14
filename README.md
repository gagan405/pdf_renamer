## PDF Renamer

PDF files, like research papers, downloaded from the web are not always named comprehensively.
For example, I cannot figure out what is the content of a file by looking at its name : `p28.pdf` or `Erni08b.pdf`.

If you are among those people who immediately rename the downloaded PDF to something meaningful, this is not for you!

For all those who keep opening random PDF files just to realize that its not what you were looking for,
maybe this tool will be helpful for organizing the files a bit better.

### What it does?

It reads the PDF metadata or the first page of the PDF and extracts the 'possible' title of the file
and renames the file. See examples below.

### How to use?

**Build**
~~~
./gradlew clean build
~~~

**Usage**
~~~
java -jar build/libs/PdfNamer-1.0-SNAPSHOT.jar --help
Usage: <main class> [options]
  Options:
  * --directory, -d
      Absolute path to the directory containing PDF files
    --extractor, -e
      Name extractor to use: InfoExtractor, FirstLineExtractor. Default
      behavior uses both extractors in the order : Info -> FirstLine
    --help

    --max-name-length, -l
      Max length of the file name. Default is 100.
  * --naming-strategy, -n
      Naming strategy to use: SNAKE_CASE, HYPHENATED, CAMEL_CASE
      Possible Values: [SNAKE_CASE, HYPHENATED, CAMEL_CASE]
    --recursive, -r
      Should the renaming be run recursively to all sub directories?
      Default: false

~~~

**Example**

*Before*
~~~
➜  articles ll
total 173552
-rw-r--r--@ 1 mishraga  1896053708   679K Apr 13 22:41 3381307.3381310.pdf
-rw-r--r--@ 1 mishraga  1896053708    81M Apr 13 22:41 6464.pdf
-rw-r--r--@ 1 mishraga  1896053708   196K Apr 13 22:41 Erni08b.pdf
-rw-r--r--@ 1 mishraga  1896053708   745K Apr 13 22:41 g1-vee-2020.pdf
-rw-r--r--@ 1 mishraga  1896053708   288K Apr 13 22:41 p28.pdf
-rw-r--r--@ 1 mishraga  1896053708   1.8M Apr 13 22:41 pivot-tracing.pdf
➜  articles pwd
/Users/mishraga/Documents/articles
~~~

*Use the tool*
~~~
➜  PdfNamer git:(master) ✗ java -jar build/libs/PdfNamer-1.0-SNAPSHOT.jar -d /Users/mishraga/Documents/articles -n HYPHENATED
Renaming file: 3381307.3381310.pdf
Renamed file to: open-source-tools-and-benchmarks-for-code-clone-detection.pdf
Renaming file: p28.pdf
Renamed file to: apache-flink-stream-and-batch-processing-in-a-single-engine-paris-carbone-asterios-katsifodimos.pdf
Renaming file: g1-vee-2020.pdf
Renamed file to: deconstructing-the-garbage-first-collector.pdf
Renaming file: pivot-tracing.pdf
Renamed file to: pivot-tracing-dynamic-causal-monitoring-for-distributed-systems.pdf
Renaming file: 6464.pdf
Renamed file to: d-l--i--t-l--l-for-consulivtion-onjjfss-j_-for-official-use-only-m-the-revolt-in-central-india.pdf
Renaming file: Erni08b.pdf
Renamed file to: the-hackers-guide-to-javac-david-erni-and-adrian-kuhn-university-of-bern-march-2008-abstract-this.pdf
~~~

*After*
~~~
➜  articles ll
total 173552
-rw-r--r--@ 1 mishraga  1896053708   288K Apr 13 22:41 apache-flink-stream-and-batch-processing-in-a-single-engine-paris-carbone-asterios-katsifodimos.pdf
-rw-r--r--@ 1 mishraga  1896053708    81M Apr 13 22:41 d-l--i--t-l--l-for-consulivtion-onjjfss-j_-for-official-use-only-m-the-revolt-in-central-india.pdf
-rw-r--r--@ 1 mishraga  1896053708   745K Apr 13 22:41 deconstructing-the-garbage-first-collector.pdf
-rw-r--r--@ 1 mishraga  1896053708   679K Apr 13 22:41 open-source-tools-and-benchmarks-for-code-clone-detection.pdf
-rw-r--r--@ 1 mishraga  1896053708   1.8M Apr 13 22:41 pivot-tracing-dynamic-causal-monitoring-for-distributed-systems.pdf
-rw-r--r--@ 1 mishraga  1896053708   196K Apr 13 22:41 the-hackers-guide-to-javac-david-erni-and-adrian-kuhn-university-of-bern-march-2008-abstract-this.pdf
~~~