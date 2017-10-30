# Este script lee una secuencia de no menos de 15 números desde teclado y luego imprime el resultado de la división
# de cada número por su entero inmediato anterior.
# Como primer paso se pide al usuario que indique la cantidad de números que ingresará.

cantidad = 0

puts '¿Cuál es la cantidad de números que ingresará? Debe ser al menos 15'

begin
    cantidad = Integer(gets) 

    raise Exception, "Debe ingresar un número mayor o igual a 15. Intente nuevamente:" unless cantidad >= 15

rescue ArgumentError => e
    puts "#{e.message}. Intentelo nuevamente:"
    retry
rescue Exception => e
    puts e.message
    retry
end    


numeros = 1.upto(cantidad).map do
    puts 'Ingrese un número'

    begin
        numero = Integer(gets)
    
    rescue ArgumentError => e
        puts "#{e.message}. Intente nuevamente:"
        retry
    end 
 end

resultado = numeros.map do | x| 
    x / (x - 1) 
    raise ZeroDivisionError unless (x - 1) > 0 
end
puts 'El resultado es: %s' % resultado.join(', ')