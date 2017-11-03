FROM php:5.6-apache

RUN a2enmod rewrite
RUN service apache2 restart

RUN docker-php-ext-install pdo pdo_mysql

EXPOSE 80
