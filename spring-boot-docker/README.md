

## maven command for building image
```$xslt
mvn clean package docker:build

mvn clean package docker:build -DpushImage

mvn clean package docker:build -DpushImageTag


```

https://github.com/spotify/docker-maven-plugin