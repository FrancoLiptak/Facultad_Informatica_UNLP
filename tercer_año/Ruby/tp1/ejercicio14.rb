def notacion_entera array
    array.each_with_index.reduce(0) { |sum, (value, index)| sum + value * 256 ** index }
end

def notacion_hexadecimal array
	array.inject("#") { |sum, n| sum + "#{n.to_s(16)}"} 
end

p notacion_hexadecimal([0, 128, 255])
p notacion_entera([0, 128, 255])