#!/bin/bash
branch="staging"
compose_file="docker-compose.staging.yml"
git fetch --all
git reset --hard HEAD
git checkout $branch || echo "Could not checkout to $branch"
git pull origin $branch || echo "Cloud not pull from origin/$branch"
docker-compose -f $compose_file build && docker-compose -f $compose_file up -d
