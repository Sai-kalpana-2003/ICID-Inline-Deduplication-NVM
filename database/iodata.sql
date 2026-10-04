CREATE DATABASE IF NOT EXISTS iodata;
USE iodata;

CREATE TABLE IF NOT EXISTS register (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60) NOT NULL UNIQUE,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(200) NOT NULL,      -- stored as salt$PBKDF2-hash, never plain text
  mobile VARCHAR(15),
  gender VARCHAR(10),
  address VARCHAR(100),
  securitykey VARCHAR(100)
);

-- Unique content that passed the inline deduplication check
CREATE TABLE IF NOT EXISTS files (
  id INT AUTO_INCREMENT PRIMARY KEY,
  from_user VARCHAR(60) NOT NULL,
  dataset_name VARCHAR(150) NOT NULL,
  dataset MEDIUMTEXT NOT NULL,
  fingerprint CHAR(64) NOT NULL UNIQUE, -- SHA-256, catches exact duplicates instantly
  uploaded_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Every similarity check (stored or rejected), used for the statistics page
CREATE TABLE IF NOT EXISTS similarity_log (
  id INT AUTO_INCREMENT PRIMARY KEY,
  from_user VARCHAR(60),
  dataset_name VARCHAR(150),
  score INT NOT NULL,                   -- 0..100
  stored BOOLEAN NOT NULL,
  logged_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
