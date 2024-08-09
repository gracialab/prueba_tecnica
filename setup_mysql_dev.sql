-- Create database + user if doesn't exist
CREATE DATABASE IF NOT EXISTS glab_test_db;
CREATE USER IF NOT EXISTS 'glab_test_dev'@'localhost' IDENTIFIED BY 'glab_test_pwd';
GRANT ALL ON `glab_test_db`.* TO 'glab_test_dev'@'localhost';
GRANT SELECT ON `performance_schema`.* TO 'glab_test_dev'@'localhost';
FLUSH PRIVILEGES;

