CREATE SCHEMA final_project;

USE Final_Project;

CREATE TABLE all_permissions(
permission_id INT AUTO_INCREMENT,
permission_name CHAR(40),
PRIMARY KEY(permission_id, permission_name)
);

CREATE TABLE all_health_status(
health_status_id INT AUTO_INCREMENT,
health_status_name CHAR(40),
health_status_space INT,
PRIMARY KEY(health_status_id, health_status_name)
);

CREATE TABLE all_users(
user_id INT AUTO_INCREMENT,
user_name CHAR(40),
user_email CHAR(40),
user_phone_number CHAR(11),
user_permission INT,
PRIMARY KEY(user_id, user_email, user_phone_number),
FOREIGN KEY(user_permission) REFERENCES all_permissions(permission_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE all_events(
event_id INT AUTO_INCREMENT,
event_name CHAR(40),
event_city CHAR(40),
event_neighborhood CHAR(40),
event_neighborhood_num INT,
event_year INT,
event_month INT,
event_day INT,
event_hour INT,
event_minute INT,
event_num_of_rows INT,
event_max_num_of_seats_in_row INT,
event_next_invitation_num INT,
PRIMARY KEY(event_id)
);

CREATE TABLE all_invitations(
invitation_id INT AUTO_INCREMENT,
invitation_num INT,
invitation_user_belong_to INT,
invitation_event_belong_to INT,
invitation_num_of_seats INT,
invitation_seat_together BOOL,
invitation_num_of_space_seats_near INT,
PRIMARY KEY(invitation_id),
FOREIGN KEY(invitation_user_belong_to) REFERENCES all_users(user_id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(invitation_event_belong_to) REFERENCES all_events(event_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE all_seats(
seat_id INT AUTO_INCREMENT,
seat_row_num INT,
seat_num INT,
seat_price INT,
seat_is_taken BOOL,
seat_is_available BOOL,
seat_is_exist BOOL,
seat_invitation_belong_to INT,
seat_event_belong_to INT,
PRIMARY KEY(seat_id),
FOREIGN KEY(seat_invitation_belong_to) REFERENCES all_invitations(invitation_id) ON UPDATE CASCADE ON DELETE SET NULL,
FOREIGN KEY(seat_event_belong_to) REFERENCES all_events(event_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE all_managers(
manager_id INT AUTO_INCREMENT,
manager_user_id INT,
manager_event_id INT,
PRIMARY KEY(manager_id),
FOREIGN KEY(manager_user_id) REFERENCES all_users(user_id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(manager_event_id) REFERENCES all_events(event_id) ON UPDATE CASCADE ON DELETE CASCADE
);