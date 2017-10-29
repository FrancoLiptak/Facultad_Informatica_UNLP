class Array
    def randomly &block
        shuffle.each &block
    end
end

=begin

El signo & puede preceder al último parámetro de una declaración de función. 
Esto indica que la función espera que se pase un bloque de código. 
Se creará un objeto Proc y se le asignará el parámetro que contiene el bloque pasado.

=end

p ["a", "b", "c"].randomly
p ["a", "b", "c"].randomly { |i| i.upcase }
