FROM selenium/standalone-firefox-debug:3.141.0-actinium

RUN curl -sL https://deb.nodesource.com/setup_8.x | sudo -E bash - \
  && sudo apt-get install -y nodejs \
  && node -v \
  && npm -v

COPY package.json \
  package-lock.json \
  test.js \
  start-virtual-desktop.sh \
  /home/seluser/

WORKDIR /home/seluser

RUN sudo chmod +x start-virtual-desktop.sh \
  && sudo npm install

CMD ["./start-virtual-desktop.sh", "node_modules/mocha/bin/mocha", "-t", "20000", "test.js"]
