CREATE OR REPLACE FUNCTION buscar_colaborador_apellidos(p_busqueda TEXT)
RETURNS TABLE(
    nombres VARCHAR,
    a_paterno VARCHAR,
    a_materno VARCHAR,
    email VARCHAR,
    estado BOOL
)
AS $$
BEGIN
    RETURN QUERY
    SELECT
        c.nombres,
        c.a_paterno,
        c.a_materno,
        c.email,
        c.estado
    FROM colaborador c
    WHERE
        -- Buscar por apellido paterno
        LOWER(c.a_paterno) LIKE LOWER(CONCAT('%', p_busqueda, '%'))
        OR
        -- Buscar por apellidos concatenados (ej: "Gomez Perez")
        LOWER(CONCAT(c.a_paterno, ' ', c.a_materno)) LIKE LOWER(CONCAT('%', p_busqueda, '%'));
END;
$$ LANGUAGE plpgsql;