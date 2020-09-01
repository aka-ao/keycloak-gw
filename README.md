# 目的
- Scala, akkaを利用したシステムからKeycloakを使った認証認可を行う

# Keycloak構築
## Keycloak起動まで
- `git clone https://github.com/keycloak/keycloak-containers.git`
- `cd keycloak-containers/docker-compose-examples/`
- `docker-compose -f keycloak-postgres.yml up -d`

## 起動後の設定
- https://thinkit.co.jp/article/17621

# 使い方
## 起動方法
- `git clone https://github.com/akapo001/keycloak-gw.git`
- `./bin/keycloak-gw.sh $admin_password $secret1 $secret2`

## 起動後
- ユーザ作成
```curl
curl -i -X POST \
      -H "Content-Type:application/json" \
      -d \
   '{
     "firstName": "test",
     "lastName": "test",
     "email": "test@example.com",
     "userName": "test",
     "password": "test"
   }' \
    'http://localhost:18080/create'
  ```

- トークン取得
```curl
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
  "userName": "test",
  "password": "test"
}' \
 'http://localhost:18080/token'
```