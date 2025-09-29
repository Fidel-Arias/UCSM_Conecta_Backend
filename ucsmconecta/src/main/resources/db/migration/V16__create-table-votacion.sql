CREATE TABLE IF NOT EXISTS "Votación" (
	"id" serial NOT NULL,
	"score" bigint NOT NULL DEFAULT '0',
	"participante_id" bigint NOT NULL,
	"ponencia_id" bigint NOT NULL,
	"congreso_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);