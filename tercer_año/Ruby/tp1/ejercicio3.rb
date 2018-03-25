def reemplazar string
    string.gsub! "{", "do\n"
    string.gsub! "}", "\nend"	    
end

# gsub! reemplaza y devuelve en el mismo string que le pasamos. No devuelve otro string.

p reemplazar("3.times { |i| puts i }")