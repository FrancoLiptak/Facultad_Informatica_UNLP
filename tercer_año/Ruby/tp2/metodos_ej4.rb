def longitud *unArreglo
    unArreglo.map { |i| puts "#{i} ---> #{i.to_s.size}" }
end

longitud(9, Time.now, 'Hola', {un: 'hash'}, :ruby)