### To run locally:

`./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`

### To run on production server (requires keystore):

`java -jar -Dspring.profiles.active=prod <jar-file-name>.jar`
