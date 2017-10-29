def contar aString, anotherString
    aString.downcase.scan(/\b#{anotherString}\b/i).size
end


p contar("La casa de la esquina tiene la puerta roja y la ventana blanca.", "la")
# => 4