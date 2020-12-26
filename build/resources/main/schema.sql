create sequence if not exists hibernate_sequence;

create table if not exists user (
    id INT NOT NULL PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    home_address VARCHAR(100),
    current_loc_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table if not exists meeting (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    destination_loc_id INT,
    appointment_time TIMESTAMP,
    state VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table if not exists meeting_user (
    id INT NOT NULL PRIMARY KEY,
    meeting_id INT NOT NULL,
    user_id INT NOT NULL,
    user_type VARCHAR NOT NULL,
    location_sharing_state VARCHAR NOT NULL,
    depart_loc_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table if not exists current_location (
    id INT NOT NULL PRIMARY KEY,
    latitude VARCHAR(100),
    longitude VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table if not exists depart_location (
    id INT NOT NULL PRIMARY KEY,
    address VARCHAR(100),
    latitude VARCHAR(100),
    longitude VARCHAR(100),
    depart_time TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table if not exists destination_location (
    id INT NOT NULL PRIMARY KEY,
    address VARCHAR(100),
    latitude VARCHAR(100),
    longitude VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table if not exists configuration(

);