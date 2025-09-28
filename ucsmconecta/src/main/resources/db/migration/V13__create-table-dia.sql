CREATE TABLE IF NOT EXISTS "Dia" (
	"id" serial NOT NULL,
	"fecha" date NOT NULL,
	"estado" bool NOT NULL DEFAULT TRUE,
	"congreso_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);