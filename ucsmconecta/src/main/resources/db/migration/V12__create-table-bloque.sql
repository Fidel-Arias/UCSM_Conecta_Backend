CREATE TABLE IF NOT EXISTS "Bloque" (
	"id" serial NOT NULL,
	"hora_inicio" time without time zone NOT NULL,
	"hora_final" time without time zone NOT NULL,
	"dia_id" bigint NOT NULL,
	"ubicacion_id" bigint NOT NULL,
	"ponencia_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);