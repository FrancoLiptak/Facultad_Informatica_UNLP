class Vehiculo
	
	MESS = "SYSTEM ERROR: method missing"
	
	def condicionesCorrectas?
		raise MESS # Indica que el método es abstracto.
	end

	def arrancar
		puts self.condicionesCorrectas? ? "Arranqué." : "No pude arrancar."
	end

end

class Auto < Vehiculo
    @frenoPuesto = false
    @cambioEnPuntoMuerto = true

	def condicionesCorrectas?
        !@frenoPuesto && @cambioEnPuntoMuerto
	end
end

class Lancha < Vehiculo
	def condicionesCorrectas?
		true
	end
end

class Moto < Vehiculo
	@estadoPatada = false

	def condicionesCorrectas?
		!@estadoPatada
	end
end

class Taller
	def probar (objeto)
		objeto.arrancar
	end
end

Taller.new.probar(Moto.new)

=begin
Respecto a la motosierra: (respuesta by Borre)
 La motosierra debería tener un comportamiento definido para que a la hora de enviarle el mensaje “arrancar”, esta pueda ser probada. Es decir que Motosierra debe ser polimórfica a Vehículos y sus subclases respecto a este mensaje. Se utiliza además el concepto de Duck Typing. 
 Duck Typing se refiere a la tendencia de Ruby a centrarse menos en la clase de un objeto, y dar prioridad a su comportamiento: qué métodos se pueden usar, y qué operaciones se pueden hacer con él.
=end
