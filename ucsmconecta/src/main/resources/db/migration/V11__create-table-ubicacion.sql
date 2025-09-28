CREATE TABLE IF NOT EXISTS "Ubicacion" (
	"id" serial NOT NULL,
	"nombre" varchar(255) NOT NULL,
	"estado" bool NOT NULL DEFAULT TRUE,
	PRIMARY KEY ("id")
);