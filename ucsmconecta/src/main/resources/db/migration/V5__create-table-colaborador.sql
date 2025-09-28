CREATE TABLE IF NOT EXISTS "Colaborador" (
	"id" serial NOT NULL,
	"nombres" varchar(40) NOT NULL,
	"a_paterno" varchar(25) NOT NULL,
	"a_materno" varchar(25) NOT NULL,
	"email" varchar(255) NOT NULL UNIQUE,
	"password" varchar(255) NOT NULL,
	"estado" bool NOT NULL DEFAULT TRUE,
	"escuela_profesional_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);