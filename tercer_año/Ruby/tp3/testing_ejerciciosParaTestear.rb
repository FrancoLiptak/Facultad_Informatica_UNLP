# Práctica 1

def contar aString, anotherString
    aString.downcase.scan(/#{anotherString.downcase}/).size
end

def contar_palabras aString, anotherString
    aString.downcase.scan(/\b#{anotherString}\b/i).size
end

def longitud unArreglo
    unArreglo.map { |i| i.length }
end

# Práctica 2
def ordenar_arreglo unArreglo
    unArreglo.sort
end

def ordenar *unArreglo
    unArreglo.sort
end

module Opposite
    def opposite
        !self
    end
end