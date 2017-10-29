def cuanto_falta?(fecha = Time.new(Time.new.year, Time.new.month, Time.new.day+1))
	fecha - Time.now
end

p cuanto_falta? Time.new(2017, 12, 31, 23, 59, 59)
# Debe retornar la cantidad de minutos que faltan para las 23:59:59 del 31/12/2017
p cuanto_falta?
# Debe retornar la cantidad de minutos que faltan para la medianoche de hoy