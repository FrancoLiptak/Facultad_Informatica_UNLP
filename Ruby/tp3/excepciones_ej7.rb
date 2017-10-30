class InvalidValue < StandardError
    def initialize(msg = "Debe ingresar un número")
      super msg
    end
  end

cantidad = 0

puts '¿Cuál es la cantidad de números que ingresará? Debe ser al menos 15'

begin
    cantidad = Integer(gets) 

    raise InvalidValue, "Debe ingresar un número mayor o igual a 15. Intente nuevamente:" unless cantidad >= 15

rescue ArgumentError => e
    puts "#{e.message}. Intentelo nuevamente:"
    retry
rescue InvalidValue => e
    puts e.message
    retry
end    


numeros = 1.upto(cantidad).map do
    puts 'Ingrese un número'

    begin
        numero = Integer(gets)
        raise InvalidValue, "Debe ingresar un número mayor a 0. Intente nuevamente:" unless numero > 0

    rescue ArgumentError => e
        puts "#{e.message}. Intente nuevamente:"
        retry
    rescue InvalidValue => e
        puts e.message
        retry
    end
    numero 
 end

 resultado = numeros.map do | x| 
    begin 
        result = x / (x - 1) 
        raise ZeroDivisionError unless (x - 1) > 0
    rescue ZeroDivisionError => e
        puts e.message
    end
    result
end
puts 'El resultado es: %s' % resultado.join(', ')