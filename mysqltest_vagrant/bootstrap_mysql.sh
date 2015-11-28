#!/usr/bin/env bash

sudo apt-get update

#install debconf utilities and prepare password for mysql installation
sudo apt-get install -y debconf-utils
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password 1234'
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password 1234'

#install mysql and change the my.cnf
sudo apt-get install -y mysql-server
sudo yes | cp -rf /vagrant/my.cnf /etc/mysql/my.cnf 

if [ ! -f /var/log/databasesetup ];
then
    echo "CREATE DATABASE jobo" | mysql -uroot -p1234
    echo "CREATE USER 'admin'@'172.0.0.103' IDENTIFIED BY 'pass'" | mysql -uroot -p1234
    echo "GRANT ALL ON jobo.* TO 'admin'@'172.0.0.103'" | mysql -uroot -p1234
	echo "CREATE USER 'admin'@'10.0.2.2' IDENTIFIED BY 'pass'" | mysql -uroot -p1234
    echo "GRANT ALL ON jobo.* TO 'admin'@'10.0.2.2'" | mysql -uroot -p1234
	echo "CREATE USER 'usercon'@'10.0.2.2' IDENTIFIED BY 'pass'" | mysql -uroot -p1234
    echo "GRANT ALL ON jobo.* TO 'usercon'@'10.0.2.2'" | mysql -uroot -p1234
	echo "CREATE USER 'usercon'@'127.0.0.103' IDENTIFIED BY 'pass'" | mysql -uroot -p1234
    echo "GRANT ALL ON jobo.* TO 'usercon'@'127.0.0.103'" | mysql -uroot -p1234
	echo "CREATE USER 'usercon'@'172.0.0.103' IDENTIFIED BY 'pass'" | mysql -uroot -p1234
    echo "GRANT ALL ON jobo.* TO 'usercon'@'172.0.0.103'" | mysql -uroot -p1234
    echo "flush privileges" | mysql -uroot -p1234
	
    touch /var/log/databasesetup

    #if [ -f /vagrant/data/initial.sql ];
    #then
    #    mysql -uroot -p1234 jobo < /vagrant/data/initial.sql
    #fi
	
	mysql -uroot -p1234 jobo < /vagrant/createjobotables.sql
fi

sudo service mysql restart
