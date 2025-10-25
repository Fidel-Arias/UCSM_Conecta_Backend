CREATE OR REPLACE FUNCTION buscar_participante_apellidos(p_busqueda TEXT)
RETURNS TABLE(
    nombres VARCHAR,
    apPaterno VARCHAR,
    apMaterno VARCHAR,
    numDocumento VARCHAR,
    estado VARCHAR
)
AS $$
BEGIN
    RETURN QUERY
    SELECT
        p.nombres,
        p.ap_paterno,
        p.ap_materno,
        p.num_documento,
        p.estado
    FROM participante p
    WHERE
        -- Buscar por apellido paterno
        LOWER(p.ap_paterno) LIKE LOWER(CONCAT('%', p_busqueda, '%'))
        OR
        -- Buscar por apellidos concatenados (ej: "Gomez Perez")
        LOWER(CONCAT(p.ap_paterno, ' ', p.ap_materno)) LIKE LOWER(CONCAT('%', p_busqueda, '%'));
END;
$$ LANGUAGE plpgsql;