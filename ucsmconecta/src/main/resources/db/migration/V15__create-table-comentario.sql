CREATE TABLE IF NOT EXISTS "Comentario" (
	"id" serial NOT NULL,
	"texto" varchar(255) NOT NULL,
	"fecha" date NOT NULL,
	"estado" varchar(10) NOT NULL DEFAULT 'PERMITIDO',
	"participante_id" bigint NOT NULL,
	"congreso_id" bigint NOT NULL,
	PRIMARY KEY ("id")
);