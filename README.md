# quarkus-bank project

Quarkus version of [nikitsenka/bank-java](https://github.com/nikitsenka/bank-java) with the purpose of benchmarking it in aws vs go.

At the time I did this, the java version is ~3yrs old, so I thought it needed some work.

Inspired by [Kai Hendry's java vs go blog post](https://dabase.com/blog/2021/java-vs-go-load-test/#load-testing-gojava-on-aws).

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
docker-compose up # for postgres
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

## Creating a native executable

> WARNING: Takes a long time (~8m on my 15" 2018 MBP, needs a lot of ram)

You can create a native executable via docker container:
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/quarkus-bank-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Sure, all this is great, but I need my docker image!
Generate it using:
```bash
./gradlew build -Dquarkus.container-image.build=true
```

## Testing with JMeter

```shell
brew install jmeter
git clone git@github.com:nikitsenka/bank-test.git
cd bank-test/jmeter
jmeter -JHOST=localhost -JPORT=8080 -JNUM_USERS=1 -n -t bank-test.jmx -l results.csv
```