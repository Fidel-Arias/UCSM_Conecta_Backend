CREATE TABLE IF NOT EXISTS "Ponencia" (
	"id" serial NOT NULL,
	"nombre" varchar(255) NOT NULL,
	"estado" bool NOT NULL DEFAULT TRUE,
	"ponente_id" bigint NOT NULL,
	"congreso_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);