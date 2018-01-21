# Taken from https://github.com/tristanpenman/docker-php-webdriver/blob/master/Dockerfile and then updated
FROM php:7

# Install dependencies
RUN apt-get update \
&& apt-get install -y unzip zlib1g-dev \
&& apt-get clean \
&& rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Install Composer
RUN cd /usr/local/bin \
  && curl -sS https://getcomposer.org/installer | php \
  && ln -sf /usr/local/bin/composer.phar /usr/local/bin/composer \
  && docker-php-ext-install zip

# Install php-webdriver and PHPUnit
COPY composer.json /wd/composer.json
RUN cd /wd \
  && composer install \
  && ln -sf /wd/vendor/phpunit/phpunit/phpunit /usr/local/bin/phpunit

# Setup working directory
RUN mkdir -p /wd/src
WORKDIR /wd/src

# Set up entrypoint script
ENV SCRIPTS_DIR /scripts
RUN mkdir -p /scripts/entrypoint.d
COPY docker-entrypoint.sh /scripts/entrypoint.sh
RUN chmod +x /scripts/entrypoint.sh
ENTRYPOINT ["/scripts/entrypoint.sh"]

CMD ["phpunit","."]