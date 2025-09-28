CREATE TABLE IF NOT EXISTS "Ubicacion" (
	"id" serial NOT NULL,
	"nombre" varchar(255) NOT NULL,
	"estado" varchar(11) NOT NULL DEFAULT 'ACTIVADO',
	PRIMARY KEY ("id")
);