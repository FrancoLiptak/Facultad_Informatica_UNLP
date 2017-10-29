def procesar_hash unHash, unProc
    unHash.invert.transform_values { |valor| unProc.call valor }
end

hash = { 'clave' => 1, :otra_clave => 'valor' }
p procesar_hash(hash, ->(x) { x.to_s.upcase }) # Recibo un Proc ya creado (lo crea con la expresión lambda)
# => { 1 => 'CLAVE', 'valor' => 'OTRA_CLAVE' }