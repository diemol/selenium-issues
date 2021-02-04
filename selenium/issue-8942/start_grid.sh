SELENIUM_DOCKER_VER="4.0.0-beta-1-prerelease-20210201"
SELENIUM_HUB_IMAGE="selenium/hub:${SELENIUM_DOCKER_VER}"
SELENIUM_NODE_IMAGE="selenium/node-chrome:${SELENIUM_DOCKER_VER}"
SELENIUM_GRID=selenium-hub
K3D_NETWORK=grid
#THREAD_COUNT=$(($(nproc) / 2)) # gives 4 on our 8 core machines
THREAD_COUNT=4 # gives 4 on our 8 core machines

docker network rm ${K3D_NETWORK} || true
docker network create ${K3D_NETWORK}

docker run \
  --rm \
  --network "${K3D_NETWORK}" \
  --name ${SELENIUM_GRID} \
  -p 4442-4444:4442-4444 \
  ${SELENIUM_HUB_IMAGE} >"logs/selenium-hub.log" &

for i in $(seq 1 $THREAD_COUNT); do
  docker run \
    --rm \
    --network "${K3D_NETWORK}" \
    -v /dev/shm:/dev/shm \
    --name "chrome-node-$i" \
    -e SE_EVENT_BUS_HOST=${SELENIUM_GRID} \
    -e SE_EVENT_BUS_PUBLISH_PORT=4442 \
    -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
    -e START_XVFB=false \
    -e HUB_HOST=${SELENIUM_GRID} \
    -e SCREEN_WIDTH=1200 \
    -e SCREEN_HEIGHT=900 \
    ${SELENIUM_NODE_IMAGE} >"logs/chrome-node-$i.log" &
done
