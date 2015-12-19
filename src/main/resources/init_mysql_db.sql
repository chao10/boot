DROP TABLE IF EXISTS `user`;
--
-- IDs from 1 to 9999 are reserved for internal use.
-- Newly registered users' ID will start from 10000.
--
CREATE TABLE user (
  id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  email VARCHAR(50),
  password VARCHAR(500),
  activated BOOLEAN DEFAULT FALSE,
  activationkey VARCHAR(50) DEFAULT NULL,
  resetpasswordkey VARCHAR(50) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `authority`;
CREATE TABLE authority (
  id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  authority VARCHAR(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE user_authority (
    user_id INT(10) NOT NULL,
    authority_id INT(10) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (authority_id) REFERENCES authority (id),
    UNIQUE INDEX user_authority_idx_1 (user_id, authority_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE oauth_access_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication_id VARCHAR(256) DEFAULT NULL,
  user_name VARCHAR(256) DEFAULT NULL,
  client_id VARCHAR(256) DEFAULT NULL,
  authentication BLOB,
  refresh_token VARCHAR(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO user (id, username,email, password, activated) VALUES (1, 'user', 'user@mail.me',
'user', true);
INSERT INTO user (id, username, email, password, activated) VALUES (2, 'admin', 'admin@mail.me',
'admin', true);

INSERT INTO authority (authority) VALUES ('ROLE_USER');
INSERT INTO authority (authority) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);