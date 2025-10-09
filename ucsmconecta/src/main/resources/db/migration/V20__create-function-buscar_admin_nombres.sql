CREATE OR REPLACE FUNCTION buscar_admin_nombres(p_busqueda TEXT)
RETURNS TABLE(
    nombres VARCHAR,
    a_paterno VARCHAR,
    a_materno VARCHAR
)
AS $$
BEGIN
    RETURN QUERY
    SELECT
        a.nombres,
        a.a_paterno,
        a.a_materno
    FROM administrador a
    WHERE
        -- Buscar por nombres
        LOWER(a.nombres) LIKE LOWER(CONCAT('%', p_busqueda, '%'));
END;
$$ LANGUAGE plpgsql;