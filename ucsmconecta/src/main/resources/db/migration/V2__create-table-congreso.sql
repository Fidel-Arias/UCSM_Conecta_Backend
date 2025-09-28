CREATE TABLE IF NOT EXISTS "Congreso" (
	"id" serial NOT NULL,
	"nombre" varchar(255) NOT NULL,
	"fecha_inicio" date NOT NULL,
	"fecha_fin" date NOT NULL,
	"asistencia_total" bigint DEFAULT 0,
	"n_refrigerio" bigint DEFAULT 0,
	"estado" bool NOT NULL DEFAULT TRUE,
	"escuela_profesional_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);