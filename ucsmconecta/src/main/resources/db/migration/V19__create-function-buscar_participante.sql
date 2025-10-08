CREATE OR REPLACE FUNCTION buscar_participante_apellidos(p_busqueda TEXT)
RETURNS TABLE(
    nombres VARCHAR,
    a_paterno VARCHAR,
    a_materno VARCHAR,
    n_documento VARCHAR,
)
AS $$
BEGIN
    RETURN QUERY
    SELECT
        p.nombres,
        p.a_paterno,
        p.a_materno,
        p.n_documento
    FROM participante p
    WHERE
        -- Buscar por apellido paterno
        LOWER(p.a_paterno) LIKE LOWER(CONCAT('%', p_busqueda, '%'))
        OR
        -- Buscar por apellidos concatenados (ej: "Gomez Perez")
        LOWER(CONCAT(p.a_paterno, ' ', p.a_materno)) LIKE LOWER(CONCAT('%', p_busqueda, '%'));
END;
$$ LANGUAGE plpgsql;