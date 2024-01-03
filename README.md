# EO Ktor sample
Ktor пример в стиле EO

## SSL сертификат
Генерация сертификата с использованием keytool
`password: foobar`
```shell
$ keytool -keystore keystore.jks -alias sampleAlias -genkeypair -keyalg RSA -keysize 4096 -validity 3 -dname 'CN=localhost, OU=ktor, O=ktor, L=Unspecified, ST=Unspecified, C=US'
```

## Docker build & run
```shell
$ sudo docker build -t eo-ktor-sample .
$ sudo docker run -p 8080:8080 -p 8443:8443 eo-ktor-sample
```

## Docker-compose
```shell
$ sudo docker-compose -f docker-compose.yml up
```

## Hikari
Пока не понял как переиспользовать имя из docker-compose для базы данных `eo-ktor-database`, по этому в файле 
конфигурации приходится использовать реальный ip
```shell
# Получаем все созданные контейнеры
$ sudo docker ps -a
# Информация о контейнере
$ sudo docker container inspect $CONTAINER-NAME_OR_ID
```
```json
{
  "NetworkSettings": {
    "Networks": {
      "eo-ktor-sample_ktor-network": {
        "Gateway": "$IP"
      }
    }
  }
}
```