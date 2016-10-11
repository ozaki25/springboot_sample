# springboot_sample

* mvn -B archetype:generate -DgroupId=hello -DartifactId=springboot_sample -Dversion=1.0.0-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart
* quick startのサンプルを作っていく
http://projects.spring.io/spring-boot/#quick-start
* pom.xmlに追記

    ```
    <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>1.3.5.RELEASE</version>
    </parent>
    <dependencies>
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
    </dependencies>
    ```

* App.javaの修正

    ```
    package hello;

    import org.springframework.boot.*;
    import org.springframework.boot.autoconfigure.*;
    import org.springframework.stereotype.*;
    import org.springframework.web.bind.annotation.*;
    
    @Controller
    @EnableAutoConfiguration
    public class SampleController {
    
        @RequestMapping("/")
        @ResponseBody
        String home() {
            return "Hello World!";
        }
    
        public static void main(String[] args) throws Exception {
            SpringApplication.run(SampleController.class, args);
        }
    }
    ```
* 開発時
  * mvn spring-boot:run
    * アプリが起動するのでhttp://localhost:8080にアクセスするとHello Worldが表示される

* 本番時
  * mvn package
  * java -jar target/springboot_sample-1.0.0-SNAPSHOT.jar