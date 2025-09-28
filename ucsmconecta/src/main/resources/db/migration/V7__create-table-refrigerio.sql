CREATE TABLE IF NOT EXISTS "Refrigerio" (
	"id" serial NOT NULL,
	"fecha" date NOT NULL,
	"estado" varchar(14) NOT NULL DEFAULT 'ACTIVADO',
	"participante_id" bigint NOT NULL,
	"congreso_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);