CREATE TABLE IF NOT EXISTS "Ponente" (
	"id" serial NOT NULL,
	"nombres" varchar(40) NOT NULL,
	"apellidos" varchar(40) NOT NULL,
	"grado_academico" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);