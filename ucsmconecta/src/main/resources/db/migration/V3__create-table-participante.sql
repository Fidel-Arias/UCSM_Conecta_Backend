CREATE TABLE IF NOT EXISTS "Participante" (
	"id" serial NOT NULL,
	"nombres" varchar(40) NOT NULL,
	"ap_paterno" varchar(25) NOT NULL,
	"ap_materno" varchar(25) NOT NULL,
	"n_documento" varchar(20) NOT NULL UNIQUE,
	"email" varchar(255) NOT NULL UNIQUE,
	"tipo_participante_id" bigint NOT NULL,
	"escuela_profesional_id" bigint NOT NULL,
	"congreso_id" bigint NOT NULL,
	"estado" varchar(15) NOT NULL DEFAULT 'MATRICULADO',
	"qr_code" varchar(255),
	PRIMARY KEY ("id")
);