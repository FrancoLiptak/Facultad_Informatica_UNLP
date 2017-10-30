# Este script lee una secuencia de no menos de 15 números desde teclado y luego imprime el resultado de la división
# de cada número por su entero inmediato anterior.
# Como primer paso se pide al usuario que indique la cantidad de números que ingresará.

cantidad = 0

puts '¿Cuál es la cantidad de números que ingresará? Debe ser al menos 15'

begin
    cantidad = Integer(gets) 

    raise Exception unless cantidad >= 15

rescue ArgumentError
    puts "Debe ingresar un número. Intente nuevamente:"
    retry
rescue Exception
    puts "El número debe ser mayor a 15. Intente nuevamente:"
    retry
end    


numeros = 1.upto(cantidad).map do
    puts 'Ingrese un número'

    begin
        numero = Integer(gets)
    
    rescue ArgumentError
        puts "Debe ingresar un número. Intente nuevamente:"
        retry
    end 
 end

resultado = numeros.map do | x| 
    x / (x - 1) 
    raise ZeroDivisionError unless (x - 1) > 0 
end
puts 'El resultado es: %s' % resultado.join(', ')