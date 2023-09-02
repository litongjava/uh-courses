
/* Drop Tables */

DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS course_remark;
DROP TABLE IF EXISTS institution;
DROP TABLE IF EXISTS semester;
DROP TABLE IF EXISTS subject;




/* Create Tables */

-- 课程表
CREATE TABLE course
(
	id bigint NOT NULL,
	institution varchar(64),
	term varchar(64),
	subject_abbr varchar(64),
	subject_name varchar(64),
	focus_on varchar(64),
	crn varchar(10),
	course varchar(10),
	section varchar(2),
	title varchar(64),
	credits varchar(10),
	instructor varchar(64),
	curr_enrolled varchar(4),
	seats_avail varchar(4),
	curr_waitlisted varchar(4),
	wait_avail varchar(4),
	days varchar(10),
	time varchar(20),
	room varchar(20),
	dates varchar(20),
	details_url varchar(255),
	sources_url varchar(255),
	creator varchar(64),
	create_time timestamp,
	updater varchar(64),
	update_time timestamp,
	deleted smallint DEFAULT 0,
	tenant_id bigint DEFAULT 1,
	PRIMARY KEY (id)
) WITHOUT OIDS;


-- course_remark
CREATE TABLE course_remark
(
	id bigint NOT NULL,
	course_id bigint,
	remark text,
	creator varchar(64),
	create_time timestamp,
	updater varchar(64),
	update_time timestamp,
	deleted smallint DEFAULT 0,
	tenant_id bigint DEFAULT 1,
	PRIMARY KEY (id)
) WITHOUT OIDS;


-- institution
CREATE TABLE institution
(
	id bigint NOT NULL,
	abbr_name varchar(10),
	name varchar(64),
	creator varchar(64),
	create_time timestamp,
	updater varchar(64),
	update_time timestamp,
	deleted smallint DEFAULT 0,
	tenant_id bigint DEFAULT 1,
	PRIMARY KEY (id)
) WITHOUT OIDS;


-- semester
CREATE TABLE semester
(
	id bigint NOT NULL,
	institution_id bigint,
	name varchar(64),
	t int,
	creator varchar(64),
	create_time timestamp,
	updater varchar(64),
	update_time timestamp,
	deleted smallint DEFAULT 0,
	tenant_id bigint DEFAULT 1,
	PRIMARY KEY (id)
) WITHOUT OIDS;


-- subject
CREATE TABLE subject
(
	id bigint NOT NULL,
	semester_id bigint,
	name varchar(64),
	s varchar(10),
	creator varchar(64),
	create_time timestamp,
	updater varchar(64),
	update_time timestamp,
	deleted smallint DEFAULT 0,
	tenant_id bigint DEFAULT 1,
	PRIMARY KEY (id)
) WITHOUT OIDS;



/* Comments */

COMMENT ON TABLE course IS '课程表';
COMMENT ON COLUMN course.id IS 'id';
COMMENT ON COLUMN course.institution IS 'institution';
COMMENT ON COLUMN course.term IS 'term';
COMMENT ON COLUMN course.subject_abbr IS 'subject_abbr';
COMMENT ON COLUMN course.subject_name IS 'subject_name';
COMMENT ON COLUMN course.focus_on IS 'GenEd/Focus/Special Des';
COMMENT ON COLUMN course.crn IS 'crn';
COMMENT ON COLUMN course.course IS 'course';
COMMENT ON COLUMN course.section IS 'section';
COMMENT ON COLUMN course.title IS 'title';
COMMENT ON COLUMN course.credits IS 'credits';
COMMENT ON COLUMN course.instructor IS 'instructor';
COMMENT ON COLUMN course.curr_enrolled IS 'curr_enrolled';
COMMENT ON COLUMN course.seats_avail IS 'seats_avail';
COMMENT ON COLUMN course.curr_waitlisted IS 'curr_waitlisted';
COMMENT ON COLUMN course.wait_avail IS 'wait_avail';
COMMENT ON COLUMN course.days IS 'days';
COMMENT ON COLUMN course.time IS 'time';
COMMENT ON COLUMN course.room IS 'room';
COMMENT ON COLUMN course.dates IS 'dates';
COMMENT ON COLUMN course.details_url IS 'details_url';
COMMENT ON COLUMN course.sources_url IS 'sources_url';
COMMENT ON COLUMN course.creator IS '创建者';
COMMENT ON COLUMN course.create_time IS '创建时间';
COMMENT ON COLUMN course.updater IS '更新者';
COMMENT ON COLUMN course.update_time IS '更新时间';
COMMENT ON COLUMN course.deleted IS '是否删除';
COMMENT ON COLUMN course.tenant_id IS 'tenant_id';
COMMENT ON TABLE course_remark IS 'course_remark';
COMMENT ON COLUMN course_remark.id IS 'id';
COMMENT ON COLUMN course_remark.course_id IS 'course_id';
COMMENT ON COLUMN course_remark.remark IS 'remark';
COMMENT ON COLUMN course_remark.creator IS '创建者';
COMMENT ON COLUMN course_remark.create_time IS '创建时间';
COMMENT ON COLUMN course_remark.updater IS '更新者';
COMMENT ON COLUMN course_remark.update_time IS '更新时间';
COMMENT ON COLUMN course_remark.deleted IS '是否删除';
COMMENT ON COLUMN course_remark.tenant_id IS 'tenant_id';
COMMENT ON TABLE institution IS 'institution';
COMMENT ON COLUMN institution.id IS 'id';
COMMENT ON COLUMN institution.abbr_name IS 'abbr_name';
COMMENT ON COLUMN institution.name IS 'name';
COMMENT ON COLUMN institution.creator IS '创建者';
COMMENT ON COLUMN institution.create_time IS '创建时间';
COMMENT ON COLUMN institution.updater IS '更新者';
COMMENT ON COLUMN institution.update_time IS '更新时间';
COMMENT ON COLUMN institution.deleted IS '是否删除';
COMMENT ON COLUMN institution.tenant_id IS 'tenant_id';
COMMENT ON TABLE semester IS 'semester';
COMMENT ON COLUMN semester.id IS 'id';
COMMENT ON COLUMN semester.institution_id IS 'institution_id';
COMMENT ON COLUMN semester.name IS 'name';
COMMENT ON COLUMN semester.t IS 't';
COMMENT ON COLUMN semester.creator IS '创建者';
COMMENT ON COLUMN semester.create_time IS '创建时间';
COMMENT ON COLUMN semester.updater IS '更新者';
COMMENT ON COLUMN semester.update_time IS '更新时间';
COMMENT ON COLUMN semester.deleted IS '是否删除';
COMMENT ON COLUMN semester.tenant_id IS 'tenant_id';
COMMENT ON TABLE subject IS 'subject';
COMMENT ON COLUMN subject.id IS 'id';
COMMENT ON COLUMN subject.semester_id IS 'semester_id';
COMMENT ON COLUMN subject.name IS 'name';
COMMENT ON COLUMN subject.s IS 's';
COMMENT ON COLUMN subject.creator IS '创建者';
COMMENT ON COLUMN subject.create_time IS '创建时间';
COMMENT ON COLUMN subject.updater IS '更新者';
COMMENT ON COLUMN subject.update_time IS '更新时间';
COMMENT ON COLUMN subject.deleted IS '是否删除';
COMMENT ON COLUMN subject.tenant_id IS 'tenant_id';