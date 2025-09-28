CREATE TABLE IF NOT EXISTS "Dia" (
	"id" serial NOT NULL,
	"fecha" date NOT NULL,
	"estado" varchar(11) NOT NULL DEFAULT 'ACTIVADO',
	"congreso_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);