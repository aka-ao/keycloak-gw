#!/bin/bash

sbt -Dinfo.akahori_s.keycloacgw.gateway.keycloak.admin.password=$1 -Dinfo.akahori_s.keycloacgw.gateway.keycloak.sample_application.secret=$2 -Dinfo.akahori_s.keycloacgw.gateway.keycloak.sample_api_gateway.secret=$3 entrypoint/run
