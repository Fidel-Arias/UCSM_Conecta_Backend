CREATE TABLE "Congreso_Colaborador" (
  "id" serial PRIMARY KEY,
  "congreso_id" bigint NOT NULL,
  "colaborador_id" bigint NOT NULL
);