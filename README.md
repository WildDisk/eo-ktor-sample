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
$ docker build -t eo-ktor-sample .
$ docker run -p 8080:8080 -p 8443:8443 eo-ktor-sample
```