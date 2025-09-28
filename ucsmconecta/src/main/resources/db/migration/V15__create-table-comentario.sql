CREATE TABLE IF NOT EXISTS "Comentario" (
	"id" serial NOT NULL,
	"texto" varchar(255) NOT NULL,
	"fecha" date NOT NULL,
	"estado" bool NOT NULL DEFAULT TRUE,
	"participante_id" bigint NOT NULL,
	"congreso_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);