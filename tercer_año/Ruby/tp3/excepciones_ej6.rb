# Este script lee una secuencia de no menos de 15 números desde teclado y luego imprime el resultado de la división
# de cada número por su entero inmediato anterior.
# Como primer paso se pide al usuario que indique la cantidad de números que ingresará.

begin

    puts '¿Cuál es la cantidad de números que ingresará? Debe ser al menos 15'
    cantidad = Integer(gets)

    Raise RunTimeError => "Debe ingresar un número mayor o igual a 15" unless cantidad >= 15

rescue RunTimeError => e
    puts "#{e.message}"
    retry
rescue NameError => e
    puts "#{e.message}"
    retry
rescue ArgumentError => e
    puts "#{e.message}. Intentelo nuevamente"
    retry
end 

# Luego se almacenan los números
numeros = 1.upto(cantidad).map do
puts 'Ingrese un número'

begin
    numero = Integer(gets)

    raise RuntimeError, "Debe ingresar un número mayor a 0. Intente nuevamente:" unless numero > 0

rescue RuntimeError => e
    puts "#{e.message}"
    retry
rescue NameError => e
    puts "#{e.message}"
    retry
rescue ArgumentError => e
    puts "#{e.message}. Intentelo nuevamente"
    retry
end
numero
end

# Y finalmente se imprime cada número dividido por su número entero inmediato anterior

resultado = numeros.map do | x| 
    begin 
        result = x / (x - 1) 

    rescue ZeroDivisionError => e
        puts e.message
    end
    result
end
puts 'El resultado es: %s' % resultado.join(', ')
