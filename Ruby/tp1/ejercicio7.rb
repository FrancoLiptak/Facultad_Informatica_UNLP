def reverso string
    string.reverse
end

def eliminar_blancos string
    string.delete ' '
end

def valor_ascii string
    string.each_byte { |i| puts i }
end

def cambiar_vocales string
    string.downcase.gsub! /([aeiou])/, 'a' => 4, "e" => 3, "i" => 1, "o" => 0, "u" => 6
end

p cambiar_vocales 'hola'