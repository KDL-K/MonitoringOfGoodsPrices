mysql -h 127.0.0.1 -u root -p < DBPricemonitoringCreator.sql
pause
mysql --default-character-set=utf8 -h 127.0.0.1 -u root -p < DBPricemonitoringFiller.sql 
pause