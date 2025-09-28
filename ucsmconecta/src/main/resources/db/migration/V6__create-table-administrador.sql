CREATE TABLE IF NOT EXISTS "Administrador" (
	"id" serial NOT NULL UNIQUE,
	"nombres" varchar(40) NOT NULL,
	"a_paterno" varchar(25) NOT NULL,
	"a_materno" varchar(25) NOT NULL,
	"email" varchar(255) NOT NULL UNIQUE,
	"password" varchar(255) NOT NULL,
	"estado" varchar(11) NOT NULL DEFAULT 'ACTIVADO',
	"escuela_profesional_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);