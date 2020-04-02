CREATE TABLE resume
(
    id          bigint PRIMARY KEY,
    name        character varying(30) NOT NULL,
    surname     character varying(30) NOT NULL,
    second_name character varying(30) NOT NULL,
    birthday    date                  NOT NULL,
    gender      character varying(10) NOT NULL
);

CREATE TABLE contact_type
(
    id    bigint PRIMARY KEY,
    type character varying(30) NOT NULL
);

CREATE TABLE contact
(
    id    bigint PRIMARY KEY,
    value character varying(50) NOT NULL,
    contact_type_id bigint NOT NULL,
    FOREIGN KEY (contact_type_id) REFERENCES contact_type (id)
);

CREATE TABLE technology
(
    id   bigint PRIMARY KEY,
    name character varying(30) NOT NULL
);

CREATE TABLE resume_contact
(
    resume_id  bigint NOT NULL,
    contact_id bigint NOT NULL,
    FOREIGN KEY (resume_id) REFERENCES resume (id),
    FOREIGN KEY (contact_id) REFERENCES contact (id)
);

CREATE TABLE resume_technology
(
    resume_id     bigint NOT NULL,
    technology_id bigint NOT NULL,
    FOREIGN KEY (resume_id) REFERENCES resume (id),
    FOREIGN KEY (technology_id) REFERENCES technology (id)
);
