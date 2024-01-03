#./gradlew :eo-ktor-sample:buildFatJar
#./gradlew buildFatJar
#sudo docker compose --project-directory wilddisk/eo-ktor up
#./gradlew clean
./gradlew clean buildFatJar --no-daemon
docker build -t wilddisk/eo-ktor .
#docker run -ti --rm -p 8082:8082 -p 9443:9443 wilddisk/eo-ktor
#docker compose --project-directory wilddisk/eo-ktor up
#docker run -p 8082:8082 -p 9443:9443 wilddisk/eo-ktor up