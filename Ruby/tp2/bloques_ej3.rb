def manejandoExcepciones *parametros
    begin
        yield parametros
        rescue RuntimeError
            puts "Algo salio mal..."
            :rt
        rescue NoMethodError => b
            puts "No encontre un metodo: " << b.message
            :nm
        rescue
            puts "¡No sé qué hacer!"
            raise
        else
            :ok
        end
end

p manejandoExcepciones("a", "b", "c") { |x| x = "a" }