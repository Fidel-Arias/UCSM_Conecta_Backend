CREATE TABLE IF NOT EXISTS "Asistencia" (
	"id" serial NOT NULL,
	"fecha" date NOT NULL DEFAULT CURRENT_DATE,
	"hora" time without time zone NOT NULL DEFAULT CURRENT_TIME,
	"participante_id" bigint NOT NULL,
	"bloque_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);